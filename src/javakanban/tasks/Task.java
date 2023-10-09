package javakanban.tasks;

public class Task {
    protected String taskName;
    protected String description;
    public Status status;
    protected int id;

    public Task() {

    }

    public Task(String taskName, String description) {
        this.taskName = taskName;
        this.description = description;
        this.status = Status.NEW;
    }

    @Override
    public String toString() {
        return ("Название задачи - " + taskName + " Описание задачи - " + description + " Статус - " + status.toString());
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
}
