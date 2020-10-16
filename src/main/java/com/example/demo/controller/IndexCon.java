package com.example.demo.controller;


import com.example.demo.dto.PaginationDto;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
public class IndexCon {

    @Autowired
    private QuestionService questionService;



    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name="page",defaultValue = "1") Integer page,
                        @RequestParam(name="size",defaultValue = "5") Integer size){
        PaginationDto pagination = questionService.list(page,size);
        model.addAttribute("pagination",pagination);


        return "index";
    }


}
