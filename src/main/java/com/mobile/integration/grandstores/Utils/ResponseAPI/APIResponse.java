package com.mobile.integration.grandstores.Utils.ResponseAPI;

import org.springframework.http.HttpStatus;

public class APIResponse {
    

    private Object data;
    private Integer status;
    private Object error;

    public APIResponse() {
        this.data = null;
        this.status = HttpStatus.OK.value();
        this.error = null;
    }


    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Object getError() {
        return error;
    }
    public void setError(Object error) {
        this.error = error;
    }


    
}