package com.ale.englishnote.util;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public class ValidField {
    public static <T> void validField(Class<T> cl, Object... obj){
        Arrays.stream(obj).toList().forEach(o -> {
            if(o == null){
                throw new AppException(HttpStatus.BAD_REQUEST, MessageContent.DATA_INVALID + cl.getName());
            }
        });
    }
}
