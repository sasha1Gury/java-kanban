package javakanban.managers;

import javakanban.tasks.Epic;
import javakanban.tasks.Subtask;
import javakanban.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface TaskManager {

    void createTasks(Task task);

    void createEpic(Epic epic);

    void createSubtask(Subtask subtask);

    List<Task> getListTasks();

    List<Epic> getListEpics();

    List<Subtask> getListSubtasks();

    Task getTaskById(int id);

    Epic getEpicById(int id);

    Subtask getSubtaskById(int id);

    void removeTasks();

    void removeEpics();

    void removeSubtasks();

    void updateTask(Task task);

    void deleteTaskById(int id);

    void updateEpic(Epic epic);

    void deleteEpicById(int id);

    void updateSubtask(Subtask subtask);

    void deleteSubtaskById(int id);

    List<Subtask> getListSubtasksByEpicId(int id);

    List<Task> getHistory();
}