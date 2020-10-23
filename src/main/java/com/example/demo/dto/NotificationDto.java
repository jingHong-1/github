package com.example.demo.dto;

import com.example.demo.model.User;
import lombok.Data;

@Data
public class NotificationDto {
    private Long id;
    
    private Long gmtCreate;

    private Integer status; 
    
    private User notifier;
    
    private String outerTitle;
    
    private String type;
}
