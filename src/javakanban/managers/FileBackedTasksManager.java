package javakanban.managers;

import javakanban.tasks.Task;

import java.io.File;

public class FileBackedTasksManager extends InMemoryTaskManager implements TaskManager {
    private File file;
    public FileBackedTasksManager(File file) {
        this.file = file;
    }

    private void save() {

    }

    @Override
    public void createTasks(Task task) {
        super.createTasks(task);
        save();
    }
}
