package com.ale.englishnote.dto.insert;

import com.ale.englishnote.entity.Mean;
import com.ale.englishnote.entity.Word;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsertMean {
    @NotBlank(message = "mean not blank")
    public String meanWord;
    public Long typeId;

    public Mean toMean(Word word){
        Mean mean = new Mean();
        mean.setId(0);
        mean.setMeanWord(meanWord);
        mean.setWord(word);
        return mean;
    }
}
