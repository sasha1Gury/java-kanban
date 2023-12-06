package managers;

import javakanban.managers.FileBackedTasksManager;
import javakanban.tasks.Epic;
import javakanban.tasks.Subtask;
import javakanban.tasks.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBackedTaskManagerTest extends TaskManagerTest<FileBackedTasksManager>{
    @BeforeEach
    void setTaskManager() {
        taskManager = new FileBackedTasksManager(new File("resource/tasksTest.csv"));
    }

    @BeforeEach
    void setUp() {
        task = new Task("epic1", "de");
        epic = new Epic("epic", "descr");
        epic.setId(1);
        subtask = new Subtask("epic1", "de", epic.getId());
    }

    @Test
    public void shouldSaveAndLoadFromFile() throws IOException {
        Path path = Path.of("resource/tasksTest.csv");
        Files.writeString(path, "");

        taskManager.createTasks(task);
        taskManager.createEpic(epic);
        Subtask subtask1 = new Subtask("Subtask", "description", epic.getId());
        taskManager.createSubtask(subtask1);

        taskManager.save();

        FileBackedTasksManager newTaskManager = FileBackedTasksManager.loadFromFile(new File("resource/tasksTest.csv"));

        String resultTask = newTaskManager.getTaskById(task.getId()).toString();
        String resultEpic = newTaskManager.getEpicById(epic.getId()).toString();
        String resultSubtask = newTaskManager.getSubtaskById(subtask1.getId()).toString();


        Assertions.assertEquals(task.toString(), resultTask);
        Assertions.assertEquals(epic.toString(), resultEpic);
        Assertions.assertEquals(subtask1.toString(), resultSubtask);

    }

}
