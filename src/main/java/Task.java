import java.io.*;


public class Task implements Comparable<Task> {
    private String id;
    private String description;
    private int priority;
    private boolean completed;
    private String categoryName;
    private int dependencyLevel;


    public Task(String id, String description, int priority, String categoryName) {
        this.id = id;
        this.description = description;
        this.priority = priority;
        this.completed = false;
        this.categoryName = categoryName;
    }

    // Getters and Setters for attributes
    public void setDependencyLevel(int dependencyLevel) {
        this.dependencyLevel = dependencyLevel;
    }
    public int getDependencyLevel() {
        return dependencyLevel;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setCategory(String categoryName) {
        this.categoryName = categoryName;
    }


    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isCompleted() {
        return completed;
    }




    public void markAsCompleted() {
        this.completed = true;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


    @Override
    public int compareTo(Task otherTask) {
        return Integer.compare(this.priority, otherTask.priority);
    }


    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                ", completed=" + completed +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}