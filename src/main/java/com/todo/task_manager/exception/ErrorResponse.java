package com.todo.task_manager.exception;

import java.time.LocalDate;

public class ErrorResponse {
    private LocalDate timeStamp;
    private int status;
    private String error;
    private String message;
    private String path;

    public ErrorResponse(int status,String error,String message,String path){
        this.timeStamp=LocalDate.now();
        this.status=status;
        this.error=error;
        this.message=message;
        this.path=path;
    }

    public LocalDate getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDate timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
