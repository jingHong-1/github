package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HelloCon {
    @GetMapping("/hello")
   public String Hello(@RequestParam(name="name") String name,Model model){

        model.addAttribute("name",name);
       return "hello";
    }


    //ModelAndView 模式
    @GetMapping("/hello2")
    public ModelAndView hello2(@RequestParam(name = "name") String name){
        ModelAndView mv = new ModelAndView();
        mv.addObject("name",name);
        mv.setViewName("hello2");
        return mv;

    }

}
