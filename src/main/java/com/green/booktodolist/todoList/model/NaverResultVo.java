package com.green.booktodolist.todoList.model;

import lombok.Data;

import java.util.List;

@Data
public class NaverResultVo {
    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<BookVo2> items;
}
