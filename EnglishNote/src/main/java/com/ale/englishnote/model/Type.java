package com.ale.englishnote.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Type implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    public String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Type type = (Type) o;
        return id == type.id && Objects.equals(name, type.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
