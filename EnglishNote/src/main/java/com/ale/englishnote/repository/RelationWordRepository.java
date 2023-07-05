package com.ale.englishnote.repository;

import com.ale.englishnote.entity.RelationWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RelationWordRepository extends JpaRepository<RelationWord, Long> {
    Optional<RelationWord> findAllById(Long id);
}
