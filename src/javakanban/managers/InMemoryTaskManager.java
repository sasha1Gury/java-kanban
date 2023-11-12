package javakanban.managers;

import javakanban.tasks.Epic;
import javakanban.tasks.Status;
import javakanban.tasks.Subtask;
import javakanban.tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class InMemoryTaskManager implements TaskManager {
    private int id = 1;
    protected final HashMap<Integer, Task> tasks = new HashMap<>();
    protected final HashMap<Integer, Epic> epics = new HashMap<>();
    protected final HashMap<Integer, Subtask> subtasks = new HashMap<>();
    protected final HistoryManager historyManager = Managers.getDefaultHistory();

    protected void setId(int id) {
        this.id = id;
    }

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
        epics.get(subtask.getEpicId()).addSubtask(subtask);
        setEpicStatus(epics.get(subtask.getEpicId()));
    }

    @Override
    public List<Task> getListTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public List<Epic> getListEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public List<Subtask> getListSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    @Override
    public Task getTaskById(int id) {
        historyManager.addTaskToHistory(tasks.get(id));
        return tasks.get(id);
    }

    @Override
    public Epic getEpicById(int id) {
        historyManager.addTaskToHistory(epics.get(id));
        return epics.get(id);
    }

    @Override
    public Subtask getSubtaskById(int id) {
        historyManager.addTaskToHistory(subtasks.get(id));
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
        for (Epic e : epics.values()) {
            e.clearAllSubtasks();
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
        setEpicStatus(epic);
    }

    @Override
    public void deleteEpicById(int id) {
        epics.get(id).clearAllSubtasks();
        epics.remove(id);
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
        System.out.println();
        epics.get(subtask.getEpicId()).setSubtasks(subtask);
        setAllEpicStatus();
    }

    @Override
    public void deleteSubtaskById(int id) {
        int epicId = subtasks.get(id).getEpicId();
        Epic epic = epics.get(epicId);
        epic.removeSubtaskById(id);
        setEpicStatus(epic);
        subtasks.remove(id);
    }

    @Override
    public List<Subtask> getListSubtasksByEpicId(int id) {
        return new ArrayList<>(epics.get(id).getSubtasks());
    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }

    private void setAllEpicStatus() {
        for (Integer name: epics.keySet()) {
            if(epics.get(name).getSubtasks().isEmpty() || epics.get(name).isNew()) {
                epics.get(name).status = Status.NEW;
            } else if(epics.get(name).isDone()) {
                epics.get(name).status = Status.DONE;
            } else epics.get(name).status = Status.IN_PROGRESS;
        }
    }

    private void setEpicStatus(Epic epic) {
        if(epic.getSubtasks().isEmpty() || epic.isNew()) {
            epic.status = Status.NEW;
        } else if(epic.isDone()) {
            epic.status = Status.DONE;
        } else epic.status = Status.IN_PROGRESS;
    }
}