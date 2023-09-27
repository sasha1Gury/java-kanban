import java.util.ArrayList;

public class Epic extends Task {
    ArrayList<Subtask> subtasks = new ArrayList<>();

    public Epic(String taskName, String description){
        super(taskName, description);
    }

    @Override
    public String toString() {
        return ("Название эпика - " + taskName + " Описание эпика - " + description + " все подзадачи - " +
                subtasks.toString());
    }
}
