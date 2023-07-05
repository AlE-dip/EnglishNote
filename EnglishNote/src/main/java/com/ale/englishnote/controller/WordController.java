package com.ale.englishnote.controller;

import com.ale.englishnote.dto.InputWordDto;
import com.ale.englishnote.dto.WordDto;
import com.ale.englishnote.service.WordService;
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
    public ResponseEntity insertWords(@Valid @RequestBody InputWordDto inputWordDto) {
        WordDto newWordDto = wordService.insertWord(inputWordDto);
        return new ResponseEntity<>(newWordDto, HttpStatus.OK);
    }

}
