package com.nhn.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespondObject {

    private String status;
    private String message;
    private Object data;

    public RespondObject() {
    }

    public RespondObject(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
