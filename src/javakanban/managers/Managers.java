package javakanban.managers;

import javakanban.http.HttpTaskManager;
import javakanban.managers.HistoryManager;
import javakanban.managers.InMemoryHistoryManager;
import javakanban.managers.InMemoryTaskManager;

import java.io.File;

public class Managers {
    public static TaskManager getDefaultTaskManager() {
        return new InMemoryTaskManager();
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }

    public static FileBackedTasksManager getDefaultFileManager() {
        return new FileBackedTasksManager(new File("resource/tasks.csv"));
    }

    public static HttpTaskManager getDefault() {
        return new HttpTaskManager("http://localhost:8078");
    }
}
