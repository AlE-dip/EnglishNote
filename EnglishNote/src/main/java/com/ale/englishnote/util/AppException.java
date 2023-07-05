package com.ale.englishnote.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Data
@AllArgsConstructor
public class AppException extends RuntimeException{
    private HttpStatusCode status;
    private String message;
}
