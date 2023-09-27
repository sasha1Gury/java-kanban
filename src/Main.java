import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static TaskManager taskManager = new TaskManager();
    public static void main(String[] args) {

        //Main.createTask();
        //Main.createTask();

        Main.createEpic();
        //Main.createEpic();

        //Main.createSubtask();
        Main.createSubtask();
        //Main.createSubtask();

        //taskManager.getListEpics();
        //taskManager.getListTasks();

        //taskManager.retrievingById();

        //Task task = new Task("Updated task", "upd");
        //task.setId(1);
        //Epic epic = new Epic("Updated epic", "upd");
        //epic.setId(2);

        //taskManager.updateTask(task);
        //taskManager.deleteTaskById(task.getId());

        //taskManager.updateEpic(epic);
        //taskManager.deleteEpicById(epic.getId());
        //taskManager.getListSubtasksByEpicId(1);

        taskManager.setEpicStatus();

        taskManager.getListTasks();
        //taskManager.removeAll();
        taskManager.getListEpics();
        //taskManager.getListTasks();
    }

    public static void createTask() {
        System.out.println("Введите название и описание задачи");
        String taskName = scanner.nextLine();
        String description = scanner.nextLine();
        Task task = new Task(taskName, description);
        taskManager.doTasks(task);
    }

    public static void createEpic() {
        System.out.println("Введите название и описание эпика");
        String taskName = scanner.nextLine();
        String description = scanner.nextLine();
        Epic epic = new Epic(taskName, description);
        taskManager.doEpic(epic);
    }

    public static void createSubtask() {
        Scanner input = new Scanner(System.in);
        System.out.println("Введите название и описание подзадачи");
        String taskName = input.nextLine();
        String description = input.nextLine();
        Subtask subtask = new Subtask(taskName, description);
        System.out.println("Введите id эпика, в который нужно добавить подзадачу");
        int id = input.nextInt();
        taskManager.createSubtask(id, subtask);
    }
}
