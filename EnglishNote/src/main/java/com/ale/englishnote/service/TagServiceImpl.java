package com.ale.englishnote.service;

import com.ale.englishnote.entity.Tag;
import com.ale.englishnote.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TagServiceImpl implements TagService {
    TagRepository tagRepository;
    @Override
    public List<Tag> saveTags(Iterable<Tag> tags) {
        return tagRepository.saveAll(tags);
    }
}
