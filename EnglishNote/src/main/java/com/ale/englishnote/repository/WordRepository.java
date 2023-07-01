package com.ale.englishnote.repository;

import com.ale.englishnote.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
    Word findFirstByOrderByIdAsc();
}
