package com.ale.englishnote.service;

import com.ale.englishnote.model.Word;
import com.ale.englishnote.repository.WordRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WordServiceImpl implements WordService {

    WordRepository wordRepository;

    @Override
    public Word findFirstWord() {
        return wordRepository.findFirstByOrderByIdAsc();
    }

    public void saveWords(List<Word> words) {
        wordRepository.saveAll(words);
    }
}
