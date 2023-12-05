package managers;

import javakanban.managers.TaskManager;
import javakanban.tasks.Epic;
import javakanban.tasks.Subtask;
import javakanban.tasks.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

abstract class TaskManagerTest<T extends TaskManager> {
    T taskManager;
    Task task;
    Epic epic;
    Subtask subtask;



    @Test
    public void taskShouldBeAddedInListAfterCreate() {
        taskManager.createTasks(task);
        List<Task> result = taskManager.getListTasks();

        List<Task> expected = new ArrayList<>();
        expected.add(task);

        Assertions.assertArrayEquals(result.toArray(), expected.toArray());
        Assertions.assertEquals(task, taskManager.getTaskById(task.getId()), "Задачи не совпадают.");
        Assertions.assertNotNull(taskManager.getTaskById(task.getId()), "Задача не найдена");

        Assertions.assertNotNull(result, "Задачи на возвращаются.");
        Assertions.assertEquals(1, taskManager.getListTasks().size(), "Неверное количество задач.");
        System.out.println("[][][");
    }

    @Test
    public void subtaskShouldBeAddedInListAfterCreate() {
        taskManager.createEpic(epic);
        taskManager.createSubtask(subtask);

        List<Subtask> result = taskManager.getListSubtasksByEpicId(epic.getId());

        List<Subtask> expected = new ArrayList<>();
        expected.add(subtask);

        Assertions.assertArrayEquals(result.toArray(), expected.toArray());
        Assertions.assertEquals(subtask, taskManager.getSubtaskById(subtask.getId()));
        Assertions.assertNotNull(taskManager.getSubtaskById(subtask.getId()), "Задача не найдена");

        Assertions.assertNotNull(result, "Задачи на возвращаются.");
        Assertions.assertEquals(1, taskManager.getListSubtasks().size(), "Неверное количество задач.");
    }

    @Test
    public void epicShouldBeAddedInListAfterCreate() {
        taskManager.createEpic(epic);
        List<Epic> result = taskManager.getListEpics();

        List<Epic> expected = new ArrayList<>();
        expected.add(epic);

        Assertions.assertArrayEquals(result.toArray(), expected.toArray());
        Assertions.assertEquals(epic, taskManager.getEpicById(epic.getId()));
        Assertions.assertNotNull(taskManager.getEpicById(epic.getId()), "Задача не найдена");

        Assertions.assertNotNull(result, "Задачи на возвращаются.");
        Assertions.assertEquals(1, taskManager.getListEpics().size(), "Неверное количество задач.");
    }

    @Test
    public void shouldReturnTaskById() {
        taskManager.createTasks(task);
        Task result = taskManager.getTaskById(task.getId());

        Assertions.assertEquals(task, result);
    }

    @Test
    public void shouldReturnEpicById() {
        taskManager.createTasks(epic);
        Task result = taskManager.getTaskById(epic.getId());

        Assertions.assertEquals(epic, result);
    }

    @Test
    public void shouldReturnSubtaskById() {
        taskManager.createTasks(subtask);
        Task result = taskManager.getTaskById(subtask.getId());

        Assertions.assertEquals(subtask, result);
    }


}
