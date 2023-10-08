import java.util.ArrayList;
import java.util.HashMap;


public class InMemoryTaskManager implements TaskManager {
    private int id = 1;
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();
    private HashMap<Integer, Subtask> subtasks = new HashMap<>();

    public void createTasks(Task task) {
        task.setId(id++);
        tasks.put(task.getId(), task);
    }

    public void createEpic(Epic epic) {
        epic.setId(id++);
        epics.put(epic.getId(), epic);
    }

    public void createSubtask(Subtask subtask) {
        subtask.setId(id++);
        subtasks.put(subtask.getId(), subtask);
        epics.get(subtask.getEpicId()).addSubtasks(subtask);
        setEpicStatus();
    }

    public ArrayList<Task> getListTasks() {
        return new ArrayList<>(tasks.values());
    }

    public ArrayList<Epic> getListEpics() {
        return new ArrayList<>(epics.values());
    }

    public ArrayList<Subtask> getListSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    public Epic getEpicById(int id) {
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
        subtasks.clear();
    }

    public void removeSubtasks() {
        subtasks.clear();
        for (Integer name: epics.keySet()) {
            epics.get(name).clearAllSubtasks();
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
        setEpicStatus();
    }

    public void deleteEpicById(int id) {
        epics.remove(id);
    }

    public void updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
        System.out.println();
        epics.get(subtask.getEpicId()).setSubtasks(subtask);
        setEpicStatus();
    }

    public void deleteSubtaskById(int id) {
        epics.get(subtasks.get(id).getEpicId()).removeSubtaskById(epics.get(subtasks.get(id).getEpicId()).getSubtasks()
                .indexOf(subtasks.get(id))); // Получение индекса subtask в списке
        subtasks.remove(id);
        setEpicStatus();
    }

    public ArrayList<Subtask> getListSubtasksByEpicId(int id) {
        return new ArrayList<>(epics.get(id).getSubtasks());
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