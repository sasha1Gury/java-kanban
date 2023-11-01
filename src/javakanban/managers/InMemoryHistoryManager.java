package javakanban.managers;

import javakanban.tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManager implements HistoryManager {

    private static class Node {
        Node prev;
        Node next;
        Task task;

        public Node(Node prev, Node next, Task task) {
            this.prev = prev;
            this.next = next;
            this.task = task;
        }
    }

    private Node first;
    private Node last;
    private final Map<Integer, Node> nodes = new HashMap<>();

    @Override
    public List<Task> getHistory() {
        List<Task> result = new ArrayList<>();
        Node current = first;
        while (current != null) {
            result.add(current.task);
            current = current.next;
        }
        return result;
    }

    @Override
    public void addTaskToHistory(Task task){
        remove(task.getId());
        Node node = new Node(last, null, task);
        if (first == null) {
            first = node;
        } else last.next = node;
        last = node;
        nodes.put(task.getId(), node);
    }

    @Override
    public void remove(int id) {
        Node remove = nodes.remove(id);
        if (remove == null) return;
        if(remove.prev == null && remove.next == null) {
            first = null;
            last = null;
        } else if (remove.prev == null) {
            first = remove.next;
            remove.next.prev = null;
        } else if (remove.next == null) {
            last = remove.prev;
            remove.prev.next = null;
        } else {
            remove.prev.next = remove.next;
            remove.next.prev = remove.prev;
        }
    }
}
