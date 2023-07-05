package com.ale.englishnote.repository;

import com.ale.englishnote.entity.Word;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
    Word findFirstByOrderByIdAsc();

    List<Word> findByEnglish(String english);

    List<Word> findByEnglishLike(String english, PageRequest pageRequest);

    List<Word> findAllByIdIn(List<Long> ids);
}
