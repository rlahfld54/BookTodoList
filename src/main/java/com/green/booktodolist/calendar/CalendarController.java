package com.green.booktodolist.calendar;

import com.green.booktodolist.calendar.model.CalendarDto;
import com.green.booktodolist.todoList.TodoService;
import com.green.booktodolist.todoList.model.SelMainVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/calendar")
public class CalendarController {
    private CalendarService calendarService;

    @Autowired
    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    // TodoList 데이터 베이스 뿌린거 그대로 사용할거임
    @GetMapping
    public ArrayList<CalendarDto> getTodoList(){
        return calendarService.getTodoList();
    }
}
