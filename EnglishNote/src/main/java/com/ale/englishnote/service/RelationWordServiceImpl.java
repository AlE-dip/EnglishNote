package com.ale.englishnote.service;

import com.ale.englishnote.entity.RelationWord;
import com.ale.englishnote.repository.RelationWordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RelationWordServiceImpl implements RelationWordService {
    RelationWordRepository relationWordRepository;
    @Override
    public void saveRelationWords(Iterable<RelationWord> relationWords) {
        relationWordRepository.saveAll(relationWords);
    }
}
