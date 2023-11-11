package javakanban.managers;

import javakanban.tasks.Epic;
import javakanban.tasks.Subtask;
import javakanban.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface TaskManager {

    public void createTasks(Task task);

    public void createEpic(Epic epic);

    public void createSubtask(Subtask subtask);

    public List<Task> getListTasks();

    public List<Epic> getListEpics();

    public List<Subtask> getListSubtasks();

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

    public List<Subtask> getListSubtasksByEpicId(int id);

    public List<Task> getHistory();
}