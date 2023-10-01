import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static TaskManager taskManager = new TaskManager();
    public static void main(String[] args) {
        //createTask();
        //createTask();
        createEpic();
        createEpic();
        createSubtask();
        createSubtask();
        createSubtask();
        createSubtask();
        createSubtask();
        //Subtask updated = new Subtask("UPD1", "da", 1);
        //updated.setId(1);
        //taskManager.updateSubtask(updated);
        //updated.setStatusInProgress();
        //taskManager.createSubtask(updated);

        //taskManager.d
        //taskManager.deleteTaskById(1);
        //ArrayList<Subtask> epic1listSub = taskManager.getListSubtasksByEpicId(1);
        //System.out.println(epic1listSub);
        taskManager.deleteSubtaskById(5);

        ArrayList<Task> listTasks = taskManager.getListTasks();
        System.out.println(listTasks.toString());
        ArrayList<Epic> listEpics = taskManager.getListEpics();
        System.out.println(listEpics.toString());
        ArrayList<Subtask> listSubtasks = taskManager.getListSubtasks();
        System.out.println(listSubtasks.toString());
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
