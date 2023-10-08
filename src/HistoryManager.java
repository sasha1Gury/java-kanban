import java.util.ArrayList;
import java.util.List;

public class HistoryManager {
    private static final int MAX_COUNT = 10;
    private static final List<Task> history = new ArrayList<>();

    public static List<Task> getHistory() {
        return history;
    }

    public static void addTaskToHistory(Task task){
        history.add(task);
        if(history.size() > MAX_COUNT) {
            history.remove(0);
        }
    }
}
