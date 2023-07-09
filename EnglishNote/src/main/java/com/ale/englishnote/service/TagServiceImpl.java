package com.ale.englishnote.service;

import com.ale.englishnote.dto.view.TagDto;
import com.ale.englishnote.dto.view.WordDto;
import com.ale.englishnote.dto.view.WordTag;
import com.ale.englishnote.entity.Tag;
import com.ale.englishnote.entity.Word;
import com.ale.englishnote.repository.TagRepository;
import com.ale.englishnote.repository.WordRepository;
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
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final WordRepository wordRepository;
    @Override
    public List<Tag> saveTags(Iterable<Tag> tags) {
        return tagRepository.saveAll(tags);
    }

    @Override
    public List<Tag> findAllByIds(List<Long> ids) {
        return tagRepository.findAllByIdIn(ids);
    }

    @Override
    public List<TagDto> queryTag(QueryRequest queryRequest) {
        PageRequest pageRequest = UtilContent.pageRequest(queryRequest);
        return tagRepository.findAll(pageRequest).stream()
                .map(TagDto::newInstance)
                .toList();
    }

    @Override
    public WordTag showTag(String value) {
        Tag tag = tagRepository.findByName(value);
        List<Word> words = wordRepository.findAllByTagsIs(tag);
        WordTag wordTag = WordTag.newInstance(tag, words);
        return wordTag;
    }

    @Override
    public TagDto addTag(String name) {
        Tag tag = tagRepository.findByName(name);
        if(tag != null) {
            throw new AppException(HttpStatus.BAD_REQUEST, MessageContent.TAG_ALREADY_EXIST);
        }
        tag = new Tag();
        tag.setName(name);

        return TagDto.newInstance(tagRepository.save(tag));
    }

    @Override
    public boolean deleteTag(Long id) {
        tagRepository.findById(id).ifPresentOrElse(tag -> {
            if(wordRepository.findFirstByTagsIs(tag) != null){
                throw new AppException(HttpStatus.BAD_REQUEST, MessageContent.TAG_REFERENCED);
            }
            tagRepository.delete(tag);
        }, () -> {
            throw new AppException(HttpStatus.BAD_REQUEST, MessageContent.TAG_DOES_NOT_EXIST);
        });

        return true;
    }
}
