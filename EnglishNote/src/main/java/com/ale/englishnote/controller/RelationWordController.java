package com.ale.englishnote.controller;

import com.ale.englishnote.dto.view.RelationWordDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relation")
public class RelationWordController {
    @PostMapping
    public ResponseEntity newRelationWord(@Validated @RequestBody RelationWordDto relationWordDto){
        return new ResponseEntity(relationWordDto, HttpStatus.OK);
    }
}
