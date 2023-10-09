package javakanban.managers;

import javakanban.managers.HistoryManager;
import javakanban.managers.InMemoryHistoryManager;
import javakanban.managers.InMemoryTaskManager;

public class Managers {
    public static TaskManager getDefault() {
        return new InMemoryTaskManager();
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}
