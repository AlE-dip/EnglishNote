package com.ale.englishnote.dto.insert;

import com.ale.englishnote.entity.Word;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsertWord {
    @NotBlank(message = "english not blank")
    public String english;
    @Valid
    @NotEmpty(message = "means not empty")
    public List<InsertMean> means;
    public List<Long> tags;
    @Valid
    public List<InsertRelationWord> relationWords;

    public Word toWord() {
        Word word = new Word();
        word.setId(0);
        word.setEnglish(english);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        word.setDate(formatter.format(new Date()));
        word.setNotification(0);
        word.setAuto(1);
        word.setGame(1);
        word.setForget(0);
        word.setMeans(new ArrayList<>());
        word.setTags(new ArrayList<>());
        word.setRelationWords(new ArrayList<>());
        return word;
    }

}
