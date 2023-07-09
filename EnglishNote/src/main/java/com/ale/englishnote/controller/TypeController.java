package com.ale.englishnote.controller;

import com.ale.englishnote.dto.insert.InsertTag;
import com.ale.englishnote.dto.view.TagDto;
import com.ale.englishnote.dto.view.TypeDto;
import com.ale.englishnote.dto.view.WordDto;
import com.ale.englishnote.dto.view.WordTag;
import com.ale.englishnote.service.TagService;
import com.ale.englishnote.service.TypeService;
import com.ale.englishnote.util.MessageContent;
import com.ale.englishnote.util.QueryRequest;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("type")
@AllArgsConstructor
public class TypeController {
    TypeService typeService;
    @GetMapping("/query")
    public ResponseEntity queryType(QueryRequest queryRequest){
        List<TypeDto> typeDtos = typeService.queryType(queryRequest);
        return new ResponseEntity<>(typeDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addType(@RequestBody InsertTag insertTag){
        TypeDto typeDto = typeService.addType(insertTag.name);
        return new ResponseEntity<>(typeDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteType(@PathVariable Long id){
        typeService.deleteType(id);
        return new ResponseEntity<>(MessageContent.DELETED, HttpStatus.OK);
    }
}
