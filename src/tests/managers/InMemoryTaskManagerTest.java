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

    @Test
    void shouldGetPrioritizedTasks() {
        Task task1 = new Task("Task", "description", 60, LocalDateTime.of(2023, 1, 1, 14, 30));
        Task task2 = new Task("Task", "description", 60, LocalDateTime.of(2023, 1, 1, 13, 30));
        Task task3 = new Task("Task", "description", 60, LocalDateTime.of(2023, 1, 1, 15, 30));

        taskManager.createTasks(task1);
        taskManager.createTasks(task2);
        taskManager.createTasks(task3);

        List<Task> prioritizedTasks = taskManager.getPrioritizedTasks();

        // Проверяем, что порядок сортировки соответствует ожиданиям
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
