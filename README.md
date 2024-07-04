# Task Scheduler

Task Scheduler is a Java application designed to help users manage their tasks efficiently. With features for adding, viewing, editing, prioritizing, and categorizing tasks, this application makes it easy to keep track of your to-do list and stay organized.

## Features

- **Add Task:** Add a new task with an ID, description, priority, and category.
- **View Tasks:** View a list of all tasks.
- **Mark Task as Completed:** Mark a task as completed.
- **View Categories:** View all available categories.
- **Prioritize Tasks:** View tasks sorted by priority.
- **Edit Task:** Edit an existing task.
- **Delete Task:** Delete a task from the list.

## Usage

To use the Task Scheduler, run the `TaskSchedulerMain` class. You will be presented with a menu with the following options:

1. Add Task
2. View Tasks
3. Mark Task as Completed
4. View Categories
5. Prioritize Tasks
6. Edit Task
7. Delete Task
8. Exit

Select an option by entering the corresponding number.

## How to Run

1. Ensure you have Java installed on your machine.
2. Clone this repository.
3. Navigate to the directory containing the source code.
4. Compile the code using `javac TaskSchedulerMain.java`.
5. Run the application using `java TaskSchedulerMain`.

## Example

```sh
javac TaskSchedulerMain.java
java TaskSchedulerMain
```

## Classes

### TaskSchedulerMain

The main class that provides the menu and handles user input for managing tasks.

### TaskManager

Handles the core functionality of task management, including adding, viewing, editing, prioritizing, and deleting tasks.

### Task

Represents a task with an ID, description, priority, and category.

### Category

Represents a category for tasks.

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request.

## License

This project is licensed under the MIT License. See the `LICENSE` file for more details.
