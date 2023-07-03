package com.ale.englishnote.service;

import com.ale.englishnote.entity.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class FileService {

    private final WordService wordService;
    private final TypeService typeService;
    private final MeanService meanService;
    private final TagService tagService;
    private final RelationWordService relationWordService;


    public void importDefault() {
        if(wordService.findFirstWord() != null) return;
        try {
            File file = new ClassPathResource("/static/english.json").getFile();
            ObjectMapper mapper = new ObjectMapper();
            ArrayList<Word> words = mapper.readValue(file, new TypeReference<>(){});

            Set<Type> typeSet = new HashSet<>();
            Set<Mean> meanSet = new HashSet<>();
            Set<Tag> tagSet = new HashSet<>();
            Set<RelationWord> relationWordSet  = new HashSet<>();
            words.forEach(word -> {
                word.getMeans().forEach(mean -> {
                    mean.setWord(word);
                    meanSet.add(mean);
                });
                word.getMeans().clear();

                tagSet.addAll(word.getTags());

                if(word.getRelationWords() != null){
                    relationWordSet.addAll(word.getRelationWords());
                    word.getRelationWords().clear();
                }
            });
            meanSet.forEach(mean -> {
                mean.getType().setId(0);
                typeSet.add(mean.getType());
            });
            List<Type> types = typeService.saveTypes(typeSet);

            tagSet.forEach(tag -> tag.setId(0));
            List<Tag> tags = tagService.saveTags(tagSet);

            relationWordSet.forEach(relationWord -> {
                relationWord.setId(0);
                words.stream()
                        .filter(word -> word.getId() == relationWord.getWordId() ||
                                word.getId() == relationWord.getRelationWordId()
                        )
                        .toList()
                        .forEach(word -> {
                            if(word.getId() == relationWord.getWordId()){
                                relationWord.setWord(word);
                            } else {
                                relationWord.setWordRelation(word);
                            }
                        });
            });

            //Update Id
            words.forEach(word -> {
                word.setId(0);
                if(word.getTags() != null){
                    word.getTags().forEach(tag -> {
                        tags.stream()
                                .filter(tag::equals)
                                .findFirst()
                                .ifPresent(t -> tag.setId(t.getId()));
                    });
                }
            });
            wordService.saveWords(words);
            relationWordService.saveRelationWords(relationWordSet);
            relationWordSet.forEach(relationWord -> {
                relationWord.getWord().getRelationWords().add(relationWord);
                relationWord.getWordRelation().getRelationWords().add(relationWord);
            });
//            wordService.saveWords(
//                    words.stream()
//                            .filter(word -> word.getRelationWords() != null)
//                            .toList()
//            );
            words.stream()
                    .filter(word -> word.getRelationWords() != null)
                    .toList()
                    .forEach(word -> {
                              wordService.saveWord(word);
                    });

            meanSet.forEach(mean -> {
                types.stream()
                        .filter(mean.getType()::equals)
                        .findFirst()
                        .ifPresent(type -> mean.getType().setId(type.getId()));
            });
            meanService.saveMeans(meanSet);

            return;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
