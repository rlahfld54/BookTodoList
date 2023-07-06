package com.green.booktodolist.todoList;

import com.green.booktodolist.todoList.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;

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

        for (int i = 0; i <selcategorylist.size(); i++) {
            if (selcategorylist.get(i).getFinish().equals("0")){
                selcategorylist.get(i).setFinish("미완료");
            } else {
                selcategorylist.get(i).setFinish("완료");
            }
            if (selcategorylist.get(i).getTotalpage().equals("")){
                selcategorylist.get(i).setTotalpage(null);
            }
        }

        return SelMainVo.builder().level(level).count(count).icategory(selcategorylist).build();

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

        int count = (int) (Math.random() * 10) + 1;
        System.out.println(count);
        SelDetailDto selTodo = mapper.selTodo(dto.getItodo());

        if (dto.getStart().equals("") ||dto.getEnd().equals("") || dto.getMemo().equals("") ){
            if (dto.getStart().equals("")){
                dto.setStart(selTodo.getStart());
            }if (dto.getMemo().equals("")) {
                dto.setMemo(selTodo.getMemo());
            }if (dto.getEnd().equals("")){
                dto.setEnd(selTodo.getEnd());
            }
        }

        if (dto.getFinish().equals("완료")){
            dto.setFinish("1");
        }else if (dto.getFinish().equals("미완료")){
            dto.setFinish("0");
        }

        if (selTodo.getFinish().equals("0") && dto.getFinish().equals("1")){

            mapper.UpdCount(count);
            mapper.UpdLevel();
        }

        return  mapper.UpdTodo(dto);
    }

    public int DelTodo(int itodo){
        DelTodoDto dto = new DelTodoDto();
        dto.setItodo(itodo);
        return mapper.DelTodo(dto);
    }
}
