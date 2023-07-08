package com.ale.englishnote.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
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

    @Transient
    private long wordId;

    @ManyToOne
    @JoinColumn(name = "word")
    private Word word;

    @Transient
    private long relationWordId;

    @ManyToOne
    @JoinColumn(name = "word_relation")
    private Word wordRelation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelationWord that = (RelationWord) o;
        return word.getId() == that.word.getId() && wordRelation.getId() == that.wordRelation.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(word.getId(), wordRelation.getId());
    }
}
