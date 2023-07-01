package com.ale.englishnote.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RelationWord implements Serializable {
    public static final int RELATED = 0;
    public static final int SYNONYM = 1;
    public static final int ANTONYM = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    public int relationType;

    @Column(insertable=false, updatable=false)
    public long wordId;
    @ManyToOne
    @JoinColumn(name = "word")
    private Word word;

    @Column(insertable=false, updatable=false)
    public long relationWordId;
    @OneToOne
    @MapsId
    @JoinColumn(name = "word_relation")
    private Word wordRelation;
}
