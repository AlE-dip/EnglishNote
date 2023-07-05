package com.ale.englishnote.dto;

import com.ale.englishnote.entity.RelationWord;
import com.ale.englishnote.entity.Word;
import com.ale.englishnote.util.RelationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelationWordDto {

    private Long id;
    private RelationType relationType;
    private Long wordRelationId;
    private String wordRelation;

    public static RelationWordDto newInstance(RelationWord relationWord, Long currentId) {
        RelationWordDto relationWordDto = new RelationWordDto();
        relationWordDto.id = relationWord.getId();
        relationWordDto.relationType = relationWordDto.stringType(relationWord.getRelationType());
        Word word = currentId == relationWord.getWord().getId() ? relationWord.getWordRelation() : relationWord.getWord();
        relationWordDto.wordRelationId = word.getId();
        relationWordDto.wordRelation = word.getEnglish();
        return relationWordDto;
    }

    public RelationWord toRelationWord(Word word){
        RelationWord relationWord = new RelationWord();
        relationWord.setId(id);
        relationWord.setRelationType(intType(relationType));
        relationWord.setWord(word);
        Word wordRelation = new Word();
        relationWord.setWordRelation(wordRelation);
        return relationWord;
    }

    private RelationType stringType(int type){
        switch (type) {
            case 1 -> {
                return RelationType.SYNONYM;
            }
            case 2 -> {
                return RelationType.ANTONYM;
            }
            default -> {
                return RelationType.RELATED;
            }
        }
    }

    private int intType(RelationType type){
        switch (type) {
            case SYNONYM -> {
                return 1;
            }
            case ANTONYM -> {
                return 2;
            }
            default -> {
                return 0;
            }
        }
    }
}
