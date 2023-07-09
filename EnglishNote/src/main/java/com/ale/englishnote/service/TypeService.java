package com.ale.englishnote.service;

import com.ale.englishnote.dto.view.TagDto;
import com.ale.englishnote.dto.view.TypeDto;
import com.ale.englishnote.dto.view.WordTag;
import com.ale.englishnote.entity.Type;
import com.ale.englishnote.util.QueryRequest;

import java.util.List;

public interface TypeService {
    List<Type> saveTypes(Iterable<Type> types);
    Type findById(long id);
    Type findByName(String name);

    List<TypeDto> queryType(QueryRequest queryRequest);

    TypeDto addType(String name);

    boolean deleteType(Long id);
}
