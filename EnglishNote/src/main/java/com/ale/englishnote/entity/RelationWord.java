package com.ale.englishnote.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RelationWord implements Serializable {
    public static final int RELATED = 0;
    public static final int SYNONYM = 1;
    public static final int ANTONYM = 2;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int relationType;

//    @Column(insertable=false, updatable=false)
    private long wordId;

    @ManyToOne
    @JoinColumn(name = "word")
    private Word word;

//    @Column(insertable=false, updatable=false)
    private long relationWordId;

    @ManyToOne
    @JoinColumn(name = "word_relation")
    private Word wordRelation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelationWord that = (RelationWord) o;
        return wordId == that.wordId && relationWordId == that.relationWordId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(wordId, relationWordId);
    }
}
