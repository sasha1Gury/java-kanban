package managers;

import javakanban.managers.InMemoryTaskManager;

import javakanban.tasks.Epic;
import javakanban.tasks.Subtask;
import javakanban.tasks.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;


public class InMemoryTaskManagerTest extends TaskManagerTest<InMemoryTaskManager> {


    @BeforeEach
    void setUp() {
        taskManager = new InMemoryTaskManager();
        task = new Task("Task", "description", 60, LocalDateTime.of(2023, 1, 1, 14, 30));
        epic = new Epic("epic", "description", 60, LocalDateTime.of(2023, 1, 1, 13, 29));
        epic.setId(1);
        subtask = new Subtask("Subtask", "description", epic.getId(), 60, LocalDateTime.of(2023, 1, 1, 15, 31));
    }

    @Test
    void shouldGetPrioritizedTasks() {
        Task task1 = new Task("Task", "description", 60, LocalDateTime.of(2023, 1, 1, 14, 30));
        Task task2 = new Task("Task", "description", 60, LocalDateTime.of(2023, 1, 1, 13, 29));
        Task task3 = new Task("Task", "description", 60, LocalDateTime.of(2023, 1, 1, 15, 31));

        taskManager.createTasks(task1);
        taskManager.createTasks(task2);
        taskManager.createTasks(task3);

        List<Task> prioritizedTasks = taskManager.getPrioritizedTasks();

        Assertions.assertEquals(task2, prioritizedTasks.get(0));
        Assertions.assertEquals(task1, prioritizedTasks.get(1));
        Assertions.assertEquals(task3, prioritizedTasks.get(2));
    }

    @Test
    public void testValidationTimeOverlaps() {
        Task task1 = new Task("Task", "description", 15, LocalDateTime.of(2023, 1, 1, 14, 30));
        Task task2 = new Task("Task", "description", 80, LocalDateTime.of(2023, 1, 1, 13, 30));
        taskManager.createTasks(task1);

        Assertions.assertThrows(IllegalStateException.class, () -> taskManager.createTasks(task2));
    }
}
