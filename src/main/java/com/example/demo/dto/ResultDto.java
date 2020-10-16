package com.example.demo.dto;

import com.example.demo.exception.CustomizeErrorCode;
import com.example.demo.exception.CustomizeException;
import lombok.Data;

@Data
public class ResultDto {
    private Integer code;
    private String message;


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


}
