package managers;

import javakanban.managers.TaskManager;
import javakanban.tasks.Epic;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


abstract class TaskManagerTest<T extends TaskManager> {
    T taskManager;

    @Test
    public void taskShouldBeAddedInListAfterCreate() {
        Epic epic = new Epic("epic1", "de");
        taskManager.createEpic(epic);
        List<Epic> result = taskManager.getListEpics();

        List<Epic> expected = new ArrayList<>();
        expected.add(epic);

        Assertions.assertArrayEquals(result.toArray(), expected.toArray());
    }
}
