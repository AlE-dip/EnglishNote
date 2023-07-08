package com.ale.englishnote.repository;

import com.ale.englishnote.entity.RelationWord;
import com.ale.englishnote.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RelationWordRepository extends JpaRepository<RelationWord, Long> {
    Optional<RelationWord> findAllById(Long id);

    @Query("select r, r.word, r.wordRelation from RelationWord r join Word w on w.id = r.word.id or w.id = r.wordRelation.id where w.id = :wordId")
    List<RelationWord> getRelationWordByWordId(Long wordId);
}
