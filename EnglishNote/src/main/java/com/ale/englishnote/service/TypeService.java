package com.ale.englishnote.service;

import com.ale.englishnote.entity.Type;

import java.util.List;

public interface TypeService {
    List<Type> saveTypes(Iterable<Type> types);
}
