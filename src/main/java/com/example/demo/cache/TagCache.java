package com.example.demo.cache;

import com.example.demo.dto.TagDto;
import com.sun.el.stream.Stream;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TagCache {
    public static List<TagDto> get(){
        List<TagDto> tagDtos = new ArrayList<>();

        TagDto program = new TagDto();
        program.setCategoryName("开发语言");
        program.setTags(Arrays.asList("js","php","css","html","java","node","python","sql","mysql","php","css","html","java","node","python","sql","mysql","php","css","html","java","node","python","sql","mysql"));
        tagDtos.add(program);

        TagDto framework = new TagDto();
        framework.setCategoryName("平台框架");
        framework.setTags(Arrays.asList("spring","springboot","koa","aop","ioc","springboot","koa","aop","ioc","springboot","koa","aop","ioc"));
        tagDtos.add(framework);

        TagDto server = new TagDto();
        server.setCategoryName("服务器");
        server.setTags(Arrays.asList("linux","nginx","apache","centos","tomcat","缓存","unix"));
        tagDtos.add(server);

        TagDto db = new TagDto();
        db.setCategoryName("数据库");
        db.setTags(Arrays.asList("MySQL","oracle","Redis","sql","tomcat","sqllite","h2"));
        tagDtos.add(db);

        TagDto tool = new TagDto();
        tool.setCategoryName("数据库");
        tool.setTags(Arrays.asList("git","github","码云"));
        tagDtos.add(tool);


        return tagDtos;
    }
    
    
    public static String  filterInvalid(String tags){
        String[] split = StringUtils.split(tags, ",");
        List<TagDto> tagDtos = get();
//        多层循环
        List<String> tagList = tagDtos.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalid = Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining(","));
        return invalid;


    }
}
