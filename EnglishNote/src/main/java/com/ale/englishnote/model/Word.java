package com.ale.englishnote.model;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Word implements Serializable {
    public int id;
    public String english;
    public ArrayList<Mean> means;
    public String date;
    public int notification;
    //0 khong dat thong bao
    //1 dat thong bao
    public int auto;
    public int game;
    public ArrayList<Tag> tags;
    public ArrayList<RelationWord> relationWords;
    public int forget;

}