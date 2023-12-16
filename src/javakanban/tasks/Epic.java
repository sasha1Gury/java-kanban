package javakanban.tasks;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Epic extends Task {
    private final Map<Integer, Subtask> subtasks = new HashMap<>();

    public Epic(String taskName, String description){
        super(taskName, description);
        this.status = Status.NEW;
        this.type = Type.EPIC;
    }

    public Epic(Integer id, String taskName, String description, Status status, int duration, LocalDateTime startTime) {
        super(taskName, description, duration, startTime);
        this.id = id;
        this.status = status;
        this.type = Type.EPIC;
    }

    public boolean isDone() {
        int flag = 0;
        for (Subtask i : subtasks.values()) {
            if (!(i.status == Status.DONE)) {
                flag += 1;
            }
        }
        return flag == 0;
    }

    public boolean isNew() {
        int flag = 0;
        for (Subtask i : subtasks.values()) {
            if (!(i.status == Status.NEW)) {
                flag += 1;
            }
        }
        return flag == 0;
    }

    public List<Subtask> getSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    public void addSubtask(Subtask subtask) {
        this.subtasks.put(subtask.getId(), subtask);
        setEpicStatus(this);
        if (subtask.getStartTime().isBefore(this.startTime)) {
            this.startTime = subtask.getStartTime();
        }
    }

    @Override
    public LocalDateTime getEndTime() {
        Duration totalDuration = this.getSubtasks().stream()
                .map(Subtask::getDuration)
                .reduce(Duration.ZERO, Duration::plus);
        this.duration = totalDuration;
        return this.startTime.plus(totalDuration);
    }

    public void setSubtasks(Subtask subtask) {
        this.subtasks.put(subtask.getId(), subtask);
        setEpicStatus(this);
    }

    public void removeSubtaskById(int id) {
        this.subtasks.remove(id);
    }

    public void clearAllSubtasks() {
        this.subtasks.clear();
    }

    private void setEpicStatus(Epic epic) {
        if(epic.getSubtasks().isEmpty() || epic.isNew()) {
            epic.status = Status.NEW;
        } else if(epic.isDone()) {
            epic.status = Status.DONE;
        } else epic.status = Status.IN_PROGRESS;
    }
}
