import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class InMemoryTaskManager implements TaskManager {
    private int id = 1;
    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();


    @Override
    public void createTasks(Task task) {
        task.setId(id++);
        tasks.put(task.getId(), task);
    }

    @Override
    public void createEpic(Epic epic) {
        epic.setId(id++);
        epics.put(epic.getId(), epic);
    }

    @Override
    public void createSubtask(Subtask subtask) {
        subtask.setId(id++);
        subtasks.put(subtask.getId(), subtask);
        epics.get(subtask.getEpicId()).addSubtasks(subtask);
        setEpicStatus();
    }

    @Override
    public ArrayList<Task> getListTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public ArrayList<Epic> getListEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public ArrayList<Subtask> getListSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    @Override
    public Task getTaskById(int id) {
        HistoryManager.addTaskToHistory(tasks.get(id));
        return tasks.get(id);
    }

    @Override
    public Epic getEpicById(int id) {
        HistoryManager.addTaskToHistory(epics.get(id));
        return epics.get(id);
    }

    @Override
    public Subtask getSubtaskById(int id) {
        HistoryManager.addTaskToHistory(subtasks.get(id));
        return subtasks.get(id);
    }

    @Override
    public void removeTasks() {
        tasks.clear();
    }

    @Override
    public void removeEpics() {
        epics.clear();
        subtasks.clear();
    }

    @Override
    public void removeSubtasks() {
        subtasks.clear();
        for (Integer name: epics.keySet()) {
            epics.get(name).clearAllSubtasks();
        }
    }

    @Override
    public void updateTask(Task task) {
        tasks.put(task.getId(), task);
    }

    @Override
    public void deleteTaskById(int id) {
        tasks.remove(id);
    }

    @Override
    public void updateEpic(Epic epic) {
        epics.put(epic.getId(), epic);
        setEpicStatus();
    }

    @Override
    public void deleteEpicById(int id) {
        epics.remove(id);
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
        System.out.println();
        epics.get(subtask.getEpicId()).setSubtasks(subtask);
        setEpicStatus();
    }

    @Override
    public void deleteSubtaskById(int id) {
        epics.get(subtasks.get(id).getEpicId()).removeSubtaskById(epics.get(subtasks.get(id).getEpicId()).getSubtasks()
                .indexOf(subtasks.get(id))); // Получение индекса subtask в списке
        subtasks.remove(id);
        setEpicStatus();
    }

    @Override
    public ArrayList<Subtask> getListSubtasksByEpicId(int id) {
        return new ArrayList<>(epics.get(id).getSubtasks());
    }

    @Override
    public List<Task> getHistory() {
        return HistoryManager.getHistory();
    }
    private void setEpicStatus() {
        for (Integer name: epics.keySet()) {
            if(epics.get(name).getSubtasks().isEmpty() || epics.get(name).isNew()) {
                epics.get(name).status = "NEW";
            } else if(epics.get(name).isDone()) {
                epics.get(name).status = "DONE";
            } else epics.get(name).status = "IN_PROGRESS";
        }
    }
}