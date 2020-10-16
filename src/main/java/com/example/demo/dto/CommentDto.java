package com.example.demo.dto;

import lombok.Data;

@Data
public class CommentDto {
    private Long parentId;
    private String comment;
    private Integer type;
    private String content;
}

