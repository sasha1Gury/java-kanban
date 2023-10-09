package javakanban.tasks;

public class Subtask extends Task {
    private int epicId;

    public Subtask(String taskName, String description, int epicId) {
        super(taskName, description);
        this.epicId = epicId;
        this.status = Status.NEW;
    }

    @Override
    public String toString() {
        return "Подзадача - " + taskName + " описание - " + description + " статус - " + status.toString() + " ИД эпика " + epicId;
    }

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }
}
