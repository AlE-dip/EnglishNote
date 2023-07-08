package com.ale.englishnote.service;

import com.ale.englishnote.dto.insert.InsertWord;
import com.ale.englishnote.entity.*;
import com.ale.englishnote.dto.view.WordDto;
import com.ale.englishnote.repository.WordRepository;
import com.ale.englishnote.util.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@AllArgsConstructor
public class WordServiceImpl implements WordService {

    private final WordRepository wordRepository;
    private final TagService tagService;
    private final RelationWordService relationWordService;
    private final MeanService meanService;

    @Override
    public Word findFirstWord() {
        return wordRepository.findFirstByOrderByIdAsc();
    }

    @Override
    public List<Word> saveWords(Iterable<Word> words) {
        return wordRepository.saveAll(words);
    }

    @Override
    public Word insertWord(Word word) {
        return wordRepository.save(word);
    }

    @Override
    public List<WordDto> queryWord(QueryRequest queryRequest) {
        PageRequest pageRequest = UtilContent.pageRequest(queryRequest);
        return wordRepository.findAll(pageRequest).stream()
                .map(WordDto::newInstance)
                .toList();
    }

    @Override
    public List<WordDto> searchWord(String english, QueryRequest queryRequest) {
        PageRequest pageRequest = UtilContent.pageRequest(queryRequest);
        return wordRepository.searchWord(english, pageRequest).stream().map(WordDto::newInstance).toList();
    }

    @Transactional(rollbackFor = AppException.class)
    @Override
    public WordDto insertWord(InsertWord insertWord) {
        if (!wordRepository.findByEnglish(insertWord.getEnglish()).isEmpty()) {
            throw new AppException(HttpStatus.BAD_REQUEST, MessageContent.WORD_ALREADY_EXIST);
        }
        Word word = insertWord.toWord();

        addTag(insertWord, word);

        wordRepository.save(word);

        addMeans(insertWord, word);
        addRelationWords(insertWord, word);

        wordRepository.save(word);

        return WordDto.newInstance(word);
    }

    @Transactional(rollbackFor = AppException.class)
    @Override
    public WordDto updateWord(InsertWord insertWord, Long id) {
        AtomicReference<Word> atomicReference = new AtomicReference<>();
        List<Word> nameWord = wordRepository.findByEnglish(insertWord.english);
        nameWord.forEach(word -> {
            if(word.getId() != id){
                throw new AppException(HttpStatus.BAD_REQUEST, MessageContent.WORD_ALREADY_EXIST);
            }
        });
        wordRepository.findById(id).ifPresentOrElse(word -> {
            //Tag
            word.getTags().clear();

            //Mean
            meanService.deleteMeans(word.getMeans());
            word.getMeans().clear();
            word.getRelationWords().clear();
            wordRepository.save(word);

            List<RelationWord> relationWords = relationWordService.getRelationWordByWordId(word.getId());
            relationWords.forEach(relationWord -> {
                Word wordTmp = relationWord.getWord().getId() != word.getId()
                        ? relationWord.getWord()
                        : relationWord.getWordRelation();
                wordTmp.getRelationWords().remove(relationWord);
                wordRepository.save(wordTmp);
            });

            relationWordService.deleteRelationWord(relationWords);

            word.setEnglish(insertWord.getEnglish());
            addTag(insertWord, word);
            addMeans(insertWord, word);
            addRelationWords(insertWord, word);
            wordRepository.save(word);

            atomicReference.set(word);
        }, () -> {
            throw new AppException(HttpStatus.BAD_REQUEST, MessageContent.ID_NOT_EXIST + Word.class.getName());
        });
        return WordDto.newInstance(atomicReference.get());
    }

    private void addTag(InsertWord insertWord, Word word) {
        //Check tags
        if (insertWord.getTags() != null && !insertWord.getTags().isEmpty()) {
            List<Tag> tags = tagService.findAllByIds(insertWord.getTags());
            if (tags.size() != insertWord.getTags().size()) {
                throw new AppException(HttpStatus.BAD_REQUEST, MessageContent.ID_NOT_EXIST + Tag.class.getName());
            }
            word.getTags().addAll(tags);
        }
    }

    private void addMeans(InsertWord insertWord, Word word) {
        //Add means
        List<Mean> means = meanService.insertMeans(insertWord.getMeans(), word);
        word.setMeans(means);
    }

    private void addRelationWords(InsertWord insertWord, Word word) {
        //Add relation word
        if (insertWord.getRelationWords() != null && !insertWord.getRelationWords().isEmpty()) {
            List<RelationWord> relationWords;
            relationWords = relationWordService.insertRelationWords(insertWord.getRelationWords(), word);
            word.setRelationWords(relationWords);
        }
    }

}
