package com.skypro.course_work_3.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class AddingError extends RuntimeException {
    public AddingError(String message) {
    }
}
