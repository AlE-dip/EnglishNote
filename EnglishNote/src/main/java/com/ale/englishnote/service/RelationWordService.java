package com.ale.englishnote.service;

import com.ale.englishnote.dto.InputRelationWordDto;
import com.ale.englishnote.entity.RelationWord;
import com.ale.englishnote.entity.Word;

import java.util.List;

public interface RelationWordService {
    List<RelationWord> saveRelationWords(Iterable<RelationWord> relationWords);

    List<RelationWord> insertRelationWords(List<InputRelationWordDto> inputRelationWordDtos, Word wordCurrent);
}
