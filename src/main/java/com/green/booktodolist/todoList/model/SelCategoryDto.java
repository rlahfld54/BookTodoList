package com.green.booktodolist.todoList.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SelCategoryDto {
    private String cate_name;
    private int itodo;
    private String title;
    private int bookmark;
    private int del_yn;
    private int finish_yn;
    private String start;
    private String end;
    private String color;
}
