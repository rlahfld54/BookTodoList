package com.green.booktodolist.calendar.model;

import lombok.Data;

@Data
public class CalendarDto {
    private int itodo;
    private String title;
    private String start;
    private String end;
    private String color;
}
