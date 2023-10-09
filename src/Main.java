import javakanban.managers.Managers;
import javakanban.managers.TaskManager;
import javakanban.tasks.Epic;
import javakanban.tasks.Subtask;
import javakanban.tasks.Task;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static TaskManager taskManager = Managers.getDefault();
    public static void main(String[] args) {

    }

    public static void createTask() {
        System.out.println("Введите название и описание задачи");
        String taskName = scanner.nextLine();
        String description = scanner.nextLine();
        Task task = new Task(taskName, description);
        taskManager.createTasks(task);
    }

    public static void createEpic() {
        System.out.println("Введите название и описание эпика");
        String taskName = scanner.nextLine();
        String description = scanner.nextLine();
        Epic epic = new Epic(taskName, description);
        taskManager.createEpic(epic);
    }

    public static void createSubtask() {
        Scanner input = new Scanner(System.in);
        System.out.println("Введите название и описание подзадачи");
        String taskName = input.nextLine();
        String description = input.nextLine();

        System.out.println("Введите id эпика, в который нужно добавить подзадачу");
        int Epicid = input.nextInt();
        Subtask subtask = new Subtask(taskName, description, Epicid);
        taskManager.createSubtask(subtask);
    }
}
