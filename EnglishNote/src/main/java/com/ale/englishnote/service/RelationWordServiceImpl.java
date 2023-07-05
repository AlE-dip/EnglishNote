package com.ale.englishnote.service;

import com.ale.englishnote.dto.InputRelationWordDto;
import com.ale.englishnote.entity.RelationWord;
import com.ale.englishnote.entity.Word;
import com.ale.englishnote.repository.RelationWordRepository;
import com.ale.englishnote.repository.WordRepository;
import com.ale.englishnote.util.AppException;
import com.ale.englishnote.util.MessageContent;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RelationWordServiceImpl implements RelationWordService {
    private final RelationWordRepository relationWordRepository;
    private final WordRepository wordRepository;

    @Override
    public List<RelationWord> saveRelationWords(Iterable<RelationWord> relationWords) {
        return relationWordRepository.saveAll(relationWords);
    }

    @Override
    public List<RelationWord> insertRelationWords(List<InputRelationWordDto> inputRelationWordDtos, Word wordCurrent) {
        List<Long> listId = inputRelationWordDtos.stream()
                .map(InputRelationWordDto::getWordRelationId)
                .toList();
        List<Word> wordRelation = wordRepository.findAllByIdIn(listId);
        if (listId.size() != wordRelation.size()) {
            throw new AppException(HttpStatus.BAD_REQUEST, MessageContent.ID_NOT_EXIST + Word.class.getName());
        }

        List<RelationWord> relationWords = new ArrayList<>();
        inputRelationWordDtos.forEach(r -> {
            wordRelation.stream()
                    .filter(w -> w.getId() == r.getWordRelationId())
                    .findFirst()
                    .ifPresent(w -> {
                        RelationWord relationWord = r.toRelationWord(wordCurrent);
                        relationWords.add(relationWord);
                        relationWord.setWordRelation(w);
                        w.getRelationWords().clear();
                        w.getRelationWords().add(relationWord);
                    });
        });
        relationWordRepository.saveAll(relationWords);
        wordRepository.saveAll(wordRelation);
        return relationWords;
    }
}
