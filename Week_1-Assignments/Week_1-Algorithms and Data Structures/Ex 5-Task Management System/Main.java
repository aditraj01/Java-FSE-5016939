public class Main {
    public static void main(String[] args) {
        TaskManagementSystem tms = new TaskManagementSystem();

        Task task1 = new Task(1, "Design the system architecture", "In Progress");
        Task task2 = new Task(2, "Develop the backend", "Not Started");
        Task task3 = new Task(3, "Create frontend UI", "In Progress");

        tms.addTask(task1);
        tms.addTask(task2);
        tms.addTask(task3);

        System.out.println("All Tasks:");
        tms.traverseTasks();

        System.out.println("\nSearching for Task with ID 2:");
        Task foundTask = tms.searchTask(2);
        if (foundTask != null) {
            System.out.println(foundTask);
        } else {
            System.out.println("Task not found.");
        }

        System.out.println("\nDeleting Task with ID 2:");
        tms.deleteTask(2);

        System.out.println("All Tasks after deletion:");
        tms.traverseTasks();
    }
}
