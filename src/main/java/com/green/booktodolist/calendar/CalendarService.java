package com.green.booktodolist.calendar;

import com.green.booktodolist.calendar.model.CalendarDto;
import com.green.booktodolist.todoList.TodoService;
import com.green.booktodolist.todoList.model.SelCategoryDto;
import com.green.booktodolist.todoList.model.SelMainVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
            String date = icategoryList.get(i).getStart();
            String enddate = icategoryList.get(i).getEnd();

            String startdate = null;
            String endDate = null;

            try {
                startdate = changeDate(date);
                endDate = changeDate(enddate);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            dto.setItodo(icategoryList.get(i).getItodo());
            dto.setTitle(icategoryList.get(i).getTitle());
            dto.setStart(startdate);
            dto.setEnd(endDate);
            dto.setColor(icategoryList.get(i).getColor());
            calendarList.add(i,dto);
        }

        return calendarList;
    }

    public String changeDate(String str) throws ParseException {
        // String str = "2023-12-25 00:41:19";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse(str);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");   // yyyy-MM-dd HH:mm:ss
        String test = formatter.format(date);
        return test;
    }
}
