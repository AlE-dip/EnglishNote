package com.ale.englishnote.service;

import com.ale.englishnote.entity.Tag;

import java.util.List;

public interface TagService {
    List<Tag> saveTags(Iterable<Tag> tags);
}
