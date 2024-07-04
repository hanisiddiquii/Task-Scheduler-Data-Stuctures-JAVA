
import java.io.*;
import java.util.*;


class CustomHashSet<T> implements Iterable<T> {


    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    private Entry<T>[] table;
    private int size;

    public CustomHashSet() {
        table = new Entry[DEFAULT_CAPACITY];
        size = 0;
    }

    public void add(T element) {
        int index = getIndex(element);
        Entry<T> entry = table[index];

        while (entry != null) {
            if (entry.key.equals(element)) {
                return;
            }
            entry = entry.next;
        }

        Entry<T> newEntry = new Entry<>(element);
        newEntry.next = table[index];
        table[index] = newEntry;
        size++;


        if ((double) size / table.length > LOAD_FACTOR) {
            resizeTable();
        }
    }

    public boolean contains(T element) {
        int index = getIndex(element);
        Entry<T> entry = table[index];

        while (entry != null) {
            if (entry.key.equals(element)) {
                return true;
            }
            entry = entry.next;
        }

        return false;
    }

    public void remove(T element) {
        int index = getIndex(element);
        Entry<T> prev = null;
        Entry<T> current = table[index];

        while (current != null) {
            if (current.key.equals(element)) {
                if (prev == null) {

                    table[index] = current.next;
                } else {

                    prev.next = current.next;
                }
                size--;
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new CustomHashSetIterator();
    }

    private int getIndex(T key) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode) % table.length;
    }

    private void resizeTable() {
        int newCapacity = table.length * 2;
        Entry<T>[] newTable = new Entry[newCapacity];

        for (Entry<T> entry : table) {
            while (entry != null) {
                Entry<T> next = entry.next;
                int newIndex = Math.abs(entry.key.hashCode()) % newCapacity;
                entry.next = newTable[newIndex];
                newTable[newIndex] = entry;
                entry = next;
            }
        }

        table = newTable;
    }

    private class CustomHashSetIterator implements Iterator<T> {
        private int currentIndex = 0;
        private Entry<T> currentEntry = null;

        @Override
        public boolean hasNext() {
            if (currentEntry != null && currentEntry.next != null) {
                return true;
            }

            for (int i = currentIndex; i < table.length; i++) {
                if (table[i] != null) {
                    return true;
                }
            }

            return false;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            if (currentEntry != null && currentEntry.next != null) {
                currentEntry = currentEntry.next;
            } else {
                do {
                    currentEntry = table[currentIndex++];
                } while (currentEntry == null);
            }

            return currentEntry.key;
        }
    }

    private static class Entry<T> {
        T key;
        Entry<T> next;

        public Entry(T key) {
            this.key = key;
            this.next = null;
        }
    }
}

class Node<T> {
    T data;
    Node<T> prev;
    Node<T> next;

    public Node(T data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

class DoublyLinkedList<T> implements Iterable<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }


    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    public void remove(T element) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(element)) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {

                    head = current.next;
                }

                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {

                    tail = current.prev;
                }

                size--;
                return;
            }
            current = current.next;
        }
    }

    public List<T> toList() {
        List<T> list = new ArrayList<>();
        Node<T> current = head;
        while (current != null) {
            list.add(current.data);
            current = current.next;
        }
        return list;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.data;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }
}

class BiNode<T> {
    T data;
    BiNode<T> left;
    BiNode<T> right;

    public BiNode(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

class BinarySearchTree<T extends Comparable<T>> {
    private BiNode<T> root;

    public BinarySearchTree() {
        this.root = null;
    }

    public void insert(T data) {
        root = insertRec(root, data);
    }

    private BiNode<T> insertRec(BiNode<T> root, T data) {
        if (root == null) {
            return new BiNode<>(data);
        }

        if (data.compareTo(root.data) < 0) {
            root.left = insertRec(root.left, data);
        } else if (data.compareTo(root.data) > 0) {
            root.right = insertRec(root.right, data);
        }

        return root;
    }

    public T search(T data) {
        return searchRec(root, data);
    }

    private T searchRec(BiNode<T> root, T data) {
        if (root == null) {
            return null;
        }

        if (root.data.equals(data)) {
            return root.data;
        }

        if (data.compareTo(root.data) < 0) {
            return searchRec(root.left, data);
        }

        return searchRec(root.right, data);
    }




    public List<T> inOrderTraversal() {
        List<T> result = new ArrayList<>();
        inOrderTraversalRec(root, result);
        return result;
    }

    private void inOrderTraversalRec(BiNode<T> root, List<T> result) {
        if (root != null) {
            inOrderTraversalRec(root.left, result);
            result.add(root.data);
            inOrderTraversalRec(root.right, result);
        }
    }


    public void delete(T data) {
        root = deleteRec(root, data);
    }

    private BiNode<T> deleteRec(BiNode<T> root, T data) {
        if (root == null) {
            return null;
        }

        if (data.compareTo(root.data) < 0) {
            root.left = deleteRec(root.left, data);
        } else if (data.compareTo(root.data) > 0) {
            root.right = deleteRec(root.right, data);
        } else {

            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            root.data = minValue(root.right);
            root.right = deleteRec(root.right, root.data);
        }

        return root;
    }

    private T minValue(BiNode<T> root) {
        T minValue = root.data;
        while (root.left != null) {
            minValue = root.left.data;
            root = root.left;
        }
        return minValue;
    }
}

class Entry<K, V> {
    K key;
    V value;
    Entry<K, V> next;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}

class HashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static  double LOAD_FACTOR = 0;

    private Entry<K, V>[] table;
    private int size;

    public HashMap() {
        table = new Entry[DEFAULT_CAPACITY];
        size = 0;
    }

    public void put(K key, V value) {
        int index = getIndex(key);
        Entry<K, V> entry = table[index];

        while (entry != null) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
            entry = entry.next;
        }


        Entry<K, V> newEntry = new Entry<>(key, value);
        newEntry.next = table[index];
        table[index] = newEntry;
        size++;

        LOAD_FACTOR = size/DEFAULT_CAPACITY;

        if ((double) size / table.length > LOAD_FACTOR) {
            resizeTable();
        }
    }

    public V get(K key) {
        int index = getIndex(key);
        Entry<K, V> entry = table[index];

        while (entry != null) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
            entry = entry.next;
        }

        return null;
    }

    public void remove(K key) {
        int index = getIndex(key);
        Entry<K, V> prev = null;
        Entry<K, V> current = table[index];

        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {

                    table[index] = current.next;
                } else {

                    prev.next = current.next;
                }
                size--;
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    public boolean containsKey(K key) {
        int index = getIndex(key);
        Entry<K, V> entry = table[index];

        while (entry != null) {
            if (entry.key.equals(key)) {
                return true;
            }
            entry = entry.next;
        }

        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        table = new Entry[DEFAULT_CAPACITY];
        size = 0;
    }

    private int getIndex(K key) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode) % table.length;
    }

    private void resizeTable() {
        int newCapacity = table.length * 2;
        Entry<K, V>[] newTable = new Entry[newCapacity];

        for (Entry<K, V> entry : table) {
            while (entry != null) {
                Entry<K, V> next = entry.next;
                int newIndex = Math.abs(entry.key.hashCode()) % newCapacity;
                entry.next = newTable[newIndex];
                newTable[newIndex] = entry;
                entry = next;
            }
        }

        table = newTable;
    }
}
class PriorityQueue<T extends Comparable<T>> {
    private List<T> elements;

    public PriorityQueue() {
        this.elements = new ArrayList<>();
    }

    public void add(T element) {
        elements.add(element);
        heapifyUp(elements.size() - 1);
    }

    public T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException("PriorityQueue is empty");
        }
        swap(0, elements.size() - 1);
        T removedElement = elements.remove(elements.size() - 1);
        heapifyDown(0);
        return removedElement;
    }

    public void remove(T element) {
        int index = elements.indexOf(element);
        if (index != -1) {
            swap(index, elements.size() - 1);

            elements.remove(elements.size() - 1);
            heapifyUp(index);
            heapifyDown(index);
        }
    }

    public T poll() {
        if (isEmpty()) {
            return null;
        }

        return remove();
    }



    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("PriorityQueue is empty");
        }

        return elements.get(0);
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public void clear() {
        elements.clear();
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (elements.get(index).compareTo(elements.get(parentIndex)) > 0) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    private void heapifyDown(int index) {
        int size = elements.size();

        while (index < size) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int largest = index;

            if (leftChild < size && elements.get(leftChild).compareTo(elements.get(largest)) > 0) {
                largest = leftChild;
            }

            if (rightChild < size && elements.get(rightChild).compareTo(elements.get(largest)) > 0) {
                largest = rightChild;
            }

            if (largest != index) {
                swap(index, largest);
                index = largest;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        T temp = elements.get(i);
        elements.set(i, elements.get(j));
        elements.set(j, temp);
    }
}

public class TaskManager {

    private DoublyLinkedList<Task> tasks;
    private HashMap<String, Task> taskHash;
    private BinarySearchTree<Category> categories;
    private PriorityQueue<Task> priorityQueue;
    private HashMap<String, CustomHashSet<String>> dependencies;


    public TaskManager() {
        tasks = new DoublyLinkedList<>();
        taskHash = new HashMap<>();
        categories = new BinarySearchTree<>();
        priorityQueue = new PriorityQueue<>();
        dependencies = new HashMap<>();
    }
    public Task getTask(String taskId) {
        for (Task task : tasks) {
            if (task.getId().equals(taskId)) {
                return task;
            }
        }
        return null;
    }


    public void addDependency(String dependentTaskId, String dependencyTaskId) {
        CustomHashSet<String> dependentSet = dependencies.get(dependentTaskId);
        if (dependentSet == null) {
            dependentSet = new CustomHashSet<>();
            dependencies.put(dependentTaskId, dependentSet);
        }
        dependentSet.add(dependencyTaskId);
    }

    public void addTask(Task task) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter comma-separated Task IDs that this task depends on (if any): ");
        String dependencyInput = scanner.nextLine().trim();

        if (!dependencyInput.isEmpty()) {
            String[] dependencyIds = dependencyInput.split(",");
            for (String dependencyId : dependencyIds) {
                addDependency(task.getId(), dependencyId);
            }
        }
        tasks.add(task);
        taskHash.put(task.getId(), task);

        Category taskCategory = findOrCreateCategory(task.getCategoryName());
        taskCategory.addTask(task);

        priorityQueue.add(task);
    }

    public void deleteTask(String taskId) {
        Task task = taskHash.get(taskId);
        if (task != null) {
            tasks.remove(task);
            taskHash.remove(taskId);
            Category taskCategory = findCategory(task.getCategoryName());
            if (taskCategory != null) {
                taskCategory.removeTask(task);
            }
            priorityQueue.remove(task);
        }
    }
    public void completeTask(String taskId) {
        Task task = taskHash.get(taskId);
        if (task != null) {
            CustomHashSet<String> dependencySet = dependencies.get(taskId);
            if (dependencySet != null && !dependencySet.isEmpty()) {
                for (String dependencyId : dependencySet) {
                    Task dependencyTask = taskHash.get(dependencyId);
                    if (dependencyTask != null && !dependencyTask.isCompleted()) {
                        System.out.println("Cannot complete task. Dependency task '" + dependencyId + "' is not completed.");
                        return;
                    }
                }
            }
            task.markAsCompleted();
            System.out.println("Task marked as completed!");
        }
    }
    public List<Task> viewTasks() {
        return tasks.toList();
    }

    public void categorizeTask(String taskId, String categoryName) {
        Task task = taskHash.get(taskId);
        if (task != null) {
            Category currentCategory = findCategory(task.getCategoryName());
            if (currentCategory != null) {
                currentCategory.removeTask(task);
            }
            Category newCategory = findOrCreateCategory(categoryName);
            newCategory.addTask(task);
        }
    }
    public List<Category> viewCategories() {
        return categories.inOrderTraversal();
    }



    public List<Task> prioritizeTasks() {
        List<Task> prioritizedTasks = new ArrayList<>();
        while (!priorityQueue.isEmpty()) {
            Task task = (Task) priorityQueue.poll();
            prioritizedTasks.add(task);
        }
        return prioritizedTasks;
    }

    private Category findOrCreateCategory(String categoryName) {
        Category category = findCategory(categoryName);
        if (category == null) {
            category = new Category(categoryName);
            categories.insert(category);
        }
        return category;
    }
    private Category findCategory(String categoryName) {
        return categories.search(new Category(categoryName));
    }

    public void editTask(String taskId, Task newTask) {
        Task existingTask = taskHash.get(taskId);

        if (existingTask != null) {
            existingTask.setDescription(newTask.getDescription());
            existingTask.setPriority(newTask.getPriority());
            existingTask.setCategoryName(newTask.getCategoryName());
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter comma-separated Task IDs that this task depends on (if any): ");
            String newDependencyInput = scanner.nextLine().trim();
            dependencies.remove(taskId);
            if (!newDependencyInput.isEmpty()) {
                String[] newDependencyIds = newDependencyInput.split(",");
                CustomHashSet<String> newDependencies = new CustomHashSet<>();
                for (String newDependencyId : newDependencyIds) {
                    newDependencies.add(newDependencyId);
                }
                dependencies.put(taskId, newDependencies);
            }
            priorityQueue.remove(existingTask);
            priorityQueue.add(existingTask);
            System.out.println("Task edited successfully!");
        } else {
            System.out.println("Task not found!");
        }
    }


}


