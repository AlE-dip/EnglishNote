package com.ale.englishnote.service;

import com.ale.englishnote.dto.InputRelationWordDto;
import com.ale.englishnote.dto.InputWordDto;
import com.ale.englishnote.entity.*;
import com.ale.englishnote.dto.WordDto;
import com.ale.englishnote.repository.MeanRepository;
import com.ale.englishnote.repository.WordRepository;
import com.ale.englishnote.util.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    public Word saveWord(Word word) {
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
        return wordRepository.findByEnglishLike("%" + english + "%", pageRequest).stream().map(WordDto::newInstance).toList();
    }

    @Transactional(rollbackFor = AppException.class)
    @Override
    public WordDto insertWord(InputWordDto inputWordDto) {
        if (!wordRepository.findByEnglish(inputWordDto.getEnglish()).isEmpty()) {
            throw new AppException(HttpStatus.BAD_REQUEST, MessageContent.WORD_ALREADY_EXIST);
        }
        Word word = inputWordDto.toWord();

        //Check tags
        if (inputWordDto.getTags() != null && !inputWordDto.getTags().isEmpty()) {
            List<Tag> tags = tagService.findAllByIds(inputWordDto.getTags());
            if (tags.size() != inputWordDto.getTags().size()) {
                throw new AppException(HttpStatus.BAD_REQUEST, MessageContent.ID_NOT_EXIST + Tag.class.getName());
            }
            word.getTags().addAll(tags);
        }

        //Add means
        List<Mean> means = meanService.insertMeans(inputWordDto.getMeans(), word);
        word.setMeans(means);

        //Add relation word
        if (inputWordDto.getRelationWords() != null && !inputWordDto.getRelationWords().isEmpty()) {
            List<RelationWord> relationWords = relationWordService.insertRelationWords(inputWordDto.getRelationWords(), word);
            word.setRelationWords(relationWords);
        }

        wordRepository.save(word);

        return WordDto.newInstance(word);
    }
}
