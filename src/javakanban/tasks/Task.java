package javakanban.tasks;

public class Task {
    protected String taskName;
    protected String description;
    public Status status;
    protected int id;
    protected Type type;

    public Task() {

    }

    public Task(String taskName, String description) {
        this.taskName = taskName;
        this.description = description;
        this.status = Status.NEW;
        this.type = Type.TASK;
    }

    @Override
    public String toString() {
        return (id + "," + type + "," + taskName + "," + status.toString() + "," + description + "," + "");
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
