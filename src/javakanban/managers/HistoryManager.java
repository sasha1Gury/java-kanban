package javakanban.managers;

import javakanban.tasks.Task;

import java.util.List;

public interface HistoryManager {
    List<Task> getHistory();

    void addTaskToHistory(Task task);
}
