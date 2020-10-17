package com.example.demo.dto;

import lombok.Data;

@Data
public class CommentCreateDto {
    private Long parentId;
    private Integer type;
    private String content;
}

