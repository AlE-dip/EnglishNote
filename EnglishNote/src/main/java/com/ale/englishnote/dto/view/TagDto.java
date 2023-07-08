package com.ale.englishnote.dto.view;

import com.ale.englishnote.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagDto {
    private Long id;
    private String name;

    public static TagDto newInstance(Tag tag) {
        TagDto tagDto = new TagDto();
        tagDto.id = tag.getId();
        tagDto.name = tag.getName();
        return tagDto;
    }

    public Tag toTag(){
        return new Tag(id, name);
    }
}
