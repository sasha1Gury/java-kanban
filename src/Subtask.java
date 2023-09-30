public class Subtask extends Task {

    private int epicId;
    public Subtask(String taskName, String description) {
        super(taskName, description);
        this.status = "NEW";
    }

    @Override
    public String toString() {
        return "Подзадача - " + taskName + " описание - " + description + " Status - " + status;
    }

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }
}
