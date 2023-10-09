package javakanban.managers;

import javakanban.tasks.Task;

import java.util.LinkedList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private static final int MAX_COUNT = 10;
    private final List<Task> history = new LinkedList<>();

    @Override
    public List<Task> getHistory() {
        return history;
    }

    @Override
    public void addTaskToHistory(Task task){
        history.add(task);
        if(history.size() > MAX_COUNT) {
            history.remove(0);
        }
    }
}
