package javakanban.tasks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EpicStatusTest {
    static Epic epic;
    static Subtask subtask;
    static Subtask subtask1;

    @BeforeEach
    void beforeEach() {
        epic = new Epic("epc1", "description");
        subtask = new Subtask("sub1", "description", epic.getId());
        subtask1 = new Subtask("sub1", "description", epic.getId());
    }

    @Test
    public void statusShouldBeNewWhenSubtasksIsEmpty() {
        Status expected = Status.NEW;

        assertEquals(expected, epic.getStatus());
    }

    @Test
    public void statusShouldBeNewWhenSubtasksIsNew() {
        epic.addSubtask(subtask);
        epic.addSubtask(subtask1);

        Status expected = Status.NEW;

        assertEquals(expected, epic.getStatus());
    }

    @Test
    public void statusShouldBeDoneWhenSubtasksIsDone() {
        subtask.setStatusDone();
        epic.addSubtask(subtask);
        subtask1.setStatusDone();
        epic.addSubtask(subtask1);

        Status expected = Status.DONE;

        assertEquals(expected, epic.getStatus());
    }

    @Test
    public void statusShouldBeInProgressWhenSubtasksIsDoneAndNew() {
        subtask.setStatusDone();
        epic.addSubtask(subtask);
        subtask1.setStatusInProgress();
        epic.addSubtask(subtask1);

        Status expected = Status.IN_PROGRESS;

        assertEquals(expected, epic.getStatus());
    }

    @Test
    public void statusShouldBeInProgressWhenSubtasksIsInProgress() {
        subtask.setStatusInProgress();
        epic.addSubtask(subtask);
        subtask1.setStatusInProgress();
        epic.addSubtask(subtask1);

        Status expected = Status.IN_PROGRESS;

        assertEquals(expected, epic.getStatus());
    }
}