package managers;

import javakanban.managers.HistoryManager;
import javakanban.managers.InMemoryHistoryManager;
import javakanban.tasks.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class InMemoryHistoryManagerTest {

    private HistoryManager historyManager;
    private Task task;

    @BeforeEach
    void setUp() {
        historyManager = new InMemoryHistoryManager();
        task = new Task("Task1", "description");
    }

    @Test
    void shouldAddToHistoryAndGetFromHistory() {
        historyManager.addTaskToHistory(task);
        Task resultTask = historyManager.getHistory().get(0);

        Assertions.assertEquals(task.toString(), resultTask.toString());
    }

    @Test
    void shouldAddOnlyOneTaskWhenTasksDuplicated() {
        historyManager.addTaskToHistory(task);
        historyManager.addTaskToHistory(task);
        List<Task> result = historyManager.getHistory();

        Assertions.assertEquals(1, result.size());
    }

    @Test
    void shouldRemoveFromHistory() {
        historyManager.addTaskToHistory(task);
        historyManager.remove(task.getId());

        Assertions.assertEquals(0, historyManager.getHistory().size());
    }


}