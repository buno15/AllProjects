package domain;

import java.util.List;

public interface TodoRepository {
    Todo save(Todo todo);

    List<Todo> findAll();

    Todo findById(int id);

    void deleteById(int id);
}
