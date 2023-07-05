package com.ale.englishnote.util;

import jakarta.annotation.Nullable;
import lombok.NonNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;

import java.util.Objects;

public class UtilContent {
    public static final String DESC = "DESC";
    public static final String ASC = "ASC";


    public static PageRequest pageRequest(QueryRequest queryRequest) {
        PageRequest pageRequest = PageRequest.of(queryRequest.getPage() - 1, queryRequest.getSize());
        Sort.Direction direction;
        if(queryRequest.getSortType() == null || queryRequest.getSortType().equals(ASC)) {
            direction = Sort.Direction.ASC;
        } else if (queryRequest.getSortType().equals(DESC)) {
            direction = Sort.Direction.DESC;
        } else {
            throw new AppException(HttpStatus.BAD_REQUEST, MessageContent.BAD_QUERY);
        }

        if(queryRequest.getSortBy() != null && !queryRequest.getSortBy().isEmpty()){
            pageRequest = pageRequest.withSort(direction, queryRequest.getSortBy());
        } else {
            pageRequest = pageRequest.withSort(direction, "id");
        }
        return pageRequest;
    }

}
