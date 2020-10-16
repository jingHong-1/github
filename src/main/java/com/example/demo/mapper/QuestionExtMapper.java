package com.example.demo.mapper;

import com.example.demo.model.Question;

public interface QuestionExtMapper {

    int intView(Question record);
    int intCommentCount(Question record);
}