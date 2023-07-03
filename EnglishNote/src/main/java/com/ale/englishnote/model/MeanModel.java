package com.ale.englishnote.model;

import com.ale.englishnote.entity.Mean;
import com.ale.englishnote.entity.Tag;
import com.ale.englishnote.entity.Type;
import com.ale.englishnote.entity.Word;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class MeanModel {

    private long id;
    private String meanWord;
    private TypeModel type;

    public MeanModel(long id, String meanWord, Type type) {
        this.id = id;
        this.meanWord = meanWord;
        this.type = new TypeModel(type);
    }

    public MeanModel(Mean mean) {
        this.id = mean.getId();
        this.meanWord = mean.getMeanWord();
        this.type = new TypeModel(mean.getType());
    }

    public Mean toMean(Word word){
        Mean mean = new Mean();
        mean.setId(id);
        mean.setMeanWord(meanWord);
        mean.setType(type.toType());
        mean.setWord(word);
        return mean;
    }
}
