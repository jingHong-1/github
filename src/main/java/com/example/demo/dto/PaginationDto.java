package com.example.demo.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class PaginationDto {
    private List<QuestionDto> question;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private Integer totalpage;

    private List<Integer> pages = new ArrayList<>();


    public void setPagination(Integer totalpage, Integer page) {

        this.totalpage=totalpage;
//        把参数设置给属性page
        this.page=page;

//        1->1234567 2->1234567 3->1234567 4->1234567 5->2345678 ......
        pages.add(page);
//展示页多少
        for (int i=1;i<=3;i++){
            if (page-i>0){
                pages.add(0,page-i);
            }

            if (page+i<=totalpage){
                pages.add(page + i);
            }
        }




        //是否展示上一页
        if (page == 1){
            showPrevious = false;
        }else {
            showPrevious = true;
        }
        //是否展示下一页
        if (page == totalpage){
            showNext = false;

        }else{
            showNext = true;
        }
        //是否展示第一页
        if (pages.contains(1)){
            showFirstPage = false;
        }else {
            showFirstPage = true;
        }
        //是否展示最后一页
        if (pages.contains(totalpage)){
            showEndPage = false;
        }else {
            showEndPage = true;
        }





    }
}
