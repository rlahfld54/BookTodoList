package com.green.booktodolist.todoList;

import com.green.booktodolist.todoList.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

        for (int i = 0; i <selTitleDto.size(); i++) {
            if (selTitleDto.get(i).getFinish().equals("0") ){
             selTitleDto.get(i).setFinish("미완료");
            } else{
                selTitleDto.get(i).setFinish("완료");
            }
        }


        int level = mapper.selUserLevel();

        List<SelFinishDto> notFinishTodo = mapper.selNotFinish();
        for (int i = 0; i <notFinishTodo.size(); i++) {
            if (notFinishTodo.get(i).getFinish().equals("0")){
                notFinishTodo.get(i).setFinish("미완료");
            } else {
                notFinishTodo.get(i).setFinish("완료");
            }
        }
        List<SelFinishDto> finishTodo = mapper.selFinish();
        for (int i = 0; i <finishTodo.size(); i++) {
            if (finishTodo.get(i).getFinish().equals("0")){
                finishTodo.get(i).setFinish("미완료");
            } else {
                finishTodo.get(i).setFinish("완료");
            }
        }

        List<SelCategoryDto> selcategorylist = mapper.selcategorylist();
        for (int i = 0; i <selcategorylist.size(); i++) {
            if (selcategorylist.get(i).getFinish().equals("0")){
                selcategorylist.get(i).setFinish("미완료");
            } else {
                selcategorylist.get(i).setFinish("완료");
            }
        }

        return SelMainVo.builder().level(level).icategory(selcategorylist).build();

    }
    public SelDetailDto selDetail(int itodo){
        SelDetailDto dto = mapper.selDetail(itodo);
        dto.setItodo(itodo);
        String finishYn = dto.getFinishYn();
        if (finishYn.equals("1")){
            dto.setFinishYn("완료");
        }else dto.setFinishYn("미완료");

        return dto;

    }
    public int UpdTodo(UpdTodoDto dto){
        return mapper.UpdTodo(dto);
    }

    public int DelTodo(int itodo){
        DelTodoDto dto = new DelTodoDto();
        dto.setItodo(itodo);
        return mapper.DelTodo(dto);
    }
}
