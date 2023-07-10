package com.ale.englishnote.dto.view;

import com.ale.englishnote.entity.RelationWord;
import com.ale.englishnote.entity.Word;
import com.ale.englishnote.util.AppException;
import com.ale.englishnote.util.MessageContent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelationWordDto {

    private Long id;
    private String relationType;
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

    private String stringType(int type){
        switch (type) {
            case 0 -> {
                return "RELATED";
            }
            case 1 -> {
                return "SYNONYM";
            }
            case 2 -> {
                return "ANTONYM";
            }
            default -> {
                throw new AppException(HttpStatus.BAD_REQUEST, MessageContent.TYPE_NOT_EXIST + type);
            }
        }
    }

    public static int intType(String type){
        switch (type) {
            case "RELATED" -> {
                return 0;
            }
            case "SYNONYM" -> {
                return 1;
            }
            case "ANTONYM" -> {
                return 2;
            }
            default -> {
                throw new AppException(HttpStatus.BAD_REQUEST, MessageContent.TYPE_NOT_EXIST + type);
            }
        }
    }
}
