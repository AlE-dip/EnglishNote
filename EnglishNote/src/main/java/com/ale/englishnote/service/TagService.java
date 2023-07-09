package com.ale.englishnote.service;

import com.ale.englishnote.dto.view.TagDto;
import com.ale.englishnote.dto.view.WordTag;
import com.ale.englishnote.entity.Tag;
import com.ale.englishnote.util.QueryRequest;

import java.util.List;

public interface TagService {
    List<Tag> saveTags(Iterable<Tag> tags);

    List<Tag> findAllByIds(List<Long> ids);

    List<TagDto> queryTag(QueryRequest queryRequest);

    WordTag showTag(String value);

    TagDto addTag(String name);

    boolean deleteTag(Long id);
}
