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
public class Mean implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String meanWord;

    @Transient
    private long wordId;

    @ManyToOne
    @JoinColumn(name = "type")
    private Type type;

    @ManyToOne
    @JoinColumn(name = "word")
    private Word word;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mean mean = (Mean) o;
        return id == mean.id && word.getId() == mean.word.getId() && Objects.equals(type, mean.type) && Objects.equals(meanWord, mean.meanWord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
