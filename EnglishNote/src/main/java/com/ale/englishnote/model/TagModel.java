package com.ale.englishnote.model;

import com.ale.englishnote.entity.Tag;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class TagModel {
    private long id;
    private String name;

    public TagModel(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public TagModel(Tag tag) {
        this.id = tag.getId();
        this.name = tag.getName();
    }

    public Tag toTag(){
        return new Tag(id, name);
    }
}
