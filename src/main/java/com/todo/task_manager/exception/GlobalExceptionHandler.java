package com.todo.task_manager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
@ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRunTimeException(RuntimeException ex, WebRequest request) {
    ErrorResponse error = new ErrorResponse(500, "Internal Server Error", ex.getMessage(), request.getDescription(false));

    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
}
@ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex,WebRequest request){
    ErrorResponse error=new ErrorResponse(400,"Bad Request",ex.getMessage(),request.getDescription(false));
    return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
}
}
