package javakanban.managers;

import javakanban.tasks.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CSVTaskFormat {
    public static Task taskFromString(String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy.MM.dd HH:mm");
        final String[] values = value.split(",");
        final int id = Integer.parseInt(values[0]);
        final Type type = Type.valueOf(values[1]);
        final String name = values[2];
        final Status status = Status.valueOf(values[3]);
        final String description = values[4];
        final int duration = Integer.parseInt(values[5]);
        final LocalDateTime localDateTime = LocalDateTime.parse(values[6], formatter);

        if (type == Type.TASK) {
            return new Task(id, name, description, status, duration, localDateTime);
        }
        if (type == Type.SUBTASK) {
            final int epicId= Integer.parseInt(values[7]);
            return new Subtask(id, name, description, status, epicId, duration, localDateTime);
        }
        return new Epic(id, name, description, status, duration, localDateTime);
    }

    public static List<Integer> historyFromString(String value) {
        List<Integer> idsHistory = new ArrayList<>();
        final String[] values = value.split(",");
        for (String str: values) {
            idsHistory.add(Integer.parseInt(str));
        }
        return idsHistory;
    }
}
