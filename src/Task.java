public class Task {
    String taskName;
    String description;
    String status;
    int id;

    public Task() {

    }

    public Task(String taskName, String description) {
        this.taskName = taskName;
        this.description = description;
        this.status = "NEW";
    }

    @Override
    public String toString() {
        return ("Название задачи - " + taskName + " Описание задачи - " + description + " Status - " + status);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setStatusInProgress() {
        this.status = "IN_PROGRESS";
    }

    public void setStatusDone() {
        this.status = "DONE";
    }
}
