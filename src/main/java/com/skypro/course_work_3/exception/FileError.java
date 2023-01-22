package com.skypro.course_work_3.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class FileError extends RuntimeException {
    public FileError(String message) {

    }
}
