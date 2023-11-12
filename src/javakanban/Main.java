package javakanban;

import javakanban.managers.FileBackedTasksManager;
import javakanban.managers.Managers;
import javakanban.managers.TaskManager;
import javakanban.tasks.Epic;
import javakanban.tasks.Subtask;
import javakanban.tasks.Task;
import javakanban.tasks.Type;
import javakanban.tasks.Status;

import java.io.File;

public class Main {
    static TaskManager taskManager = Managers.getDefault();
    static FileBackedTasksManager fileBackedTasksManager = new FileBackedTasksManager(new File("resource/tasks.csv"));
    /*public static void main(String[] args) {
        Epic epic = new Epic("epc1", "description");
        fileBackedTasksManager.createEpic(epic);
        //taskManager.createEpic(epic);
        //Epic epic1 = new Epic("epc2", "description");
        //taskManager.createEpic(epic1);
        Subtask subtask = new Subtask("sub1", "description", epic.getId());
        fileBackedTasksManager.createSubtask(subtask);
        //taskManager.createSubtask(subtask);
        //Subtask subtask1 = new Subtask("sub2", "description", epic.getId());
        //taskManager.createSubtask(subtask1);
        //Subtask subtask2 = new Subtask("sub2", "description", epic.getId());
        //taskManager.createSubtask(subtask2);
        Task task = new Task("tsk1", "description");
        //taskManager.createTasks(task);
        //Task task2 = new Task("tsk2", "description");
        //taskManager.createTasks(task2);
        fileBackedTasksManager.createTasks(task);
        Task task1 = new Subtask(2, "sub task", "description", Status.NEW ,1);
        fileBackedTasksManager.createSubtask((Subtask) task1);
        System.out.println(fileBackedTasksManager.getTaskById(task.getId()));
        //System.out.println(taskManager.getTaskById(task2.getId()));
        //System.out.println(taskManager.getTaskById(task2.getId()));
        //System.out.println(taskManager.getTaskById(task.getId()));
        System.out.println(fileBackedTasksManager.getEpicById(epic.getId()));
        //System.out.println(taskManager.getEpicById(epic1.getId()));
        System.out.println(fileBackedTasksManager.getSubtaskById(subtask.getId()));
        //taskManager.deleteEpicById(epic.getId());
        //System.out.println("History === ==== ==== === == == == ");
        //System.out.println(taskManager.getHistory().toString());
    }*/
}
