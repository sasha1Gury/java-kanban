package javakanban.tasks;

import java.util.ArrayList;

public class Epic extends Task {
    private final ArrayList<Subtask> subtasks = new ArrayList<>();

    public Epic(String taskName, String description){
        super(taskName, description);
        this.status = Status.NEW;
        this.type = Type.EPIC;
    }

    public Epic(Integer id, String taskName, String description, Status status) {
        this.id = id;
        this.taskName = taskName;
        this.description = description;
        this.status = status;
        this.type = Type.EPIC;
    }


    public boolean isDone() {
        int flag = 0;
        for (Subtask i : subtasks) {
            if (!(i.status == Status.DONE)) {
                flag += 1;
            }
        }
        return flag == 0;
    }

    public boolean isNew() {
        int flag = 0;
        for (Subtask i : subtasks) {
            if (!(i.status == Status.NEW)) {
                flag += 1;
            }
        }
        return flag == 0;
    }

    public ArrayList<Subtask> getSubtasks() {
        return subtasks;
    }

    public void addSubtask(Subtask subtask) {
        this.subtasks.add(subtask);
        setEpicStatus(this);
    }

    public void setSubtasks(Subtask subtask) {
        this.subtasks.set(subtask.getId()-1 ,subtask);
    }

    public void removeSubtaskById(int id) {
        subtasks.remove(id-1);
    }

    public void clearAllSubtasks() {
        this.subtasks.clear();
    }

    public void clearSubtasksByEpic(int id) {
        subtasks.removeIf(subtask -> subtask.getEpicId() == id);
    }

    private void setEpicStatus(Epic epic) {
        if(epic.getSubtasks().isEmpty() || epic.isNew()) {
            epic.status = Status.NEW;
        } else if(epic.isDone()) {
            epic.status = Status.DONE;
        } else epic.status = Status.IN_PROGRESS;
    }
}
