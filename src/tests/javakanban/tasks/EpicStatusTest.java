package javakanban.tasks;

import javakanban.managers.InMemoryTaskManager;
import javakanban.managers.TaskManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EpicStatusTest {
    static TaskManager taskManager;
    static Epic epic;

    @BeforeAll
    static void setUp() {
        taskManager = new InMemoryTaskManager();
        epic = new Epic("epc1", "description");
        taskManager.createEpic(epic);
    }

    @Test
    public void statusShouldBeNewWhenSubtasksIsEmpty() {
        Status expected = Status.NEW;

        assertEquals(expected, taskManager.getEpicById(epic.getId()).getStatus());
    }

    @Test
    public void statusShouldBeNewWhenSubtasksIsNew() {
        Subtask subtask = new Subtask("sub1", "description", epic.getId());
        taskManager.createSubtask(subtask);
        Subtask subtask1 = new Subtask("sub2", "description", epic.getId());
        taskManager.createSubtask(subtask1);

        Status expected = Status.NEW;

        assertEquals(expected, taskManager.getEpicById(epic.getId()).getStatus());
    }

    @Test
    public void statusShouldBeDoneWhenSubtasksIsDone() {
        Subtask subtask = new Subtask("sub1", "description", epic.getId());
        subtask.setStatusDone();
        taskManager.createSubtask(subtask);
        Subtask subtask1 = new Subtask("sub2", "description", epic.getId());
        subtask1.setStatusDone();
        taskManager.createSubtask(subtask1);

        Status expected = Status.DONE;

        assertEquals(expected, taskManager.getEpicById(epic.getId()).getStatus());
    }

    @Test
    public void statusShouldBeInProgressWhenSubtasksIsDoneAndNew() {
        Subtask subtask = new Subtask("sub1", "description", epic.getId());
        subtask.setStatusDone();
        taskManager.createSubtask(subtask);
        Subtask subtask1 = new Subtask("sub2", "description", epic.getId());
        subtask1.setStatusInProgress();
        taskManager.createSubtask(subtask1);

        Status expected = Status.IN_PROGRESS;

        assertEquals(expected, taskManager.getEpicById(epic.getId()).getStatus());
    }

    @Test
    public void statusShouldBeInProgressWhenSubtasksIsInProgress() {
        Subtask subtask = new Subtask("sub1", "description", epic.getId());
        subtask.setStatusInProgress();
        taskManager.createSubtask(subtask);
        Subtask subtask1 = new Subtask("sub2", "description", epic.getId());
        subtask1.setStatusInProgress();
        taskManager.createSubtask(subtask1);

        Status expected = Status.IN_PROGRESS;

        assertEquals(expected, taskManager.getEpicById(epic.getId()).getStatus());
    }
}