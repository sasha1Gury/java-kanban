package javakanban;

import javakanban.managers.FileBackedTasksManager;
import javakanban.managers.Managers;
import javakanban.managers.TaskManager;
import javakanban.tasks.Epic;
import javakanban.tasks.Subtask;
import javakanban.tasks.Task;

import java.io.File;
import java.io.IOException;


public class Main {
    static TaskManager taskManager = Managers.getDefault();
    static FileBackedTasksManager fileBackedTasksManager = new FileBackedTasksManager(new File("resource/tasks.csv"));
    public static void main(String[] args) {



        Epic epic = new Epic("epc1", "description");
        fileBackedTasksManager.createEpic(epic);
        //taskManager.createEpic(epic);
        Epic epic1 = new Epic("epc2", "description");
        //taskManager.createEpic(epic1);

        Subtask subtask = new Subtask("sub1", "description", epic.getId());
        fileBackedTasksManager.createSubtask(subtask);

        //taskManager.createSubtask(subtask);
        Subtask subtask1 = new Subtask("sub2", "description", epic.getId());
        //taskManager.createSubtask(subtask1);
        Subtask subtask2 = new Subtask("sub2", "description", epic.getId());
        //taskManager.createSubtask(subtask2);

        Task task = new Task("tsk1", "description");
        //taskManager.createTasks(task);
        Task task2 = new Task("tsk2", "description");
        //taskManager.createTasks(task2);


        fileBackedTasksManager.createTasks(task);




        //System.out.println(taskManager.getTaskById(task.getId()));
        //System.out.println(taskManager.getTaskById(task2.getId()));
        //System.out.println(taskManager.getTaskById(task2.getId()));
        //System.out.println(taskManager.getTaskById(task.getId()));
        //System.out.println(taskManager.getEpicById(epic.getId()));
        //System.out.println(taskManager.getEpicById(epic1.getId()));
        //System.out.println(taskManager.getSubtaskById(subtask1.getId()));
        //taskManager.deleteEpicById(epic.getId());

        System.out.println("History === ==== ==== === == == == ");

        //System.out.println(taskManager.getHistory().toString());
 
    }
}
