import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Subtask> subtasks = new ArrayList<>();

    public Epic(String taskName, String description){
        super(taskName, description);
        this.status = "NEW";
    }

    @Override
    public String toString() {
        return ("Название эпика - " + taskName + " Описание эпика - " + description  + " Status - " + status
                + " все подзадачи - " + subtasks.toString());
    }

    public boolean isDone() {
        int flag = 0;
        for (Subtask i : subtasks) {
            if (!i.status.equals("DONE")) {
                flag += 1;
            }
        }
        return flag == 0;
    }

    public boolean isNew() {
        int flag = 0;
        for (Subtask i : subtasks) {
            if (!i.status.equals("NEW")) {
                flag += 1;
            }
        }
        return flag == 0;
    }

    public ArrayList<Subtask> getSubtasks() {
        return subtasks;
    }

    public void addSubtasks(Subtask subtask) {
        this.subtasks.add(subtask);
    }

    public void setSubtasks(Subtask subtask) {
        this.subtasks.set(subtask.getId() ,subtask);
    }
}
