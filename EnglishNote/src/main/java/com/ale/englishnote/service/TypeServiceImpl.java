package com.ale.englishnote.service;

import com.ale.englishnote.dto.view.TagDto;
import com.ale.englishnote.dto.view.TypeDto;
import com.ale.englishnote.dto.view.WordTag;
import com.ale.englishnote.entity.Tag;
import com.ale.englishnote.entity.Type;
import com.ale.englishnote.entity.Word;
import com.ale.englishnote.repository.MeanRepository;
import com.ale.englishnote.repository.TypeRepository;
import com.ale.englishnote.util.AppException;
import com.ale.englishnote.util.MessageContent;
import com.ale.englishnote.util.QueryRequest;
import com.ale.englishnote.util.UtilContent;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TypeServiceImpl implements TypeService {
    private final TypeRepository typeRepository;
    private final MeanRepository meanRepository;
    @Override
    public List<Type> saveTypes(Iterable<Type> types) {
        return typeRepository.saveAll(types);
    }

    public Type findById(long id){
        return typeRepository.findById(id);
    }

    @Override
    public Type findByName(String name) {
        return typeRepository.findByName(name);
    }

    @Override
    public List<TypeDto> queryType(QueryRequest queryRequest) {
        PageRequest pageRequest = UtilContent.pageRequest(queryRequest);
        return typeRepository.findAll(pageRequest).stream()
                .map(TypeDto::newInstance)
                .toList();
    }

    @Override
    public TypeDto addType(String name) {
        Type type = typeRepository.findByName(name);
        if(type != null) {
            throw new AppException(HttpStatus.BAD_REQUEST, MessageContent.TYPE_ALREADY_EXIST);
        }
        type = new Type();
        type.setName(name);

        return TypeDto.newInstance(typeRepository.save(type));
    }

    @Override
    public boolean deleteType(Long id) {
        typeRepository.findById(id).ifPresentOrElse(type -> {
            if(meanRepository.findFirstByTypeIs(type) != null){
                throw new AppException(HttpStatus.BAD_REQUEST, MessageContent.TYPE_REFERENCED);
            }
            typeRepository.delete(type);
        }, () -> {
            throw new AppException(HttpStatus.BAD_REQUEST, MessageContent.TYPE_DOES_NOT_EXIST);
        });

        return true;
    }


}
