public class Task {
    private int taskId;
    private String taskName;
    private String status;

    public Task(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Task ID: " + taskId + ", Task Name: " + taskName + ", Status: " + status;
    }
}

public class Node {
    Task task;
    Node next;

    public Node(Task task) {
        this.task = task;
        this.next = null;
    }
}

public class TaskLinkedList {
    private Node head;

    public TaskLinkedList() {
        this.head = null;
    }

    public void addTask(Task task) {
        Node newNode = new Node(task);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public Task findTaskById(int taskId) {
        Node current = head;
        while (current != null) {
            if (current.task.getTaskId() == taskId) {
                return current.task;
            }
            current = current.next;
        }
        return null;
    }

    public void printAllTasks() {
        Node current = head;
        while (current != null) {
            System.out.println(current.task);
            current = current.next;
        }
    }

    public boolean removeTaskById(int taskId) {
        if (head == null) return false;
        if (head.task.getTaskId() == taskId) {
            head = head.next;
            return true;
        }
        Node current = head;
        while (current.next != null && current.next.task.getTaskId() != taskId) {
            current = current.next;
        }
        if (current.next == null) return false;
        current.next = current.next.next;
        return true;
    }
}

public class TaskManagementSystem {
    public static void main(String[] args) {
        TaskLinkedList taskList = new TaskLinkedList();
        taskList.addTask(new Task(1, "Design UI", "In Progress"));
        taskList.addTask(new Task(2, "Develop Backend", "Not Started"));
        taskList.addTask(new Task(3, "Write Tests", "Not Started"));
        taskList.addTask(new Task(4, "Deploy Application", "Completed"));

        System.out.println("All tasks:");
        taskList.printAllTasks();

        System.out.println("\nSearching for task with ID 3:");
        Task task = taskList.findTaskById(3);
        if (task != null) {
            System.out.println("Found: " + task);
        } else {
            System.out.println("Task not found.");
        }

        System.out.println("\nDeleting task with ID 2:");
        boolean isDeleted = taskList.removeTaskById(2);
        System.out.println("Deleted: " + isDeleted);

        System.out.println("\nAll tasks after deletion:");
        taskList.printAllTasks();
    }
}
