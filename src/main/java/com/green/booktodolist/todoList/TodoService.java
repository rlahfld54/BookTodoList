package com.green.booktodolist.todoList;

import com.green.booktodolist.todoList.model.SelCategoryDto;
import com.green.booktodolist.todoList.model.SelFinishDto;
import com.green.booktodolist.todoList.model.SelMainVo;
import com.green.booktodolist.todoList.model.SelTitleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    private final TodoMapper mapper;

    @Autowired
    public TodoService(TodoMapper mapper) {
        this.mapper = mapper;
    }

    public SelMainVo selMain(){
        List<SelTitleDto> selTitleDto = mapper.selTitle();
        int level = mapper.selUserLevel();
        List<SelFinishDto> notFinishTodo = mapper.selNotFinish();
        List<SelFinishDto> finishTodo = mapper.selFinish();
        List<SelCategoryDto> selcategorylist = mapper.selcategorylist();


        return SelMainVo.builder().TodoTitle(selTitleDto).level(level).icategory(selcategorylist)
                .NotFinish(notFinishTodo).Finish(finishTodo).build();
    }
}
