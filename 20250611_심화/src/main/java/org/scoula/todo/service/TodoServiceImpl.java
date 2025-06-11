package org.scoula.todo.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.todo.domain.TodoDTO;
import org.scoula.todo.mapper.TodoMapper;
import org.springframework.stereotype.Service;

@Log4j2
@Service //서비스 계층임을 명시 + Bean 등록
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
    private final TodoMapper todoMapper;

    @Override
    public List<TodoDTO> selectAll() {
        List<TodoDTO> todos = todoMapper.selectAll();
        log.info("조회된 todo 개수 : {}", todos.size());
        return todos;


    }

    @Override
    public int insertTodo(TodoDTO todo) {
        int count = todoMapper.insertTodo(todo);
        log.info("삽입된 행의 개수 : {}", count);
        return count;
    }

    @Override
    public int updateTodo(Long id) {
        int count = todoMapper.updateTodo(id);
        log.info("수정된 행의 개수 : {}", count);
        return count;
    }

    @Override
    public int deleteTodo(Long id) {
        int count = todoMapper.deleteTodo(id);
        log.info("삭제된 행의 개수 : {}", count);
        return count;
    }
}
