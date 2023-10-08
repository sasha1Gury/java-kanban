import java.util.ArrayList;
import java.util.List;

public interface TaskManager {

    public void createTasks(Task task);

    public void createEpic(Epic epic);

    public void createSubtask(Subtask subtask);

    public ArrayList<Task> getListTasks();

    public ArrayList<Epic> getListEpics();

    public ArrayList<Subtask> getListSubtasks();

    public Task getTaskById(int id);

    public Epic getEpicById(int id);

    public Subtask getSubtaskById(int id);

    public void removeTasks();

    public void removeEpics();

    public void removeSubtasks();

    public void updateTask(Task task);

    public void deleteTaskById(int id);

    public void updateEpic(Epic epic);

    public void deleteEpicById(int id);

    public void updateSubtask(Subtask subtask);

    public void deleteSubtaskById(int id);

    public ArrayList<Subtask> getListSubtasksByEpicId(int id);

    List<Task> getHistory();
}