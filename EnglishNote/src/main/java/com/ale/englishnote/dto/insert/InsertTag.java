package com.ale.englishnote.dto.insert;

import com.ale.englishnote.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsertTag {
    public String name;

    public Tag toTag(){
        return new Tag(0, name);
    }
}
