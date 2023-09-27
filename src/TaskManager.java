import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TaskManager {
    int idTask = 1;
    int idEpic = 1;
    HashMap<Integer, Task> tasks = new HashMap<>();
    HashMap<Integer, Epic> epics = new HashMap<>();
    Subtask subtask;

    public void doTasks(Task task) {
        tasks.put(idTask++, task);
    }

    public void doEpic(Epic epic) {
        epics.put(idEpic++, epic);
    }

    public void createSubtask(int id, Subtask subtask) {
        epics.get(id).subtasks.add(subtask);
    }

    public void getListTasks() {
        for (Integer id: tasks.keySet()) {
            String key = id.toString();
            String value = tasks.get(id).toString();
            System.out.println(key + " " + value);
        }
    }

    public void getListEpics() {
        for (Integer name: epics.keySet()) {
            String key = name.toString();
            String value = epics.get(name).toString();
            System.out.println(key + " " + value + " ");
        }
    }

    public void retrievingById() {

    }
}
