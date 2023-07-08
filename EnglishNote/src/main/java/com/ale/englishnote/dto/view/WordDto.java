package com.ale.englishnote.dto.view;

import com.ale.englishnote.entity.Word;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WordDto {
    private Long id;
    private String english;
    private String date;

    private Integer notification; //0 khong dat thong bao //1 dat thong bao
    private Integer auto;
    private Integer game;
    private Integer forget;

    private List<MeanDto> means;

    private List<TagDto> tags;

    private List<RelationWordDto> relationWords;

    public static WordDto newInstance(Word word) {
        WordDto wordDto = new WordDto();
        wordDto.id = word.getId();
        wordDto.english = word.getEnglish();
        wordDto.date = word.getDate();
        wordDto.notification = word.getNotification();
        wordDto.auto = word.getAuto();
        wordDto.game = word.getGame();
        wordDto.forget = word.getForget();
        if(word.getMeans() != null) {
            wordDto.means = word.getMeans().stream().map(MeanDto::newInstance).toList();
        }
        if(word.getTags() != null) {
            wordDto.tags = word.getTags().stream().map(TagDto::newInstance).toList();
        }
        if(word.getRelationWords() != null) {
            wordDto.relationWords = word.getRelationWords().stream().map(r -> RelationWordDto.newInstance(r, word.getId())).toList();
        }
        return wordDto;
    }

    public Word toWord(){
        Word word = new Word();
        word.setId(id);
        word.setEnglish(english);
        word.setDate(date);
        word.setNotification(notification);
        word.setAuto(auto);
        word.setGame(game);
        word.setForget(forget);
        if(means != null) {
            word.setMeans(means.stream().map(m -> m.toMean(word)).toList());
        }
        if(tags != null) {
            word.setTags(tags.stream().map(TagDto::toTag).toList());
        }
        if(relationWords != null) {
            word.setRelationWords(relationWords.stream().map(r -> r.toRelationWord(word)).toList());
        }
        return word;
    }


}
