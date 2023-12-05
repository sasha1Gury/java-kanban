package managers;

import javakanban.managers.InMemoryTaskManager;
import org.junit.jupiter.api.BeforeEach;

public class InMemoryTaskManagerTest extends TaskManagerTest<InMemoryTaskManager> {
    @BeforeEach
    void setUp() {
        taskManager = new InMemoryTaskManager();
    }


}
