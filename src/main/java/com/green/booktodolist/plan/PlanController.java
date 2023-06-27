package com.green.booktodolist.plan;


import com.green.booktodolist.plan.model.PlanBookInsDto;
import com.green.booktodolist.todoList.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static java.sql.DriverManager.println;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/plan")
public class PlanController {

    private final PlanService service;

    @Autowired
    public PlanController(PlanService service) {
        this.service = service;
    }

    @PostMapping
    public int postBook(PlanBookInsDto dto){
        return service.insBook(dto);
    }
}
