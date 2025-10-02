import java.util.*;

class Task {
    private int id;
    private String title;
    private boolean completed;

    public Task(int id, String title) {
        this.id = id;
        this.title = title;
        this.completed = false;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public boolean isCompleted() { return completed; }

    public void setTitle(String title) { this.title = title; }
    public void markCompleted() { this.completed = true; }

    @Override
    public String toString() {
        return "Task{" + "id=" + id + ", title='" + title + "', completed=" + completed + '}';
    }
}

public class TaskManager {
    private static Map<Integer, Task> tasks = new HashMap<>();
    private static int counter = 0;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Task Manager ---");
            System.out.println("1. Add Task");
            System.out.println("2. View All Tasks");
            System.out.println("3. Update Task");
            System.out.println("4. Mark Task Completed");
            System.out.println("5. Delete Task");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addTask();
                case 2 -> viewTasks();
                case 3 -> updateTask();
                case 4 -> completeTask();
                case 5 -> deleteTask();
                case 6 -> { System.out.println("Goodbye!"); return; }
                default -> System.out.println("Invalid choice, try again!");
            }
        }
    }

    private static void addTask() {
        System.out.print("Enter task title: ");
        String title = sc.nextLine();
        Task task = new Task(++counter, title);
        tasks.put(task.getId(), task);
        System.out.println("Task added: " + task);
    }

    private static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }
        tasks.values().forEach(System.out::println);
    }

    private static void updateTask() {
        System.out.print("Enter task ID to update: ");
        int id = sc.nextInt(); sc.nextLine();
        Task task = tasks.get(id);
        if (task == null) {
            System.out.println("Task not found!");
            return;
        }
        System.out.print("Enter new title: ");
        String newTitle = sc.nextLine();
        task.setTitle(newTitle);
        System.out.println("Task updated: " + task);
    }

    private static void completeTask() {
        System.out.print("Enter task ID to mark completed: ");
        int id = sc.nextInt();
        Task task = tasks.get(id);
        if (task == null) {
            System.out.println("Task not found!");
            return;
        }
        task.markCompleted();
        System.out.println("Task marked completed: " + task);
    }

    private static void deleteTask() {
        System.out.print("Enter task ID to delete: ");
        int id = sc.nextInt();
        if (tasks.remove(id) != null) {
            System.out.println("Task deleted.");
        } else {
            System.out.println("Task not found!");
        }
    }
}
