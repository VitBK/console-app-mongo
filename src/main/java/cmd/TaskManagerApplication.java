package cmd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Scanner;

@SpringBootApplication
public class TaskManagerApplication implements CommandLineRunner {

    @Autowired
    private TaskService taskService;

    public static void main(String[] args) {
        SpringApplication.run(TaskManagerApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nTask Manager");
            System.out.println("1. Display all tasks");
            System.out.println("2. Display overdue tasks");
            System.out.println("3. Display tasks by category");
            System.out.println("4. Display subtasks by category");
            System.out.println("5. Add/Update a task");
            System.out.println("6. Delete a task");
            System.out.println("7. Search tasks by description");
            System.out.println("8. Search subtasks by name");
            System.out.println("9. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> taskService.getAllTasks().forEach(System.out::println);
                case 2 -> taskService.getOverdueTasks().forEach(System.out::println);
                case 3 -> {
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    taskService.getTasksByCategory(category).forEach(System.out::println);
                }
                case 4 -> {
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    taskService.getSubTasksByCategory(category).forEach(System.out::println);
                }
                case 5 -> {
                    System.out.print("Enter task name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter deadline (YYYY-MM-DD): ");
                    LocalDate deadline = LocalDate.parse(scanner.nextLine());
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();

                    Task task = new Task();
                    task.setName(name);
                    task.setDescription(description);
                    task.setDateOfCreation(LocalDate.now());
                    task.setDeadline(deadline);
                    task.setCategory(category);
                    taskService.saveOrUpdateTask(task);
                }
                case 6 -> {
                    System.out.print("Enter task ID: ");
                    String id = scanner.nextLine();
                    taskService.deleteTask(id);
                }
                case 7 -> {
                    System.out.print("Enter keyword: ");
                    String keyword = scanner.nextLine();
                    taskService.searchByDescription(keyword).forEach(System.out::println);
                }
                case 8 -> {
                    System.out.print("Enter keyword: ");
                    String keyword = scanner.nextLine();
                    taskService.searchBySubTaskName(keyword).forEach(System.out::println);
                }
                case 9 -> System.exit(0);
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}