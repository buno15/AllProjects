import application.TodoService;
import domain.TodoRepository;
import infrastructure.TodoRepositoryImpl;
import presentation.TodoViewer;

public class Main {
    public static void main(String[] args) throws Exception {
        TodoRepository todoRepository = new TodoRepositoryImpl();
        TodoService todoService = new TodoService(todoRepository);
        TodoViewer todoViewer = new TodoViewer(todoService);
        todoViewer.showAll();
        todoViewer.save();
        todoViewer.deleteById(0);
    }
}
