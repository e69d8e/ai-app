package com.li.handler;

import com.li.common.constant.Constant;
import com.li.common.exception.SessionException;
import com.li.common.exception.UserException;
import com.li.common.result.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserException.class)
    public ResponseEntity<Result> handleUserException(UserException e) {
        return ResponseEntity.status(401).body(Result.fail(Constant.USER_EXCEPTION));
    }

    @ExceptionHandler(SessionException.class)
    public ResponseEntity<Result> handleSessionException(SessionException e) {
        return ResponseEntity.status(501).body(Result.fail(Constant.SESSION_EXCEPTION));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result> handleException(Exception e) {
        return ResponseEntity.status(500).body(Result.fail(Constant.SERVER_EXCEPTION));
    }
}
