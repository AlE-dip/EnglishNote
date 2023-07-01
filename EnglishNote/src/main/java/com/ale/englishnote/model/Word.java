package com.ale.englishnote.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.sql.ast.tree.expression.Collation;
import org.springframework.context.annotation.Primary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Word implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String english;
    private String date;

    private int notification; //0 khong dat thong bao //1 dat thong bao
    private int auto;
    private int game;
    private int forget;

    @OneToMany(mappedBy = "word")
    private List<Mean> means;

    @OneToMany
    private ArrayList<Tag> tags;

    @OneToMany(mappedBy = "word")
    private ArrayList<RelationWord> relationWords;

    @OneToOne(mappedBy = "wordRelation", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private RelationWord relationWord;
}