package com.ale.englishnote.service;

import com.ale.englishnote.entity.RelationWord;
import com.ale.englishnote.repository.RelationWordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RelationWordServiceImpl implements RelationWordService {
    private final RelationWordRepository relationWordRepository;
    @Override
    public List<RelationWord> saveRelationWords(Iterable<RelationWord> relationWords) {
        return relationWordRepository.saveAll(relationWords);
    }
}
