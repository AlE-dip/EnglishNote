package com.ale.englishnote.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mean implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    public String meanWord;
    public long wordId;

    @ManyToOne
    @JoinColumn(name = "type")
    public Type type;

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
        return Objects.hash(id, type, meanWord, word.getId());
    }
}
