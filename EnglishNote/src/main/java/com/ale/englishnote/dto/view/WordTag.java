package com.ale.englishnote.dto.view;

import com.ale.englishnote.entity.Tag;
import com.ale.englishnote.entity.Word;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WordTag extends TagDto{
    public List<WordDto> words;

    public static WordTag newInstance(Tag tag, List<Word> words){
        WordTag wordTag = new WordTag();
        wordTag.id = tag.getId();
        wordTag.name = tag.getName();
        wordTag.words = words.stream().map(WordDto::newInstance).toList();
        return wordTag;
    }
}
