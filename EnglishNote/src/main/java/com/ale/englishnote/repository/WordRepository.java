package com.ale.englishnote.repository;

import com.ale.englishnote.entity.Word;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
    Word findFirstByOrderByIdAsc();
    List<Word> findByEnglish(String english);
    List<Word> findAllByIdIn(List<Long> ids);
    @Query(value = "select w from Word w join Mean m on w.id = m.word.id where w.english like %:value% or m.meanWord like %:value%")
    List<Word> searchWord(String value, PageRequest pageRequest);
}
