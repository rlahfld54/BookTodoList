package com.green.booktodolist.calendar;

import com.green.booktodolist.calendar.model.CalendarDto;
import com.green.booktodolist.todoList.TodoService;
import com.green.booktodolist.todoList.model.SelCategoryDto;
import com.green.booktodolist.todoList.model.SelMainVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalendarService {
    private TodoService todoService;

    @Autowired
    public CalendarService(TodoService todoService) {
        this.todoService = todoService;
    }

    public SelMainVo GetMain(){
        return todoService.selMain();
    }

    public ArrayList<CalendarDto> getTodoList() {
        // 프론트엔드가 원하는 형식으로 파싱하는 곳
         List<SelCategoryDto> icategoryList = this.GetMain().getIcategory();

        ArrayList<CalendarDto> calendarList = new ArrayList<>();
        for (int i = 0; i < icategoryList.size(); i++) {
            CalendarDto dto = new CalendarDto();

            dto.setItodo(icategoryList.get(i).getItodo());
            dto.setTitle(icategoryList.get(i).getTitle());
            dto.setStart(icategoryList.get(i).getStart());
            dto.setEnd(icategoryList.get(i).getEnd());
//            dto.setColor(icategoryList.get(i).getColor());
            calendarList.add(i,dto);
        }

        return calendarList;
    }
}
