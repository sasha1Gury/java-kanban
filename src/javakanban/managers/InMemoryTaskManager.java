package javakanban.managers;

import javakanban.comparators.TimeComparator;
import javakanban.tasks.Epic;
import javakanban.tasks.Status;
import javakanban.tasks.Subtask;
import javakanban.tasks.Task;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;


public class InMemoryTaskManager implements TaskManager {
    private int id = 1;
    protected final HashMap<Integer, Task> tasks = new HashMap<>();
    protected final HashMap<Integer, Epic> epics = new HashMap<>();
    protected final HashMap<Integer, Subtask> subtasks = new HashMap<>();
    protected final HistoryManager historyManager = Managers.getDefaultHistory();
    protected final Set<Task> prioritizedTasks = new TreeSet<>(new TimeComparator());

    protected void setId(int id) {
        this.id = id;
    }

    @Override
    public void createTasks(Task task) throws IllegalStateException {
        validationTimeOverlaps(task);
        task.setId(id++);
        tasks.put(task.getId(), task);
        prioritizedTasks.add(task);
    }

    @Override
    public void createEpic(Epic epic) throws IllegalStateException {
        epic.setId(id++);
        epics.put(epic.getId(), epic);
        prioritizedTasks.add(epic);
    }

    @Override
    public void createSubtask(Subtask subtask) throws IllegalStateException {
        validationTimeOverlaps(subtask);
        subtask.setId(id++);
        subtasks.put(subtask.getId(), subtask);
        epics.get(subtask.getEpicId()).addSubtask(subtask);
        setEpicStatus(epics.get(subtask.getEpicId()));
        prioritizedTasks.add(subtask);
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
    public void updateTask(Task task) throws IllegalStateException {
        tasks.put(task.getId(), task);
    }

    @Override
    public void deleteTaskById(int id) {
        prioritizedTasks.remove(tasks.get(id));
        tasks.remove(id);
    }

    @Override
    public void updateEpic(Epic epic) throws IllegalStateException {
        epics.put(epic.getId(), epic);
        setEpicStatus(epic);

    }

    @Override
    public void deleteEpicById(int id) {
        for(Subtask s : epics.get(id).getSubtasks()) {
            subtasks.remove(s.getId());
        }
        epics.get(id).clearAllSubtasks();
        epics.remove(id);
    }

    @Override
    public void updateSubtask(Subtask subtask) throws IllegalStateException {
        subtasks.put(subtask.getId(), subtask);
        System.out.println();
        epics.get(subtask.getEpicId()).setSubtasks(subtask);
        setAllEpicStatus();
    }

    @Override
    public void deleteSubtaskById(int id) {
        int epicId = subtasks.get(id).getEpicId();
        Epic epic = epics.get(epicId);
        prioritizedTasks.remove(subtasks.get(id));
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
    @Override
    public List<Task> getPrioritizedTasks() {
        return new ArrayList<>(prioritizedTasks);
    }

    private void validationTimeOverlaps(Task task) {
        LocalDateTime taskStartTime = task.getStartTime();
        LocalDateTime taskEndTime = task.getEndTime();

        if(prioritizedTasks.isEmpty()) {
            return;
        }

        boolean hasOverlappingTask = prioritizedTasks
                .stream()
                .anyMatch(existingTask -> {
                    LocalDateTime existStartTime = existingTask.getStartTime();
                    LocalDateTime existEndTime = existingTask.getEndTime();

                    return Objects.nonNull(existStartTime) &&
                            Objects.nonNull(existEndTime) &&
                            Objects.nonNull(taskStartTime) &&
                            Objects.nonNull(taskEndTime) &&
                            (taskStartTime.isAfter(existEndTime) &&
                            taskEndTime.isAfter(existEndTime)) ||
                            (taskStartTime.isBefore(existStartTime) &&
                                    taskEndTime.isBefore(existStartTime));
                });

        if (!hasOverlappingTask) {
            throw new IllegalStateException("Ошибка. Временной интервал задачи пересекается с уже существующей задачей.");
        }
    }
}