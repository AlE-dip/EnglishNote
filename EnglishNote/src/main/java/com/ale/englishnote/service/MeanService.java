package com.ale.englishnote.service;

import com.ale.englishnote.dto.InputMeanDto;
import com.ale.englishnote.entity.Mean;
import com.ale.englishnote.entity.Word;

import java.util.List;

public interface MeanService {
    void saveMeans(Iterable<Mean> means);

    List<Mean> insertMeans(List<InputMeanDto> inputMeanDtos, Word word);
}
