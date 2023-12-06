package javakanban.tasks;

import java.time.Duration;
import java.time.LocalDateTime;

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

    public Task(Integer id, String taskName, String description, Status status) {
        this.id = id;
        this.taskName = taskName;
        this.description = description;
        this.status = status;
        this.type = Type.TASK;
    }

    public Task(String taskName, String description) {
        this.taskName = taskName;
        this.description = description;
        this.status = Status.NEW;
        this.type = Type.TASK;
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
        return (id + "," + type + "," + taskName + "," + status.toString() + "," + description + ",");
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
