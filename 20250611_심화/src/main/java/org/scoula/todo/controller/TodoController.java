package org.scoula.todo.controller;

import lombok.RequiredArgsConstructor;
import org.scoula.todo.domain.TodoDTO;
import org.scoula.todo.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {
    private final TodoService todoService;

    @PostMapping("/insert")
    public String insertTodo(TodoDTO todo) {
        todoService.insertTodo(todo);
        return "redirect:/";
    }
    @PostMapping("/update")
    public String updateTodo(@RequestParam("id") Long id) {
        todoService.updateTodo(id);
        return "redirect:/";
    }
    @PostMapping("/delete")
    public String deleteTodo(@RequestParam("id") Long id) {
        todoService.deleteTodo(id);
        return "redirect:/";
    }
}
