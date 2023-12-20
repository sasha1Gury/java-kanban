package javakanban.http;

import com.google.gson.Gson;
import javakanban.server.KVServer;
import javakanban.tasks.Epic;
import javakanban.tasks.Subtask;
import javakanban.tasks.Task;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HttpTaskManagerTest {
    private static KVServer kvServer;
    protected static Gson gson;
    private static HttpTaskManager httpTaskManager;

    HttpTaskManagerTest() {
        super();
    }

    @BeforeEach
    void setUp() throws IOException {
        kvServer = new KVServer();
        kvServer.start();
        gson = new Gson();
        httpTaskManager = new HttpTaskManager("http://localhost:8078");
    }

    @AfterEach
    void tearDown() {
        kvServer.stop(1);
    }

    @Test
    void shouldSaveAndLoadFromServer() {
        Task task = new Task("Task1", "des");
        httpTaskManager.createTasks(task);
        Epic epic = new Epic("Task1", "des");
        httpTaskManager.createEpic(epic);
        Subtask subtask = new Subtask("Task1", "des", epic.getId());
        httpTaskManager.createSubtask(subtask);
        List<Task> expectTasks = httpTaskManager.getListTasks();
        List<Epic> expectEpics = httpTaskManager.getListEpics();
        List<Subtask> expectSubtasks = httpTaskManager.getListSubtasks();

        httpTaskManager.load();
        List<Task> resultTasks = httpTaskManager.getListTasks();
        List<Epic> resultEpics = httpTaskManager.getListEpics();
        List<Subtask> resultSubtasks = httpTaskManager.getListSubtasks();

        Assertions.assertArrayEquals(expectTasks.toArray(), resultTasks.toArray());

    }
}