package Model;
public class Node<T> {
    private T value;
    private int priority;

    // Constructor para inicializar un nodo con valor y prioridad
    public Node(T value, int priority) {
        this.value = value;
        this.priority = priority;
    }

    // Métodos de acceso y modificación para value y priority
    public T getValue() {
        return value;
    }

    public int getPriority() {
        return priority;
    }

    // Método toString para representar un nodo como una cadena de texto
    @Override
    public String toString() {
        return value + ", Prioridad: " + priority;
    }
}
