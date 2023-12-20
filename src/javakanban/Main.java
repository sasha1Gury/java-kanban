package javakanban;

import com.google.gson.Gson;
import javakanban.http.HttpTaskServer;
import javakanban.server.KVServer;
import javakanban.tasks.Task;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Gson gson = new Gson();
        KVServer kvServer = new KVServer();
        kvServer.start();
        HttpTaskServer httpTaskServer = new HttpTaskServer();
        httpTaskServer.start();

        Task newTask = new Task("Task1", "des");
        System.out.println(newTask.getId());

        String json = gson.toJson(newTask);
        System.out.println(json);
    }
}
