package com.ale.englishnote.dto.view;

import com.ale.englishnote.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeDto {
    private Long id;
    private String name;

    public static TypeDto newInstance(Type type) {
        TypeDto typeDto = new TypeDto();
        typeDto.id = type.getId();
        typeDto.name = type.getName();
        return typeDto;
    }

    public Type toType(){
        return new Type(id, name);
    }

}
