package com.ale.englishnote.dto.insert;

import com.ale.englishnote.dto.view.RelationWordDto;
import com.ale.englishnote.entity.RelationWord;
import com.ale.englishnote.entity.Word;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsertRelationWord {
    @NotNull(message = "relation type id not null")
    public String relationType;
    @NotNull(message = "word id not null")
    public Long wordRelationId;

    public RelationWord toRelationWord(Word word){
        RelationWord relationWord = new RelationWord();
        relationWord.setId(0);
        relationWord.setRelationType(RelationWordDto.intType(relationType));
        relationWord.setWord(word);
        relationWord.setWordRelation(null);
        return relationWord;
    }

}
