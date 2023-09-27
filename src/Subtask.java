public class Subtask extends Task {
    public Subtask(String taskName, String description) {
        super(taskName, description);
        this.status = "NEW";
    }

    @Override
    public String toString() {
        return "Подзадача - " + taskName + " описание - " + description + " Status - " + status;
    }
}
