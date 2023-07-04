package com.green.booktodolist.todoList.model;

import lombok.Data;

@Data
public class UpdTodoDto {
    private String start;
    private String end;
    private String finish;
    private String memo;
    private int itodo;
}
