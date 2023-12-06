package managers;

import javakanban.managers.InMemoryTaskManager;

import javakanban.tasks.Epic;
import javakanban.tasks.Subtask;
import javakanban.tasks.Task;
import org.junit.jupiter.api.BeforeEach;


public class InMemoryTaskManagerTest extends TaskManagerTest<InMemoryTaskManager> {

    @BeforeEach
    void setTaskManager() {
        taskManager = new InMemoryTaskManager();
    }

    @BeforeEach
    void setUp() {
        task = new Task("epic1", "de");
        epic = new Epic("epic", "descr");
        epic.setId(1);
        subtask = new Subtask("epic1", "de", epic.getId());
    }
}
