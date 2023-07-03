package com.ale.englishnote.service;

import com.ale.englishnote.entity.Word;
import com.ale.englishnote.model.WordModel;
import com.ale.englishnote.repository.WordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WordServiceImpl implements WordService {

    private final WordRepository wordRepository;

    @Override
    public Word findFirstWord() {
        return wordRepository.findFirstByOrderByIdAsc();
    }

    @Override
    public List<Word> saveWords(Iterable<Word> words) {
        return wordRepository.saveAll(words);
    }

    @Override
    public List<WordModel> insertWords(Iterable<Word> words) {
        return null;
    }

    @Override
    public Word saveWord(Word word) {
        return wordRepository.save(word);
    }

    @Override
    public List<WordModel> findAll() {
        return wordRepository.findAll().stream().map(WordModel::new).toList();
    }

    @Override
    public List<WordModel> findWord(String english) {
        return wordRepository.findByEnglish(english).stream().map(WordModel::new).toList();
    }

    @Override
    public List<WordModel> filterWord(String english) {
        return wordRepository.findByEnglishLike("%" + english + "%").stream().map(WordModel::new).toList();
    }
}
