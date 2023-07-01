package com.ale.englishnote.service;

import com.ale.englishnote.entity.Word;

public interface WordService {
    Word findFirstWord();

    void saveWords(Iterable<Word> words);
    void saveWord(Word word);
}
