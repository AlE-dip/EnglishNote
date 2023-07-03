package com.ale.englishnote.service;

import com.ale.englishnote.entity.RelationWord;

import java.util.List;

public interface RelationWordService {
    List<RelationWord> saveRelationWords(Iterable<RelationWord> relationWords);
}
