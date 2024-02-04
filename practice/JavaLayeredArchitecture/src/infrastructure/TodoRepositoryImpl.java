package infrastructure;

import java.util.List;

import domain.Todo;
import domain.TodoRepository;

public class TodoRepositoryImpl implements TodoRepository {

    @Override
    public Todo save(Todo todo) {
        return new Todo(1, "title", false);
    }

    @Override
    public List<Todo> findAll() {
        return List.of(new Todo(1, "findAll", false));
    }

    @Override
    public Todo findById(int id) {
        return new Todo(1, "findById", false);
    }

    @Override
    public void deleteById(int id) {
        System.out.println("Deleted");
    }

}
