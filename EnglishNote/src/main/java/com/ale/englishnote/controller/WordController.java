package com.ale.englishnote.controller;

import com.ale.englishnote.entity.Word;
import com.ale.englishnote.model.WordModel;
import com.ale.englishnote.service.WordService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@AllArgsConstructor
public class WordController {
    private final WordService wordService;

    @GetMapping("/words")
    public ResponseEntity allWord() {
        List<WordModel> wordModels = wordService.findAll();
        return new ResponseEntity<>(wordModels, HttpStatus.OK);
    }

    @GetMapping("/find/{word}")
    public ResponseEntity findWord(@PathVariable String word) {
        List<WordModel> wordModels = wordService.findWord(word);
        return new ResponseEntity<>(wordModels, HttpStatus.OK);
    }

    @GetMapping("/filter/{word}")
    public ResponseEntity filterWord(@PathVariable String word) {
        List<WordModel> wordModels = wordService.filterWord(word);
        return new ResponseEntity<>(wordModels, HttpStatus.OK);
    }

//    @GetMapping("/insert")
//    public ResponseEntity insertWords(List<WordModel> wordModels) {
////        List<WordModel> newWordModels = wordService.saveWords(wordModels);
//        return new ResponseEntity<>(newWordModels, HttpStatus.OK);
//    }

}
