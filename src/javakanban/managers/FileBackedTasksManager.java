package javakanban.managers;

import javakanban.exception.ManagerSaveException;
import javakanban.tasks.Epic;
import javakanban.tasks.Subtask;
import javakanban.tasks.Task;

import java.io.*;
import java.util.List;


public class FileBackedTasksManager extends InMemoryTaskManager implements TaskManager {
    private final File file;
    public FileBackedTasksManager(File file) {
        this.file = file;
    }

    private void save() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write("id,type,name,status,description,epic" + "\n");
            List<Task> tasks = getListTasks();
            List<Epic> epics = getListEpics();
            List<Subtask> subtasks = getListSubtasks();
            List<Task> history = getHistory();
            for (Task t: tasks) {
                bufferedWriter.write(t.toString() + "\n");
            }
            for (Epic e: epics) {
                bufferedWriter.write(e.toString() + "\n");
            }
            for (Subtask s: subtasks) {
                bufferedWriter.write(s.toString() + "\n");
            }
            bufferedWriter.newLine();
            for (Task h: history) {
                bufferedWriter.write(h.getId() + ",");
            }
        } catch (IOException e) {
            throw new ManagerSaveException("Can't load from file - " + file.getName(), e);
        }
    }

    @Override
    public void createTasks(Task task) {
        super.createTasks(task);
        save();
    }

    @Override
    public void createEpic(Epic epic) {
        super.createEpic(epic);
        save();
    }

    @Override
    public void createSubtask(Subtask subtask) {
        super.createSubtask(subtask);
        save();
    }

    @Override
    public Task getTaskById(int id) {
        Task task = super.getTaskById(id);
        save();
        return task;
    }

    @Override
    public Epic getEpicById(int id) {
        Epic epic = super.getEpicById(id);
        save();
        return epic;
    }

    @Override
    public Subtask getSubtaskById(int id) {
        Subtask subtask = super.getSubtaskById(id);
        save();
        return subtask;
    }

    @Override
    public void removeTasks() {
        super.removeTasks();
        save();
    }

    @Override
    public void removeEpics() {
        super.removeEpics();
        save();
    }

    @Override
    public void removeSubtasks() {
        super.removeSubtasks();
        save();
    }

    @Override
    public void updateTask(Task task) {
        super.updateTask(task);
        save();
    }

    @Override
    public void deleteTaskById(int id) {
        super.deleteTaskById(id);
        save();
    }

    @Override
    public void updateEpic(Epic epic) {
        super.updateEpic(epic);
        save();
    }

    @Override
    public void deleteEpicById(int id) {
        super.deleteEpicById(id);
        save();
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        super.updateSubtask(subtask);
        save();
    }

    @Override
    public void deleteSubtaskById(int id) {
        super.deleteSubtaskById(id);
        save();
    }
}
