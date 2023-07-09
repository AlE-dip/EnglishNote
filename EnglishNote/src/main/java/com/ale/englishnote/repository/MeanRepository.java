package com.ale.englishnote.repository;

import com.ale.englishnote.entity.Mean;
import com.ale.englishnote.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeanRepository extends JpaRepository<Mean, Long> {
    Mean findFirstByTypeIs(Type type);
}
