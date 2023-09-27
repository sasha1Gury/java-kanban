import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TaskManager {
    int idTask = 1;
    int idEpic = 1;
    HashMap<Integer, Task> tasks = new HashMap<>();
    HashMap<Integer, Epic> epics = new HashMap<>();

    public void doTasks(Task task) {
        task.setId(idTask++);
        tasks.put(task.getId(), task);
    }

    public void doEpic(Epic epic) {
        epic.setId(idEpic++);
        epics.put(epic.getId(), epic);
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

    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
        tasks.get(task.getId()).setStatusInProgress();
    }

    public void deleteTaskById(int id) {
        tasks.remove(id);
    }

    public void updateEpic(Epic epic) {
        epics.put(epic.getId(), epic);
        epics.get(epic.getId()).setStatusInProgress();
    }

    public void deleteEpicById(int id) {
        epics.remove(id);
    }

    public void getListSubtasksByEpicId(int id) {
        String value = epics.get(id).subtasks.toString();
        System.out.println("epic - " + id + " список - " + value);
        System.out.println();
    }

    public void setEpicStatus() {
        for (Integer name: epics.keySet()) {
            if(epics.get(name).subtasks.isEmpty() || epics.get(name).isNew()) {
                epics.get(name).status = "NEW";
            } else if(epics.get(name).isDone()) {
                epics.get(name).status = "DONE";
            } else epics.get(name).status = "IN_PROGRESS";
        }
    }
}
