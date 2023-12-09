package javakanban.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static java.time.LocalDate.*;

public class TasksTimeTest {

    @Test
    public void shouldGetEndDateTime() {
        Task task = new Task("Task", "description", 60, LocalDateTime.now());
        LocalDateTime result = task.getEndTime().truncatedTo(ChronoUnit.MINUTES);
        LocalDateTime expected = LocalDateTime.now().plus(Duration.ofMinutes(60)).truncatedTo(ChronoUnit.MINUTES);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void epicStartTimeShouldBeBasedOnSubtaskTime() {
        Epic epic = new Epic("Epic", "description");
        Assertions.assertEquals(Duration.ofMinutes(0), epic.getDuration());
        epic.setId(1);
        Subtask subtask = new Subtask("Subtask", "description", epic.getId(), 60,  LocalDateTime.of(2023, 1, 1, 14, 30));
        subtask.setId(0);
        Subtask subtask1 = new Subtask("Subtask1", "description", epic.getId(), 60, LocalDateTime.of(2020, 1, 1, 14, 30));
        subtask.setId(1);
        epic.addSubtask(subtask); epic.addSubtask(subtask1);

        LocalDateTime result = epic.getStartTime().truncatedTo(ChronoUnit.MINUTES);
        LocalDateTime expected = LocalDateTime.of(2020, 1, 1, 14, 30).truncatedTo(ChronoUnit.MINUTES);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void epicShouldCountThatDurationByDurationSubtasks() {
        Epic epic = new Epic("Epic", "description");
        Assertions.assertEquals(Duration.ofMinutes(0), epic.getDuration());
        epic.setId(1);
        Subtask subtask = new Subtask("Subtask", "description", epic.getId(), 60, LocalDateTime.now());
        subtask.setId(0);
        Subtask subtask1 = new Subtask("Subtask1", "description", epic.getId(), 60, LocalDateTime.now());
        subtask.setId(1);
        epic.addSubtask(subtask); epic.addSubtask(subtask1);

        LocalDateTime result = epic.getEndTime().truncatedTo(ChronoUnit.MINUTES);
        LocalDateTime expected = LocalDateTime.now().plus(Duration.ofMinutes(120)).truncatedTo(ChronoUnit.MINUTES);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void ifSubtasksIsEmptyEpicDurationShouldBe0() {
        Epic epic = new Epic("Epic", "description");

        LocalDateTime result = epic.getEndTime().truncatedTo(ChronoUnit.MINUTES);
        LocalDateTime expected = LocalDateTime.now().plus(Duration.ofMinutes(0)).truncatedTo(ChronoUnit.MINUTES);

        Assertions.assertEquals(expected, result);
    }


}
