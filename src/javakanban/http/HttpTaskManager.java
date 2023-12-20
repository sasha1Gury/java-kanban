package javakanban.http;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javakanban.managers.FileBackedTasksManager;
import javakanban.tasks.Epic;
import javakanban.tasks.Subtask;
import javakanban.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class HttpTaskManager extends FileBackedTasksManager {
    private final Gson gson;
    private final KVTaskClient client;

    //url = "http://localhost:" + 8078 + "/"

    public HttpTaskManager(String url) {
        super(null);
        gson = new Gson();
        client = new KVTaskClient(url);
    }

    private void addTasks(List<? extends Task> tasks) {
        int maxId = -1;
        for (Task task : tasks) {
            int id = task.getId();
            if (id > maxId) {
                maxId = id;
            }
            putTasksByTypes(task);
            setId(maxId);
        }
    }

    public void load() {
        ArrayList<Task> tasks = gson.fromJson(client.load("tasks"), new TypeToken<ArrayList<Task>>(){}.getType());
        addTasks(tasks);
        ArrayList<Epic> epics = gson.fromJson(client.load("epics"), new TypeToken<ArrayList<Epic>>(){}.getType());
        addTasks(epics);
        ArrayList<Subtask> subtasks = gson.fromJson(client.load("subtasks"), new TypeToken<ArrayList<Task>>(){}.getType());
        addTasks(subtasks);
    }

    @Override
    public void save() {
        String jsonTasks = gson.toJson(getListTasks());
        client.put("tasks", jsonTasks);
        String jsonSubtasks = gson.toJson(getListSubtasks());
        client.put("subtasks", jsonSubtasks);
        String jsonEpics = gson.toJson(getListEpics());
        client.put("epics", jsonEpics);
    }
}
