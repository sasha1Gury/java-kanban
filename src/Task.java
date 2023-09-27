public class Task {
    String taskName;
    String description;
    String[] status = {"NEW", "IN_PROGRESS", "DONE"};

    public Task() {

    }

    public Task(String taskName, String description) {
        this.taskName = taskName;
        this.description = description;
    }
    @Override
    public String toString() {
        return ("Название задачи - " + taskName + " Описание задачи - " + description);
    }

}
