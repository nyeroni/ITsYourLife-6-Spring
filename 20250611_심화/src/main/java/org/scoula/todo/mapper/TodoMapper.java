package org.scoula.todo.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Select;
import org.scoula.todo.domain.TodoDTO;

public interface TodoMapper {
    @Select("SELECT * FROM todo ORDER BY id DESC")
    List<TodoDTO> selectAll();

    int insertTodo(TodoDTO todo);
    int updateTodo(Long id);
    int deleteTodo(Long id);
}
