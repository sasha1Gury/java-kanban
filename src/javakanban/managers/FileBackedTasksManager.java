package javakanban.managers;

import javakanban.exception.ManagerSaveException;
import javakanban.tasks.Epic;
import javakanban.tasks.Subtask;
import javakanban.tasks.Task;

import java.io.*;
import java.nio.file.Files;
import java.util.List;


public class FileBackedTasksManager extends InMemoryTaskManager implements TaskManager {
    private final File file;
    public FileBackedTasksManager(File file) {
        this.file = file;
    }

    public static void main(String[] args) {
        FileBackedTasksManager fileBackedTasksManager = FileBackedTasksManager
                .loadFromFile(new File("resource/tasks.csv"));
        System.out.println(fileBackedTasksManager);

    }

    public static FileBackedTasksManager loadFromFile(File file) {
        final FileBackedTasksManager fileBackedTasksManager = new FileBackedTasksManager(file);

        try {
            final String csv = Files.readString(file.toPath());
            final String[] lines = csv.split("\n", -1);
            int maxId = -1;

            for (int i = 1; i < lines.length; i++) {
                if (lines[i].isEmpty()) break;
                Task task = CSVTaskFormat.taskFromString(lines[i]);
                if (task.getId() > maxId) maxId = task.getId()+1;
                fileBackedTasksManager.putTasksByTypes(task);
            }
            if (!lines[lines.length - 1].isEmpty()) {
                String history = lines[lines.length - 1];
                for (Integer id : CSVTaskFormat.historyFromString(history)) {
                    Task task = fileBackedTasksManager.findTask(id);
                    fileBackedTasksManager.historyManager.addTaskToHistory(task);
                }
            }

            for(Subtask subtask: fileBackedTasksManager.getListSubtasks()) {
                Epic epic = (Epic) fileBackedTasksManager.findTask(subtask.getEpicId());
                epic.addSubtask(subtask);
            }

            fileBackedTasksManager.setId(maxId);

        } catch (IOException e) {
            throw new ManagerSaveException("Can't read from file - " + file.getName(), e);
        }
        return fileBackedTasksManager;
    }

    protected Task findTask(int id) {
        Task task = tasks.get(id);
        if(task != null) return task;
        Subtask subtask = subtasks.get(id);
        if(subtask != null) return subtask;
        return epics.get(id);
    }
    private void putTasksByTypes(Task task) {
        switch (task.getType()){
            case TASK:
                tasks.put(task.getId(), task);
                break;
            case EPIC:
                epics.put(task.getId(), (Epic)task);
                break;
            case SUBTASK:
                subtasks.put(task.getId(), (Subtask) task);
                break;
        }
    }

    public void save() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write("id,type,name,status,description,duration,time,epic" + "\n");
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
            bufferedWriter.write("\n");
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
