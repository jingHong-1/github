package com.example.demo.controller;


import com.example.demo.dto.NotificationDto;
import com.example.demo.dto.PaginationDto;
import com.example.demo.enums.NotificationTypeEnum;
import com.example.demo.mapper.NotificationMapper;
import com.example.demo.model.User;
import com.example.demo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NotificationCon {
    @Autowired
    private NotificationService notificationService;


    @GetMapping("/notification/{id}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name = "id") Long id){

        User user= (User) request.getSession().getAttribute("user");
        if (user==null){
            return "redirect:/";
        }

        NotificationDto notificationDto = notificationService.read(id,user);

        if (NotificationTypeEnum.REPLY_COMMENT.getType() == notificationDto.getType()
                || NotificationTypeEnum.REPLY_QUESTION.getType() == notificationDto.getType()) {
            return "redirect:/question/"+notificationDto.getOuterid();
        } else {
            return "redirect:/";
        }


    }

}
