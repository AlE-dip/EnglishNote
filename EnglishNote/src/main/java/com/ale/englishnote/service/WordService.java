package com.ale.englishnote.service;

import com.ale.englishnote.model.Word;
import com.ale.englishnote.repository.WordRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface WordService {
    Word findFirstWord();

    void saveWords(List<Word> words);
}
