package com.ale.englishnote.model;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mean implements Serializable {
    public int id;
    public Type type;
    public String meanWord;
    public int wordId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mean mean = (Mean) o;
        return id == mean.id && wordId == mean.wordId && Objects.equals(type, mean.type) && Objects.equals(meanWord, mean.meanWord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, meanWord, wordId);
    }
}
