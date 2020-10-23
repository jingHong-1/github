package com.example.demo.service;

import com.example.demo.dto.NotificationDto;
import com.example.demo.dto.PaginationDto;
import com.example.demo.mapper.NotificationMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.Notification;
import com.example.demo.model.NotificationExample;
import com.example.demo.model.User;
import com.example.demo.model.UserExample;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NotificationService {
    @Autowired
    private NotificationMapper notificationMapper;

    @Autowired
    private UserMapper userMapper;

    public PaginationDto list(Long userId, Integer page, Integer size) {
        PaginationDto<NotificationDto> paginationDto = new PaginationDto<>();
        Integer totalpage;
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria().andReceiverEqualTo(userId);

        Integer totalCount = notificationMapper.countByExample(notificationExample);
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
        NotificationExample example = new NotificationExample();
        example.createCriteria().andReceiverEqualTo(userId);
        example.setOrderByClause("gmt_create desc");
        List<Notification> notifications = notificationMapper.selectByExampleWithRowbounds(example,new RowBounds(offsize,size));

        if (notifications.size() == 0){
            return paginationDto;
        }

        Set<Long> disUserIds = notifications.stream().map(notify -> notify.getNotifier()).collect(Collectors.toSet());
        ArrayList<Long> userIds = new ArrayList<>(disUserIds);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdIn(userIds);
        List<User> users = userMapper.selectByExample(userExample);

        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(u -> u.getId(), u -> u));




        List<NotificationDto> notificationDtos = new ArrayList<>();


        paginationDto.setData(notificationDtos);

        return paginationDto;
    }
}
