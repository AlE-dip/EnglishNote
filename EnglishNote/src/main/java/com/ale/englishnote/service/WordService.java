package com.ale.englishnote.service;

import com.ale.englishnote.dto.insert.InsertWord;
import com.ale.englishnote.entity.Word;
import com.ale.englishnote.dto.view.WordDto;
import com.ale.englishnote.util.QueryRequest;

import java.util.List;

public interface WordService {
    enum OperationType {
        UPDATE,
        INSERT
    }

    Word findFirstWord();

    List<Word> saveWords(Iterable<Word> words);

    Word insertWord(Word word);

    public List<WordDto> queryWord(QueryRequest queryRequest);

    List<WordDto> searchWord(String english, QueryRequest queryRequest);

    WordDto insertWord(InsertWord insertWord);
    WordDto updateWord(InsertWord insertWord, Long id);
}
