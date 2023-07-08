package com.ale.englishnote.service;

import com.ale.englishnote.dto.insert.InsertRelationWord;
import com.ale.englishnote.entity.RelationWord;
import com.ale.englishnote.entity.Word;

import java.util.List;

public interface RelationWordService {
    void deleteRelationWord(List<RelationWord> relationWords);

    enum Type {
        UPDATE,
        INSERT
    }
    List<RelationWord> saveRelationWords(Iterable<RelationWord> relationWords);

    List<RelationWord> insertRelationWords(List<InsertRelationWord> insertRelationWords, Word wordCurrent);

    List<RelationWord> getRelationWordByWordId(Long wordId);
}
