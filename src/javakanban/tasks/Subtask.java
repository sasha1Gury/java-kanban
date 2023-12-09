package javakanban.tasks;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Subtask extends Task {
    private int epicId;

    public Subtask(String taskName, String description, int epicId) {
        super(taskName, description);
        this.epicId = epicId;
        this.status = Status.NEW;
        this.type = Type.SUBTASK;
    }

    public Subtask(String taskName, String description, int epicId, int duration, LocalDateTime startTime) {
        super(taskName, description, duration, startTime);
        this.epicId = epicId;
        this.status = Status.NEW;
        this.type = Type.SUBTASK;
    }

    public Subtask(Integer id, String taskName, String description, Status status, int epicId) {
        super(taskName, description);
        this.id = id;
        this.status = status;
        this.type = Type.SUBTASK;
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        String startTimeString = startTime.format(DateTimeFormatter.ofPattern("yyy.MM.dd HH:mm"));
        String durationString = String.valueOf(duration.toSeconds());
        return (id + "," + type + "," + taskName + "," + status.toString() + "," + description + ","
                + durationString + "," + startTimeString + "," + getEpicId());
    }

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }
}
