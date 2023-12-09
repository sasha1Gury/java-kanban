package javakanban.comparators;

import javakanban.tasks.Task;
import java.util.Comparator;

public class TimeComparator implements Comparator<Task> {

    @Override
    public int compare(Task o1, Task o2) {
        Comparator<Task> startTimeComparator = Comparator
                .comparing(Task::getStartTime, Comparator.nullsLast(Comparator.naturalOrder()));

        return startTimeComparator.compare(o1, o2);
    }
}