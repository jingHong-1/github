package com.example.demo.controller;

import com.example.demo.dto.CommentCreateDto;
import com.example.demo.dto.CommentDto;
import com.example.demo.dto.ResultDto;
import com.example.demo.enums.CommentTypeEnum;
import com.example.demo.exception.CustomizeErrorCode;
import com.example.demo.model.Comment;
import com.example.demo.model.User;
import com.example.demo.service.CommentService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class CommentCon {

    @Autowired
    private CommentService commentService;


    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDto commentCreateDto,
                       HttpServletRequest request){
        User user= (User) request.getSession().getAttribute("user");
        if (user == null){
            return ResultDto.erroeOf(CustomizeErrorCode.NO_LOGIN);
        }
        //借助commons  阿帕奇的工具包判断
        if (commentCreateDto == null || StringUtils.isBlank(commentCreateDto.getContent())){
            return ResultDto.erroeOf(CustomizeErrorCode.CONTENT_IS_EMPTY);
        }
        Comment comment = new Comment();
        comment.setParentId(commentCreateDto.getParentId());
        comment.setContent(commentCreateDto.getContent());
        comment.setType(commentCreateDto.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);
        commentService.insert(comment,user);
        return ResultDto.okOf();
    }

    //二级回复
    @ResponseBody
    @RequestMapping(value = "/comment/{id}",method = RequestMethod.GET)
    public ResultDto comments(@PathVariable(name = "id") Long id){
        List<CommentDto> commentDtos = commentService.ListByTargeId(id, CommentTypeEnum.COMMENT);


        return  ResultDto.okOf(commentDtos);
    }
}
