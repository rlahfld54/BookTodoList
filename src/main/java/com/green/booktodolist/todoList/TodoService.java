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

        int level = mapper.selUserLevel();
        int count = mapper.selUserCount();

        List<SelCategoryDto> selcategorylist = mapper.selcategorylist();
        return SelMainVo.builder().level(level).count(count).icategory(selcategorylist).build();

    }
    public int updel(UpdDel dto){

        SelDetailDto selTodo = mapper.selTodo(dto.getItodo());

        final int countup =10;
        final int countdown = -10;

        if (selTodo.getFinish().equals("0") && dto.getFinish()==1){
            mapper.countUp(countup);
            mapper.updLevel();
        }else if(selTodo.getFinish().equals("1") && dto.getFinish()==0){
            mapper.countDown(countdown);
        }
        return mapper.updel(dto);
    }

    public SelDetailDto selDetail(int itodo){

        SelDetailDto dto = mapper.selDetail(itodo);
        dto.setItodo(itodo);

        String finishYn = dto.getFinish();

        if (finishYn.equals("1")){
            dto.setFinish("완료");
        }else dto.setFinish("미완료");
        return dto;

    }

    public int UpdTodo(UpdTodoDto dto){
        SelDetailDto selTodo = mapper.selTodo(dto.getItodo());

        if (dto.getStart().equals("") ||dto.getEnd().equals("") || dto.getMemo().equals("") || dto.getBookmark().equals("")){
            if (dto.getStart().equals("")){
                dto.setStart(selTodo.getStart());
            }if (dto.getMemo().equals("")) {
                dto.setMemo(selTodo.getMemo());
            }if (dto.getEnd().equals("")){
                dto.setEnd(selTodo.getEnd());
            }if(dto.getBookmark().equals("")){
                dto.setBookmark(selTodo.getBookmark());
            }
        }

        return  mapper.updTodo(dto);
    }

    public int DelTodo(int itodo){
        DelTodoDto dto = new DelTodoDto();
        dto.setItodo(itodo);
        return mapper.DelTodo(dto);
    }
}
