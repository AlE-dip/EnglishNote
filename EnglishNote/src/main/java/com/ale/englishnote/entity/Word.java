package com.ale.englishnote.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Word implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String english;
    private String date;

    private int notification; //0 khong dat thong bao //1 dat thong bao
    private int auto;
    private int game;
    private int forget;

    @OneToMany(mappedBy = "word")
    private List<Mean> means;

    @ManyToMany
    private List<Tag> tags;

    @ManyToMany
    private List<RelationWord> relationWords;
}