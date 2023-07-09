package com.ale.englishnote.controller;

import com.ale.englishnote.dto.insert.InsertWord;
import com.ale.englishnote.dto.view.WordDto;
import com.ale.englishnote.service.WordService;
import com.ale.englishnote.util.MessageContent;
import com.ale.englishnote.util.QueryRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/word")
public class WordController {
    private final WordService wordService;

    @GetMapping("/query")
    public ResponseEntity queryWord(QueryRequest queryRequest) {
        List<WordDto> wordDtos = wordService.queryWord(queryRequest);
        return new ResponseEntity<>(wordDtos, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity searchWord(
            @RequestParam String value,
            QueryRequest queryRequest
    ) {
        List<WordDto> wordDtos = wordService.searchWord(value, queryRequest);
        return new ResponseEntity<>(wordDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity insertWords(@Valid @RequestBody InsertWord insertWord) {
        WordDto newWordDto = wordService.insertWord(insertWord);
        return new ResponseEntity<>(newWordDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateWords(@Valid @RequestBody InsertWord insertWord, @PathVariable Long id) {
        WordDto newWordDto = wordService.updateWord(insertWord, id);
        return new ResponseEntity<>(newWordDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteWords(@PathVariable Long id) {
        wordService.deleteWord(id);
        return new ResponseEntity<>(MessageContent.DELETED, HttpStatus.OK);
    }

}
