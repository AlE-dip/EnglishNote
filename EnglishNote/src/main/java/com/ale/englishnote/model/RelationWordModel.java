package com.ale.englishnote.model;

import com.ale.englishnote.entity.RelationWord;
import com.ale.englishnote.entity.Tag;
import com.ale.englishnote.entity.Word;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class RelationWordModel {
    public static final String RELATED = "RELATED";
    public static final String SYNONYM = "SYNONYM";
    public static final String ANTONYM = "ANTONYM";
    private long id;
    private String relationType;
    private long wordRelationId;
    private String wordRelation;

    public RelationWordModel(long id, String relationType, long wordRelationId, String wordRelation) {
        this.id = id;
        this.relationType = relationType;
        this.wordRelationId = wordRelationId;
        this.wordRelation = wordRelation;
    }

    public RelationWordModel(RelationWord relationWord, long currentId) {
        this.id = relationWord.getId();
        this.relationType = stringType(relationWord.getRelationType());
        Word word = currentId == relationWord.getWord().getId() ? relationWord.getWordRelation() : relationWord.getWord();
        this.wordRelationId = word.getId();
        this.wordRelation = word.getEnglish();
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

    public String stringType(int type){
        switch (type) {
            case 0 -> {
                return RELATED;
            }
            case 1 -> {
                return SYNONYM;
            }
            case 2 -> {
                return ANTONYM;
            }
            default -> {
                return "";
            }
        }
    }

    public int intType(String type){
        switch (type) {
            case RELATED -> {
                return 0;
            }
            case SYNONYM -> {
                return 1;
            }
            case ANTONYM -> {
                return 2;
            }
            default -> {
                return -1;
            }
        }
    }
}
