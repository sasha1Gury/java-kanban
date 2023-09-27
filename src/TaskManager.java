import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TaskManager {
    int idTask = 1;
    int idEpic = 1;
    HashMap<Integer, Task> tasks = new HashMap<>();
    HashMap<Integer, Epic> epics = new HashMap<>();

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
        if (tasks.isEmpty()) {
            System.out.println("Список задач пуст");
        }
        for (Integer id: tasks.keySet()) {
            String key = id.toString();
            String value = tasks.get(id).toString();
            System.out.println(key + " " + value);
        }
    }

    public void getListEpics() {
        if (epics.isEmpty()) {
            System.out.println("Список эпиков пуст");
        }
        for (Integer name: epics.keySet()) {
            String key = name.toString();
            String value = epics.get(name).toString();
            System.out.println(key + " " + value + " ");
        }
    }

    public void getTaskById(int id) {
        String value = tasks.get(id).toString();
        System.out.println(id + " " + value + " ");
    }

    public void getEpicById(int id) {
        String value = epics.get(id).toString();
        System.out.println(id + " " + value + " ");
    }

    public void removeAll() {
        tasks.clear();
        epics.clear();
    }

    public void retrievingById() {
        System.out.println("Введите: 1 - задача, 2 - эпик");
        Scanner scanner = new Scanner(System.in);
        int identification;
        int cas = scanner.nextInt();
        switch (cas) {
            case 1:
                System.out.println("Введите идентификатор задачи");
                identification = scanner.nextInt();
                getTaskById(identification);
                break;
            case 2:
                System.out.println("Введите идентификатор задачи");
                identification = scanner.nextInt();
                getEpicById(identification);
                break;
            default:
                System.out.println("ERROR");
                break;
        }
    }

    
}
