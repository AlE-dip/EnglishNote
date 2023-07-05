package com.ale.englishnote.service;

import com.ale.englishnote.dto.InputWordDto;
import com.ale.englishnote.entity.Word;
import com.ale.englishnote.dto.WordDto;
import com.ale.englishnote.util.QueryRequest;

import java.util.List;

public interface WordService {
    Word findFirstWord();

    List<Word> saveWords(Iterable<Word> words);

    Word saveWord(Word word);

    public List<WordDto> queryWord(QueryRequest queryRequest);

    List<WordDto> searchWord(String english, QueryRequest queryRequest);

    WordDto insertWord(InputWordDto inputWordDto);
}
