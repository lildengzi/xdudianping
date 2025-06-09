package com.example.dianping.common;

import lombok.Data;

@Data
public class CommonResponse {
    private String msg;
    private Integer code;
    private Object data;

    public CommonResponse(Object data) {
        this.code = 1;
        this.msg = "success";
        this.data = data;
    }

    public CommonResponse(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }

    public static CommonResponse create(Object result) {
        return new CommonResponse(result);
    }

    public static CommonResponse create(String msg, Integer code) {
        return new CommonResponse(msg, code);
    }
} 