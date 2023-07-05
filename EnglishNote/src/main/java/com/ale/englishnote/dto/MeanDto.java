package com.ale.englishnote.dto;

import com.ale.englishnote.entity.Mean;
import com.ale.englishnote.entity.Type;
import com.ale.englishnote.entity.Word;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeanDto {

    private Long id;
    private String meanWord;
    private TypeDto type;

    public static MeanDto newInstance(Mean mean) {
        MeanDto meanDto = new MeanDto();
        meanDto.id = mean.getId();
        meanDto.meanWord = mean.getMeanWord();
        meanDto.type = TypeDto.newInstance(mean.getType());
        return meanDto;
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
