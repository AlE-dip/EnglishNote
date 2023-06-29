package com.ale.englishnote.model;

import lombok.*;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagWord {
    public int id;
    public int tagId;
    public int wordId;

}
