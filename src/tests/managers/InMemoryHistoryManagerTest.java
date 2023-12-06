package managers;

import javakanban.managers.HistoryManager;
import javakanban.managers.InMemoryHistoryManager;
import javakanban.tasks.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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

    @Test
    void shouldRemoveFromHistoryCentre() {
        task.setId(1);
        Task taskCentre = new Task("centre", "des");
        taskCentre.setId(2);
        Task taskLast = new Task("Last", "des");
        taskLast.setId(3);
        historyManager.addTaskToHistory(task);
        historyManager.addTaskToHistory(taskCentre);
        historyManager.addTaskToHistory(taskLast);
        historyManager.remove(taskCentre.getId());

        List<Task> expected = new ArrayList<>();
        expected.add(task); expected.add(taskLast);

        Assertions.assertArrayEquals(expected.toArray(), historyManager.getHistory().toArray());
    }

    @Test
    void shouldRemoveFromHistoryLast() {
        task.setId(1);
        Task taskCentre = new Task("centre", "des");
        taskCentre.setId(2);
        Task taskLast = new Task("Last", "des");
        taskLast.setId(3);
        historyManager.addTaskToHistory(task);
        historyManager.addTaskToHistory(taskCentre);
        historyManager.addTaskToHistory(taskLast);
        historyManager.remove(taskLast.getId());

        List<Task> expected = new ArrayList<>();
        expected.add(task); expected.add(taskCentre);

        Assertions.assertArrayEquals(expected.toArray(), historyManager.getHistory().toArray());
    }


}