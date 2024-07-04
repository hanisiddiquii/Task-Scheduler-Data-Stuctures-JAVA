import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TaskSchedulerMain {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("*************************************************************************WELCOME*************************************************************************");
                System.out.println("             ╚══██╔══╝██╔══██╗██╔════╝██║ ██╔╝            ██╔════╝██╔════╝██║  ██║██╔════╝██╔══██╗██║   ██║██║     ██╔════╝██╔══██╗    ");
                        System.out.println("                ██║   ███████║███████╗█████╔╝             ███████╗██║     ███████║█████╗  ██║  ██║██║   ██║██║     █████╗  ██████╔╝  ");
                                System.out.println("                ██║   ██╔══██║╚════██║██╔═██╗             ╚════██║██║     ██╔══██║██╔══╝  ██║  ██║██║   ██║██║     ██╔══╝  ██╔══██╗  ");
                                        System.out.println("                ██║   ██║  ██║███████║██║  ██╗            ███████║╚██████╗██║  ██║███████╗██████╔╝╚██████╔╝███████╗███████╗██║  ██║   ");
                                                System.out.println("                ╚═╝   ╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝            ╚══════╝ ╚═════╝╚═╝  ╚═╝╚══════╝╚═════╝  ╚═════╝ ╚══════╝╚══════╝╚═╝  ╚═╝");


        while (true) {
            System.out.println("                                                                          **MENU**");
            System.out.println("                                                                        1. Add Task");
            System.out.println("                                                                        2. View Tasks");
            System.out.println("                                                                        3. Mark Task as Completed");
            System.out.println("                                                                        4. View Categories");
            System.out.println("                                                                        5. Prioritize Tasks");
            System.out.println("                                                                        6. Edit Task");
            System.out.println("                                                                        7. Delete Task");
            System.out.println("                                                                        8. Exit");


            int choice = 0;
            try {
                System.out.print("                                                                    Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("                                                       Invalid input. Please enter a valid integer.");
                scanner.nextLine();
            }

            switch (choice) {
                case 1:
                    addTask(taskManager, scanner);
                    break;
                case 2:
                    viewTasks(taskManager);
                    break;
                case 3:
                    System.out.println("                                                               Saved Tasks");
                    viewTasks(taskManager);
                    markTaskAsCompleted(taskManager, scanner);
                    break;
                case 4:
                    viewCategories(taskManager);
                    break;
                case 5:
                    prioritizeTasks(taskManager);
                    break;
                case 6:
                    System.out.println("                                                               Saved Tasks");
                    viewTasks(taskManager);
                    editTask(taskManager, scanner);
                    break;
                case 7:
                    System.out.println("                                                               Saved Tasks");
                    viewTasks(taskManager);
                    deleteTask(taskManager, scanner);
                    break;

                case 8:
                    System.out.println("                                                               Exiting Task Scheduler. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("                                                               Enter a number between 1 and 8.");
            }
        }
    }


    private static void addTask(TaskManager taskManager, Scanner scanner) {
        System.out.print("Enter Task ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter Task Description: ");
        String description = scanner.nextLine();

        System.out.print("Enter Task Priority: ");
        int priority = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Category Name: ");
        String categoryName = scanner.nextLine();

        Task task = new Task(id, description, priority, categoryName);
        taskManager.addTask(task);
        System.out.println("Task added successfully :)");
    }


    private static void viewTasks(TaskManager taskManager) {
        List<Task> tasks = taskManager.viewTasks();
        if (tasks.isEmpty()) {
            System.out.println("No tasks available :(");
        } else {
            System.out.println("Tasks:");
            for (Task task : tasks) {
                System.out.println(task);
            }
        }
    }
    private static void deleteTask(TaskManager taskManager, Scanner scanner) {
        System.out.print("Enter the ID of the task you want to delete: ");
        String taskIdToDelete = scanner.nextLine().trim();
        taskManager.deleteTask(taskIdToDelete);

        System.out.println("Task deleted successfully!!!");
    }



    private static void markTaskAsCompleted(TaskManager taskManager, Scanner scanner) {
        System.out.print("Enter Task ID to mark as completed: ");
        String taskId = scanner.nextLine();
        taskManager.completeTask(taskId);
        System.out.println("Task marked as completed !!!");
    }

    private static void viewCategories(TaskManager taskManager) {
        List<Category> categories = taskManager.viewCategories();
        if (categories.isEmpty()) {
            System.out.println("No categories available.");
        } else {
            System.out.println("Categories:");
            for (Category category : categories) {
                System.out.println(category.getName());
            }
        }
    }

    private static void prioritizeTasks(TaskManager taskManager) {
        List<Task> prioritizedTasks = taskManager.prioritizeTasks();
        if (prioritizedTasks.isEmpty()) {
            System.out.println("No tasks available for prioritization.");
        } else {
            System.out.println("Prioritized Tasks:");
            for (Task task : prioritizedTasks) {
                System.out.println(task);
            }
        }
    }

    private static void editTask(TaskManager taskManager, Scanner scanner) {
        System.out.print("Enter the ID of the task you want to edit: ");
        String taskIdToEdit = scanner.nextLine().trim();
        System.out.print("Enter Task Description: ");
        String description = scanner.nextLine();

        System.out.print("Enter Task Priority: ");
        int priority = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Category Name: ");
        String categoryName = scanner.nextLine();

        Task newTask = new Task(taskIdToEdit, description, priority, categoryName);
        taskManager.editTask(taskIdToEdit, newTask);

        System.out.println("Task edited successfully!");
    }
}
