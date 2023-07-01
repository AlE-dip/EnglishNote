package com.ale.englishnote.repository;

import com.ale.englishnote.entity.RelationWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationWordRepository extends JpaRepository<RelationWord, Long> {
}
