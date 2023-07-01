package com.ale.englishnote.service;

import com.ale.englishnote.entity.Word;
import com.ale.englishnote.repository.WordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WordServiceImpl implements WordService {

    WordRepository wordRepository;

    @Override
    public Word findFirstWord() {
        return wordRepository.findFirstByOrderByIdAsc();
    }

    public void saveWords(Iterable<Word> words) {
        wordRepository.saveAll(words);
    }

    @Override
    public void saveWord(Word word) {
        wordRepository.save(word);
    }
}
