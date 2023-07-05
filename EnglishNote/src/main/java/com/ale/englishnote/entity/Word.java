package com.ale.englishnote.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Word implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String english;
    private String date;

    private int notification = 0; //0 don't set notification; 1 set notification; default 0
    private int auto = 1; //0 don't set notification; 1 set notification; default 1
    private int game = 1; //default 1
    private int forget = 0; //default 0

    @OneToMany(mappedBy = "word")
    private List<Mean> means;

    @ManyToMany
    private List<Tag> tags;

    @ManyToMany(cascade = {CascadeType.ALL})
    private List<RelationWord> relationWords;

}