package com.example.demo.controller;

import com.example.demo.dto.FileDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@Controller
public class FileCon {
    @RequestMapping("/file/upload")
    @ResponseBody
    public FileDto upload(){


        FileDto fileDto = new FileDto();
        fileDto.setSuccess(1);
        fileDto.setUrl("/img/11.jpg");
        return fileDto;
    }



}
