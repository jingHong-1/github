package com.example.demo.exception;

public enum  CustomizeErrorCode implements ICustomizeErrorCode{


    QUESTION_NOT_FOUND(2001,"你找的问题不存在，换个试试?"),
    TAGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论进行回复"),
    NO_LOGIN(2003,"当前操作需要登录，请登录后重试"),
    SYS_ERROR(2004,"服务器冒烟了，要不然你稍后再试试！！！"),
    TYPE_PARAM_WRONG(2005,"评论不存在或类型错误"),
    COMMENT_NOT_FOUND(2006,"回复的评论不存在，要不换个试试"),
    CONTENT_IS_EMPTY(2007,"输入内容不能为空")
    ;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage(){
        return message;
    }
    private Integer code;
    private String message;


    CustomizeErrorCode(Integer code,String message) {
        this.message = message;
        this.code = code;
    }

    CustomizeErrorCode(String message){
        this.message=message;
    }
}
