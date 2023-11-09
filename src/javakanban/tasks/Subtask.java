package javakanban.tasks;

public class Subtask extends Task {
    private int epicId;

    public Subtask(String taskName, String description, int epicId) {
        super(taskName, description);
        this.epicId = epicId;
        this.status = Status.NEW;
        this.type = Type.SUBTASK;
    }

    @Override
    public String toString() {
        return (id + "," + type + "," + taskName + "," + status.toString() + "," + description + "," + getEpicId());
    }

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }
}
