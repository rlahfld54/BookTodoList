package com.green.booktodolist.todoList.model;

import lombok.Data;

@Data
public class SelDetailDto {
    private int itodo;
    private String createdTodo;
    private String catename;
    private String title;
    private String writer;
    private String start;
    private String finish;
    private String memo;
    private String finishYn;


}
