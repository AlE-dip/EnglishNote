package com.ale.englishnote.model;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RelationWord implements Serializable {
    public static final int RELATED = 0;
    public static final int SYNONYM = 1;
    public static final int ANTONYM = 2;

    public int id;
    public int wordId;
    public int relationWordId;
    public int relationType;

}
