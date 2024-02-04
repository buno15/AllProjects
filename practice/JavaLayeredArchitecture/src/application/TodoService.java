package application;

import java.util.List;

import domain.Todo;
import domain.TodoRepository;

public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo save(Todo todo) {
        return todoRepository.save(todo);
    }

    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    public Todo findById(int id) {
        return todoRepository.findById(id);
    }

    public void deleteById(int id) {
        todoRepository.deleteById(id);
    }
}
