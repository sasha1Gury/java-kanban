import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class TaskManager {
    private int idTask = 1;
    private int idEpic = 1;
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();
    private ArrayList<Subtask> subtasks = new ArrayList<>();

    public void createTasks(Task task) {
        task.setId(idTask++);
        tasks.put(task.getId(), task);
    }

    public void createEpic(Epic epic) {
        epic.setId(idEpic++);
        epics.put(epic.getId(), epic);
    }

    public void createSubtask(int id, Subtask subtask) {
        subtasks.add(subtask);
        epics.get(id).setSubtasks(subtask);
    }

    public ArrayList getListTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Список задач пуст");
        }
        for (Integer id: tasks.keySet()) {
            String key = id.toString();
            String value = tasks.get(id).toString();
            System.out.println(key + " " + value);
        }
        return new ArrayList<>(tasks.values());
    }

    public ArrayList getListEpics() {
        if (epics.isEmpty()) {
            System.out.println("Список эпиков пуст");
        }
        for (Integer name: epics.keySet()) {
            String key = name.toString();
            String value = epics.get(name).toString();
            System.out.println(key + " " + value + " ");
        }
        return new ArrayList<>(epics.values());
    }

    public ArrayList getListSubtasks() {
        return new ArrayList<>(subtasks);
    }

    public Task getTaskById(int id) {
        String value = tasks.get(id).toString();
        System.out.println(id + " " + value + " ");
        return tasks.get(id);
    }

    public Epic getEpicById(int id) {
        String value = epics.get(id).toString();
        System.out.println(id + " " + value + " ");
        return epics.get(id);
    }

    public Subtask getSubtaskById(int id) {
        return subtasks.get(id);
    }

    public void removeTasks() {
        tasks.clear();
    }

    public void removeEpics() {
        epics.clear();
    }

    public void removeSubtasks() {
        subtasks.clear();
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
    }

    public void deleteTaskById(int id) {
        tasks.remove(id);
    }

    public void updateEpic(Epic epic) {
        epics.put(epic.getId(), epic);
    }

    public void deleteEpicById(int id) {
        epics.remove(id);
    }

    public void updateSubtask(Subtask subtask) {
        subtasks.set(subtask.getId(), subtask);
    }

    public void deleteSubtaskById(int id) {
        subtasks.remove(subtasks.get(id));
    }

    public void getListSubtasksByEpicId(int id) {
        String value = epics.get(id).getSubtasks().toString();
        //String value = epics.get(id).subtasks.toString();
        System.out.println("epic - " + id + " список - " + value);
        System.out.println();
    }

    public void setEpicStatus() {
        for (Integer name: epics.keySet()) {
            if(epics.get(name).getSubtasks().isEmpty() || epics.get(name).isNew()) {
                epics.get(name).status = "NEW";
            } else if(epics.get(name).isDone()) {
                epics.get(name).status = "DONE";
            } else epics.get(name).status = "IN_PROGRESS";
        }
    }
}