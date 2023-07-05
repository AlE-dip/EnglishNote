package com.ale.englishnote.repository;

import com.ale.englishnote.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
    Type findById(long id);

    Type findByName(String name);
}
