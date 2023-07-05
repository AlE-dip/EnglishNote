package com.ale.englishnote.service;

import com.ale.englishnote.entity.Tag;
import com.ale.englishnote.entity.Type;

import java.util.List;

public interface TagService {
    List<Tag> saveTags(Iterable<Tag> tags);

    List<Tag> findAllByIds(List<Long> ids);


}
