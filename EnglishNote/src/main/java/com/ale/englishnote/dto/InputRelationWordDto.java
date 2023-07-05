package com.ale.englishnote.dto;

import com.ale.englishnote.entity.RelationWord;
import com.ale.englishnote.entity.Word;
import com.ale.englishnote.util.RelationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputRelationWordDto {
    @NotNull(message = "relation type id not null")
    private String relationType;
    @NotNull(message = "word id not null")
    private Long wordRelationId;

    public RelationWord toRelationWord(Word word){
        RelationWord relationWord = new RelationWord();
        relationWord.setId(0);
        relationWord.setRelationType(intType(relationType));
        relationWord.setWord(word);
        relationWord.setWordRelation(null);
        return relationWord;
    }

    private int intType(String type){
        switch (type) {
            case "SYNONYM" -> {
                return 1;
            }
            case "ANTONYM" -> {
                return 2;
            }
            default -> {
                return 0;
            }
        }
    }
}
