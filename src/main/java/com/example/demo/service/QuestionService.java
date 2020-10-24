package com.example.demo.service;

import com.example.demo.dto.PaginationDto;
import com.example.demo.dto.QuestionDto;
import com.example.demo.dto.QuestionQueryDto;
import com.example.demo.exception.CustomizeErrorCode;
import com.example.demo.exception.CustomizeException;
import com.example.demo.mapper.QuestionExtMapper;
import com.example.demo.mapper.QuestionMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Question;
import com.example.demo.model.QuestionExample;
import com.example.demo.model.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;


    public PaginationDto list(String search ,Integer page, Integer size) {


        if (StringUtils.isNotBlank(search)) {
            String[] tags = StringUtils.split(search, ' ');
            search = Arrays.stream(tags).collect(Collectors.joining("|"));
        }
//拼接string




//        计算分页
        PaginationDto paginationDto = new PaginationDto();
        Integer totalpage;
        QuestionQueryDto questionQueryDto = new QuestionQueryDto();
//        查询
        questionQueryDto.setSearch(search);
        Integer totalCount = questionExtMapper.countBySearch(questionQueryDto);

        if (totalCount % size ==0){
            totalpage = totalCount/size;
        }else {
            totalpage = totalCount/size + 1;
        }

        if (page <1 ){
            page = 1;
        }
        if (page > totalpage){
            page = totalpage;
        }
        paginationDto.setPagination(totalpage,page);
//----
        Integer offsize = size*(page - 1);
        QuestionExample questionExample = new QuestionExample();
        questionExample.setOrderByClause("gmt_create desc");
        questionQueryDto.setSize(size);
        questionQueryDto.setPage(offsize);

        List<Question> questions = questionExtMapper.selectBySearch(questionQueryDto);
        List<QuestionDto> questionDtoList = new ArrayList<>();

        for (Question question : questions) {
            User user =  userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);

        }
        paginationDto.setData(questionDtoList);

        return paginationDto;
    }

    public PaginationDto listByUserId(Long userId, Integer page, Integer size) {
        PaginationDto paginationDto = new PaginationDto();
        Integer totalpage;
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userId);

        Integer totalCount = questionMapper.countByExample(questionExample);
        if (totalCount % size ==0){
            totalpage = totalCount/size;
        }else {
            totalpage = totalCount/size + 1;
        }

        if (page <1 ){
            page = 1;
        }
        if (page > totalpage){
            page = totalpage;
        }
        paginationDto.setPagination(totalpage,page);
//----
        Integer offsize = size*(page - 1);
        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreatorEqualTo(userId);
        example.setOrderByClause("gmt_create desc");
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(example,new RowBounds(offsize,size));

        List<QuestionDto> questionDtoList = new ArrayList<>();

        for (Question question : questions) {
            User user =  userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);

        }
        paginationDto.setData(questionDtoList);

        return paginationDto;
    }

    public QuestionDto getById(Long id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question == null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDto questionDto = new QuestionDto();
        //把question赋值给questionDto
        BeanUtils.copyProperties(question,questionDto);
        User user =  userMapper.selectByPrimaryKey(question.getCreator());
        questionDto.setUser(user);
        return questionDto;
    }

    public void createOrUpdate(Question question) {

        if (question.getId() == null){
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setViewCount(0);
            question.setCommentCount(0);
            question.setLikeCount(0);
            questionMapper.insert(question);
        }else {
            //更新
            Question upDataQuestion = new Question();
            upDataQuestion.setGmtModified(System.currentTimeMillis());
            upDataQuestion.setTitle(question.getTitle());
            upDataQuestion.setDescription(question.getDescription());
            upDataQuestion.setTag(question.getTag());
            QuestionExample example = new QuestionExample();
            example.createCriteria().andIdEqualTo(question.getId());
            int updated = questionMapper.updateByExampleSelective(upDataQuestion, example);
            if (updated != 1){
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }

        }
    }

    public void intView(Long id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.intView(question);
    }

    public List<QuestionDto> selectRelated(QuestionDto queryDto) {
        if (StringUtils.isBlank(queryDto.getTag())) {
            return new ArrayList<>();
        }
//拼接string

        String[] tags = StringUtils.split(queryDto.getTag(), ',');
        String regexpTag = Arrays.stream(tags).collect(Collectors.joining("|"));
        Question question = new Question();
        question.setId(queryDto.getId());
        question.setTag(regexpTag);

        List<Question> questions = questionExtMapper.selectRelated(question);

        List<QuestionDto> questionDtos = questions.stream().map(q -> {
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(q,questionDto);
            return questionDto;
        }).collect(Collectors.toList());
        return questionDtos;
    }
}

