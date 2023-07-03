package com.ale.englishnote.model;

import com.ale.englishnote.entity.Type;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class TypeModel {
    private long id;
    private String name;

    public TypeModel(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public TypeModel(Type type) {
        this.id = type.getId();
        this.name = type.getName();
    }

    public Type toType(){
        return new Type(id, name);
    }

}
