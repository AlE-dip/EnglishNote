package com.ale.englishnote.service;

import com.ale.englishnote.dto.insert.InsertMean;
import com.ale.englishnote.entity.Mean;
import com.ale.englishnote.entity.Type;
import com.ale.englishnote.entity.Word;
import com.ale.englishnote.repository.MeanRepository;
import com.ale.englishnote.util.AppException;
import com.ale.englishnote.util.MessageContent;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MeanServiceImpl implements MeanService {
    private final MeanRepository meanRepository;
    private final TypeService typeService;
    @Override
    public void saveMeans(Iterable<Mean> means) {
        meanRepository.saveAll(means);
    }

    @Override
    public List<Mean> insertMeans(List<InsertMean> insertMean, Word word) {
        List<Mean> means = new ArrayList<>();
        insertMean.forEach(inputMeanDto -> {
            Type type;
            if(inputMeanDto.getTypeId() != null){
                type = typeService.findById(inputMeanDto.getTypeId());
            } else {
                type = typeService.findByName(null);
            }
            if (type == null) {
                throw new AppException(HttpStatus.BAD_REQUEST, MessageContent.ID_NOT_EXIST + Type.class.getTypeName());
            }
            Mean mean = inputMeanDto.toMean(word);
            mean.setType(type);
            means.add(mean);
        });
        return meanRepository.saveAll(means);
    }

    @Override
    public void deleteMeans(List<Mean> means) {
        meanRepository.deleteAll(means);
    }
}
