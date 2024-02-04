package presentation;

import application.TodoService;
import domain.Todo;

public class TodoViewer {
    private final TodoService todo;

    public TodoViewer(TodoService todo) {
        this.todo = todo;
    }

    public void save() {
        todo.save(new Todo());
    }

    public void deleteById(int id) {
        todo.deleteById(id);
    }

    public void showAll() {
        todo.findAll().forEach(t -> System.out.println(t.getId() + " " + t.getTitle() + " " + t.isCompleted()));
    }
}
