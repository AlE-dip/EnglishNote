package com.ale.englishnote.service;

import com.ale.englishnote.entity.Type;
import com.ale.englishnote.repository.TypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TypeServiceImpl implements TypeService {
    private final TypeRepository typeRepository;
    @Override
    public List<Type> saveTypes(Iterable<Type> types) {
        return typeRepository.saveAll(types);
    }
}
