package javakanban.managers;

import javakanban.tasks.Epic;
import javakanban.tasks.Subtask;
import javakanban.tasks.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class FileBackedTasksManager extends InMemoryTaskManager implements TaskManager {
    private final File file;
    public FileBackedTasksManager(File file) {
        this.file = file;
    }

    private void save() throws IOException {
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write("id,type,name,status,description,epic" + "\n");
        }
        try(FileWriter fileWriter = new FileWriter(file, true)) {
            List<Task> tasks = getListTasks();
            List<Epic> epics = getListEpics();
            List<Subtask> subtasks = getListSubtasks();
            List<Task> history = getHistory();
            for (Task t: tasks) {
                fileWriter.write(t.toString() + "\n");
            }
            for (Epic e: epics) {
                fileWriter.write(e.toString() + "\n");
            }
            for (Subtask s: subtasks) {
                fileWriter.write(s.toString() + "\n");
            }
            for (Task h: history) {
                fileWriter.write(h.getId() + ",");
            }
        }
    }

    @Override
    public void createTasks(Task task) {
        super.createTasks(task);
        try {
            save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createEpic(Epic epic) {
        super.createEpic(epic);
        try {
            save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createSubtask(Subtask subtask) {
        super.createSubtask(subtask);
        try {
            save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
