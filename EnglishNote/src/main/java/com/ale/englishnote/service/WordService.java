package com.ale.englishnote.service;

import com.ale.englishnote.entity.Word;
import com.ale.englishnote.model.WordModel;

import java.util.Arrays;
import java.util.List;

public interface WordService {
    Word findFirstWord();

    List<Word> saveWords(Iterable<Word> words);

    List<WordModel> insertWords(Iterable<Word> words);
    Word saveWord(Word word);

    List<WordModel> findAll();

    List<WordModel> findWord(String english);

    List<WordModel> filterWord(String word);
}
