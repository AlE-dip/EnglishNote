package com.ale.englishnote.dto;

import com.ale.englishnote.entity.Mean;
import com.ale.englishnote.entity.Type;
import com.ale.englishnote.entity.Word;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputMeanDto {
    @NotBlank(message = "mean not blank")
    private String meanWord;
    private Long typeId;

    public Mean toMean(Word word){
        Mean mean = new Mean();
        mean.setId(0);
        mean.setMeanWord(meanWord);
        mean.setWord(word);
        return mean;
    }
}
