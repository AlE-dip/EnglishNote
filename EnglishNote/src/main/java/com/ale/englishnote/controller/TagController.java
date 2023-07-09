package com.ale.englishnote.controller;

import com.ale.englishnote.dto.insert.InsertTag;
import com.ale.englishnote.dto.view.TagDto;
import com.ale.englishnote.dto.view.WordDto;
import com.ale.englishnote.dto.view.WordTag;
import com.ale.englishnote.service.TagService;
import com.ale.englishnote.util.MessageContent;
import com.ale.englishnote.util.QueryRequest;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tag")
@AllArgsConstructor
public class TagController {
    TagService tagService;
    @GetMapping("/query")
    public ResponseEntity queryTag(QueryRequest queryRequest){
        List<TagDto> tagDtos = tagService.queryTag(queryRequest);
        return new ResponseEntity<>(tagDtos, HttpStatus.OK);
    }

    @GetMapping("/show")
    public ResponseEntity showTag(@RequestParam String value){
        WordTag wordTag = tagService.showTag(value);
        return new ResponseEntity<>(wordTag, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addTag(@RequestBody InsertTag insertTag){
        TagDto tagDto = tagService.addTag(insertTag.name);
        return new ResponseEntity<>(tagDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTag(@PathVariable Long id){
        tagService.deleteTag(id);
        return new ResponseEntity<>(MessageContent.DELETED, HttpStatus.OK);
    }
}
