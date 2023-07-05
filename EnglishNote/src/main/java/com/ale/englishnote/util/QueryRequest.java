package com.ale.englishnote.util;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.web.bind.annotation.RequestParam;

@Data
@AllArgsConstructor
public class QueryRequest {
    private Integer page;
    private Integer size;
    private String sortBy;
    private String sortType;
}
