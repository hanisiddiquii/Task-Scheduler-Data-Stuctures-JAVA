



import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Category implements Comparable<Category> {
    private String name;
    private BinarySearchTree<Task> tasks;
    
    public Category(String name) {
        this.name = name;
        this.tasks = new BinarySearchTree<>();
    }

    public String getName() {
        return name;
    }

    public void addTask(Task task) {
        tasks.insert(task);
    }

    public void removeTask(Task task) {
        tasks.delete(task);
    }

    public List<Task> viewTasks() {
        return tasks.inOrderTraversal();
    }
    @Override
    public int compareTo(Category otherCategory) {
        return this.name.compareTo(otherCategory.name);
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                '}';
    }
}

