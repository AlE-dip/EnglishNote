package com.ale.englishnote.model;

import com.ale.englishnote.entity.Mean;
import com.ale.englishnote.entity.RelationWord;
import com.ale.englishnote.entity.Tag;
import com.ale.englishnote.entity.Word;
import jakarta.annotation.Nullable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Setter;

import java.util.List;

@Data
public class WordModel {
    private long id;
    private String english;
    private String date;

    private int notification; //0 khong dat thong bao //1 dat thong bao
    private int auto;
    private int game;
    private int forget;

    private List<MeanModel> means;

    private List<TagModel> tags;

    private List<RelationWordModel> relationWords;

    public WordModel(long id, String english, String date, int notification, int auto, int game, int forget, List<MeanModel> means, List<TagModel> tags, List<RelationWordModel> relationWords) {
        this.id = id;
        this.english = english;
        this.date = date;
        this.notification = notification;
        this.auto = auto;
        this.game = game;
        this.forget = forget;
        this.means = means;
        this.tags = tags;
        this.relationWords = relationWords;
    }

    public WordModel(Word word) {
        this.id = word.getId();
        this.english = word.getEnglish();
        this.date = word.getDate();
        this.notification = word.getNotification();
        this.auto = word.getAuto();
        this.game = word.getGame();
        this.forget = word.getForget();
        this.means = word.getMeans().stream().map(MeanModel::new).toList();
        this.tags = word.getTags().stream().map(TagModel::new).toList();
        this.relationWords = word.getRelationWords().stream().map(r -> new RelationWordModel(r, word.getId())).toList();
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
        word.setMeans(means.stream().map(m -> m.toMean(word)).toList());
        word.setTags(tags.stream().map(TagModel::toTag).toList());
        word.setRelationWords(relationWords.stream().map(r -> r.toRelationWord(word)).toList());
        return word;
    }


}
