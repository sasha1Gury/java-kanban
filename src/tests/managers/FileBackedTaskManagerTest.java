package managers;

import javakanban.managers.FileBackedTasksManager;
import javakanban.managers.InMemoryTaskManager;
import javakanban.tasks.Epic;
import javakanban.tasks.Subtask;
import javakanban.tasks.Task;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;

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
}
