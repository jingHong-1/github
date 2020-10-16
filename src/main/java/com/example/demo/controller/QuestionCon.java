package com.example.demo.controller;

import com.example.demo.dto.QuestionDto;

import com.example.demo.mapper.QuestionExtMapper;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionCon {

    @Autowired
    private QuestionService questionService;


    @GetMapping("/question/{id}")
    public String question(@PathVariable(value = "id") Long id,
                           Model model){

        QuestionDto questionDto = questionService.getById(id);
        //累加阅读数
        questionService.intView(id);
        model.addAttribute("question",questionDto);
        return "question";
    }
}
