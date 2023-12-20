package javakanban.http;

import com.google.gson.Gson;
import javakanban.managers.Managers;
import javakanban.server.KVServer;
import javakanban.tasks.Epic;
import javakanban.tasks.Subtask;
import javakanban.tasks.Task;
import managers.TaskManagerTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;

public class HttpTaskManagerTest extends TaskManagerTest<HttpTaskManager> {
    private static KVServer kvServer;
    protected static Gson gson;

    HttpTaskManagerTest() {
        super();
    }

    @BeforeEach
    void setUp() throws IOException {
        kvServer = new KVServer();
        kvServer.start();
        gson = new Gson();
        taskManager = Managers.getDefault();

        task = new Task("Task", "description", 60, LocalDateTime.of(2023, 1, 1, 14, 30));
        epic = new Epic("epic", "description", 60, LocalDateTime.of(2023, 1, 1, 13, 29));
        epic.setId(1);
        subtask = new Subtask("Subtask", "description", epic.getId(), 60, LocalDateTime.of(2023, 1, 1, 15, 31));
    }

    @AfterEach
    void tearDown() {
        kvServer.stop(1);
    }

    @Test
    void shouldSaveAndLoadFromServer() {
        Task task = new Task("Task1", "des");
        taskManager.createTasks(task);
        Epic epic = new Epic("Task1", "des");
        taskManager.createEpic(epic);
        Subtask subtask = new Subtask("Task1", "des", epic.getId());
        taskManager.createSubtask(subtask);

        taskManager.load();
        String resultTask = taskManager.getTaskById(task.getId()).toString();
        String resultEpic = taskManager.getEpicById(epic.getId()).toString();
        String resultSubtask = taskManager.getSubtaskById(subtask.getId()).toString();


        Assertions.assertEquals(task.toString(), resultTask);
        Assertions.assertEquals(epic.toString(), resultEpic);
        Assertions.assertEquals(subtask.toString(), resultSubtask);

    }
}