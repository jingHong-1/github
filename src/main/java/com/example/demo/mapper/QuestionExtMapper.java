package com.example.demo.mapper;

import com.example.demo.model.Question;

import java.util.List;

public interface QuestionExtMapper {

    int intView(Question record);
    int intCommentCount(Question record);

    List <Question> selectRelated(Question question);
}