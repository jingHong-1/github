package com.example.demo.dto;

import com.example.demo.exception.CustomizeErrorCode;
import com.example.demo.exception.CustomizeException;
import lombok.Data;

@Data
public class ResultDto<T> {
    private Integer code;
    private String message;
    private T data;


    public static ResultDto erroeOf(Integer code,String message){
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(code);
        resultDto.setMessage(message);
        return resultDto;
    }
    public static ResultDto erroeOf(CustomizeException e) {

        return erroeOf(e.getCode(),e.getMessage());
    }

    public static ResultDto erroeOf(CustomizeErrorCode errorCode) {
        return erroeOf(errorCode.getCode(),errorCode.getMessage());
    }
    public static  ResultDto okOf(){
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(200);
        resultDto.setMessage("请求成功");
        return resultDto;
    }
    //封装一个List，用于返回前端
    public static <T> ResultDto okOf(T t){
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(200);
        resultDto.setMessage("请求成功");
        resultDto.setData(t);
        return resultDto;
    }


}
