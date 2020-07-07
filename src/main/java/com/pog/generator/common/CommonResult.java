package com.pog.generator.common;


public class CommonResult<T> {

    private Integer code;
    private String message;
    private T data;

    public CommonResult(Integer code, String message, T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> CommonResult<T> success(Integer code,String message, T data){
        return new CommonResult<T>(code,message, data);
    }

    public static <T> CommonResult<T> success(String message, T data){
        return new CommonResult<T>(200,message, data);
    }

    public static <T> CommonResult<T> success(T data){
        return new CommonResult<T>(200,"操作成功", data);
    }

    public static <T> CommonResult<T> failed(Integer code, String message){
        return new CommonResult<T>(code, message, null);
    }

    public static <T> CommonResult<T> failed(String message){
        return new CommonResult<T>(500, message, null);
    }

    public static <T> CommonResult<T> failed(){
        return new CommonResult<T>(500, "操作失败", null);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
