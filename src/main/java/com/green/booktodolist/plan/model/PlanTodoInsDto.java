package com.green.booktodolist.plan.model;

import lombok.Data;
@Data
// 프론트에서 받아오는 데이터
public class PlanTodoInsDto {
    private String author;
    private int bookmark;
    private String cate;
    private String company;
    private int del;
    private int finish;
    private String memo;
    private String start;
    private String end;
    private String title;
    private String page;
    private String isbn;
    private int iuser;
}