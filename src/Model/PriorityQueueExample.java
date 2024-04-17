package Model;
import java.util.PriorityQueue;
import java.util.Comparator;

public class PriorityQueueExample<T> {
    private PriorityQueue<Node<T>> queue;
    private final Object lock = new Object(); // Objeto de bloqueo para sincronización
    private boolean producerDone = false; // Variable que indica si el productor ha terminado
    private static final String COLOR_RED = "\u001B[31m";
    private static final String COLOR_BLUE = "\u001B[34m";
    private static final String RESET_COLOR = "\u001B[0m";
    // Constructor de PriorityQueueExample
    public PriorityQueueExample(Comparator<Node<T>> comparator) {
        queue = new PriorityQueue<>(comparator);
    }

    // Método para agregar un nodo a la cola de prioridad de manera sincronizada
    public void add(T value, int priority, boolean isLast) {
        synchronized (lock) {
            Node<T> newNode = new Node<>(value, priority);
            queue.add(newNode);

            // Imprimir en color rojo cuando se añade un elemento a la cola
            System.out.println(COLOR_RED + "Añadido: " + newNode + RESET_COLOR);

            if (isLast) {
                producerDone = true; // El productor ha terminado de agregar elementos
            }
            lock.notify(); // Notifica a los hilos en espera
        }
    }

    // Método para extraer el nodo con la prioridad más alta de manera sincronizada
    public Node<T> pollHighestPriorityNode() {
        synchronized (lock) {
            while (queue.isEmpty()) {
                if (producerDone) {
                    return null; // Si el productor ha terminado y la cola está vacía, no hay más pacientes por atender
                }
                try {
                    lock.wait(); // Espera si la cola está vacía
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Hilo interrumpido.");
                }
            }
            Node<T> node = queue.poll();
            System.out.println(COLOR_BLUE + "Atendido: " + node + RESET_COLOR);
            return node;
        }
    }

    // Método para verificar si la cola de prioridad está vacía
    public boolean isEmpty() {
        synchronized (lock) {
            return queue.isEmpty();
        }
    }

    // Método para verificar si el productor ha terminado
    public boolean isProducerDone() {
        return producerDone;
    }
}
