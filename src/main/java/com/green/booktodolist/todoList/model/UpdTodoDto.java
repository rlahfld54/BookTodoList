package com.green.booktodolist.todoList.model;

import lombok.Data;

@Data
public class UpdTodoDto {
    private String start;
    private String end;
    private String memo;
    private String bookmark;
    private int itodo;
}
