package org.scoula.todo.service;

import java.util.List;
import org.scoula.todo.domain.TodoDTO;
import org.springframework.stereotype.Service;

public interface TodoService {
    List<TodoDTO> selectAll();
    int insertTodo(TodoDTO todo);
    int updateTodo(Long id);
    int deleteTodo(Long id);
}
