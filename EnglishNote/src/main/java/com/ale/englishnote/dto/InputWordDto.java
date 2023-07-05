package com.ale.englishnote.dto;

import com.ale.englishnote.entity.Tag;
import com.ale.englishnote.entity.Type;
import com.ale.englishnote.entity.Word;
import com.ale.englishnote.util.AppException;
import com.ale.englishnote.util.MessageContent;
import com.ale.englishnote.util.ValidField;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.http.HttpStatus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputWordDto {
    @NotBlank(message = "english not blank")
    private String english;
    @Valid
    @NotEmpty(message = "means not empty")
    private List<InputMeanDto> means;
    private List<Long> tags;
    @Valid
    private List<InputRelationWordDto> relationWords;

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
