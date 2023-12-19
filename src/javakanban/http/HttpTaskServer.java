package javakanban.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import javakanban.managers.Managers;
import javakanban.managers.TaskManager;
import javakanban.tasks.Epic;
import javakanban.tasks.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class HttpTaskServer {
    public static final int PORT = 8080;
    private final HttpServer server;
    private final Gson gson;
    private final TaskManager taskManager;
    public HttpTaskServer() throws IOException {
        this(Managers.getDefaultFileManager());
    }

    public HttpTaskServer(TaskManager taskManager) throws IOException{
        this.taskManager = taskManager;
        gson = new GsonBuilder().create();
        server = HttpServer.create(new InetSocketAddress("localhost", PORT), 0);
        server.createContext("/tasks", this::handle);
    }

    public static void main(String[] args) throws IOException {
        final HttpTaskServer server = new HttpTaskServer();
        server.start();
    }

    private void start() {
        server.start();
    }

    public void handle(HttpExchange exc) throws IOException {
        try{
            System.out.println("\n/tasks: " + exc.getRequestURI());
            final String path = exc.getRequestURI().getPath().substring(7);
            switch (path){
                case "": {
                    if (!exc.getRequestMethod().equals("GET")) {
                        System.out.println("Waiting GET, but received " + exc.getRequestMethod());
                        exc.sendResponseHeaders(485, 0);
                    }
                    final String response = gson.toJson(taskManager.getPrioritizedTasks());
                    sendResponseText(exc, response);
                    break;
                }
                case "history": {
                    if (!exc.getRequestMethod().equals("GET")) {
                        System.out.println("/history must be GET");
                        exc.sendResponseHeaders(485, 0);
                    }
                    final String response = gson.toJson(taskManager.getHistory());
                    sendResponseText(exc, response);
                    break;
                }
                case "task":
                    handleTask(exc);
                    break;
                case "epic":
                    handleEpic(exc);
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            exc.close();
        }
    }

    private void handleEpic(HttpExchange exc) throws IOException {
        final String query = exc.getRequestURI().getQuery();
        System.out.println(query);
        switch (exc.getRequestMethod()) {
            case "GET": {
                if (query == null) {
                    final List<Epic> epics = taskManager.getListEpics();
                    final String response = gson.toJson(epics);
                    sendResponseText(exc, response);
                    return;
                }
                String idStr = query.substring(3);
                final int id = Integer.parseInt(idStr);
                final Epic epic = taskManager.getEpicById(id);
                final String response = gson.toJson(epic);
                sendResponseText(exc, response);
                break;
            }
            case "DELETE": {
                if (query == null) {
                    taskManager.removeEpics();
                    System.out.println("All epics delete");
                    exc.sendResponseHeaders(200, 0);
                    return;
                }
                String idStr = query.substring(3);
                final int id = Integer.parseInt(idStr);
                taskManager.deleteEpicById(id);
                System.out.println(id + "epic delete");
                exc.sendResponseHeaders(200, 0);
                break;
            }
            case "POST": {
                String json = readText(exc);
                if(json.isEmpty()) {
                    System.out.println("Body is empty");
                    exc.sendResponseHeaders(400, 0);
                    return;
                }
                final Epic epic = gson.fromJson(json, Epic.class);
                final Integer id = epic.getId();
                if(id != null) {
                    taskManager.updateEpic(epic);
                    System.out.println("Epic " + id + " updated");
                    exc.sendResponseHeaders(200, 0);
                } else {
                    taskManager.createEpic(epic);
                    System.out.println("Epic " + id + "created");
                    final String response = gson.toJson(epic);
                    sendResponseText(exc, response);
                }
                break;
            }
            default: {
                System.out.println("/epic get " + exc.getRequestMethod());
                exc.sendResponseHeaders(405, 0);
                break;
            }
        }
    }

    private void handleTask(HttpExchange exc) throws IOException {
        final String query = exc.getRequestURI().getQuery();
        System.out.println(query);
        switch (exc.getRequestMethod()) {
            case "GET": {
                if (query == null) {
                    final List<Task> tasks = taskManager.getListTasks();
                    final String response = tasks.toString();
                    sendResponseText(exc, response);
                    return;
                }
                String idStr = query.substring(3);
                final int id = Integer.parseInt(idStr);
                final Task task = taskManager.getTaskById(id);
                final String response = task.toString();
                sendResponseText(exc, response);
                break;
            }
            case "DELETE": {
                if (query == null) {
                    taskManager.removeTasks();
                    System.out.println("All tasks delete");
                    exc.sendResponseHeaders(200, 0);
                    return;
                }
                String idStr = query.substring(3);
                final int id = Integer.parseInt(idStr);
                taskManager.deleteTaskById(id);
                System.out.println(id + "task delete");
                exc.sendResponseHeaders(200, 0);
                break;
            }
            case "POST": {
                String json = readText(exc);
                if(json.isEmpty()) {
                    System.out.println("Body is empty");
                    exc.sendResponseHeaders(400, 0);
                    return;
                }
                final Task task = gson.fromJson(json, Task.class);
                final Integer id = task.getId();
                if(id != null) {
                    taskManager.updateTask(task);
                    System.out.println("Task " + id + " updated");
                    exc.sendResponseHeaders(200, 0);
                } else {
                    taskManager.createTasks(task);
                    System.out.println("Task " + id + "created");
                    final String response = gson.toJson(task);
                    sendResponseText(exc, response);
                }
                break;
            }
            default: {
                System.out.println("/task get " + exc.getRequestMethod());
                exc.sendResponseHeaders(405, 0);
                break;
            }
        }
    }

    private String readText(HttpExchange exc)  {
        InputStreamReader streamReader = new InputStreamReader(exc.getRequestBody(), Charset.defaultCharset());
        BufferedReader bufferedReader = new BufferedReader(streamReader);
        String json = bufferedReader.lines().collect(Collectors.joining("\n"));
        return json;
    }

    private void sendResponseText(HttpExchange exchange, String response) throws IOException {
        byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(200, 0);
        OutputStream os = exchange.getResponseBody();
        os.write(bytes);
        exchange.close();
    }
}
