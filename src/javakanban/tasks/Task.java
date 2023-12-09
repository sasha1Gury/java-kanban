package javakanban.tasks;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String taskName;
    protected String description;
    public Status status;
    protected int id;
    protected Duration duration;
    protected LocalDateTime startTime;
    protected Type type;

    public Task() {

    }

    public Task(Integer id, String taskName, String description, Status status, int duration, LocalDateTime startTime) {
        this.id = id;
        this.taskName = taskName;
        this.description = description;
        this.status = status;
        this.type = Type.TASK;
        this.duration = Duration.ofMinutes(duration);
        this.startTime = startTime;
    }

    public Task(String taskName, String description) {
        this.taskName = taskName;
        this.description = description;
        this.status = Status.NEW;
        this.type = Type.TASK;
        this.duration = Duration.ofMinutes(0);
        this.startTime = LocalDateTime.now();
    }

    public Task(String taskName, String description, int duration, LocalDateTime startTime) {
        this.taskName = taskName;
        this.description = description;
        this.duration = Duration.ofMinutes(duration);
        this.startTime = startTime;
        this.status = Status.NEW;
        this.type = Type.TASK;
    }

    @Override
    public String toString() {
        String startTimeString = startTime.format(DateTimeFormatter.ofPattern("yyy.MM.dd HH:mm"));
        String durationString = String.valueOf(duration.toSeconds());
        return (id + "," + type + "," + taskName + "," + status.toString() + "," + description + ","
                + durationString + "," + startTimeString);
    }

    public LocalDateTime getEndTime() {
        if (startTime == null) return null;
        else return startTime.plus(duration);
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setStatusInProgress() {
        this.status = Status.IN_PROGRESS;
    }

    public void setStatusDone() {
        this.status = Status.DONE;
    }

    public Status getStatus() {
        return this.status;
    }

    public Type getType() {
        return this.type;
    }
}
