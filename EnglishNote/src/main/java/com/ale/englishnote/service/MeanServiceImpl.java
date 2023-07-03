package com.ale.englishnote.service;

import com.ale.englishnote.entity.Mean;
import com.ale.englishnote.repository.MeanRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MeanServiceImpl implements MeanService {
    private final MeanRepository meanRepository;
    @Override
    public void saveMeans(Iterable<Mean> means) {
        meanRepository.saveAll(means);
    }
}
