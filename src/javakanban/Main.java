package javakanban;

import javakanban.managers.Managers;
import javakanban.managers.TaskManager;
import javakanban.tasks.Epic;
import javakanban.tasks.Subtask;
import javakanban.tasks.Task;


public class Main {
    static TaskManager taskManager = Managers.getDefault();
    public static void main(String[] args) {
/*
        Epic epic = new Epic("epc1", "description");
        taskManager.createEpic(epic);
        Epic epic1 = new Epic("epc2", "description");
        taskManager.createEpic(epic1);

        Subtask subtask = new Subtask("sub1", "description", epic.getId());
        taskManager.createSubtask(subtask);
        Subtask subtask1 = new Subtask("sub2", "description", epic.getId());
        taskManager.createSubtask(subtask1);
        Subtask subtask2 = new Subtask("sub2", "description", epic.getId());
        taskManager.createSubtask(subtask2);

        Task task = new Task("tsk1", "description");
        taskManager.createTasks(task);
        Task task2 = new Task("tsk2", "description");
        taskManager.createTasks(task2);

        System.out.println(taskManager.getTaskById(task.getId()));
        System.out.println(taskManager.getTaskById(task2.getId()));
        System.out.println(taskManager.getTaskById(task2.getId()));
        System.out.println(taskManager.getTaskById(task.getId()));
        System.out.println(taskManager.getEpicById(epic.getId()));
        System.out.println(taskManager.getEpicById(epic1.getId()));
        taskManager.deleteEpicById(epic.getId());

        System.out.println("History === ==== ==== === == == == ");

        System.out.println(taskManager.getHistory().toString());
 */
    }
}
