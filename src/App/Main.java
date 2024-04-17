package App;

import Model.Node;
import Model.Pacient;
import java.util.Comparator;
import Model.PriorityQueueExample;
import Threads.Consumer;
import Threads.Producer;

public class Main {
    public static void main(String[] args) {
        // Crear una cola de prioridad de pacientes con un comparador personalizado por edad (prioridad)
        Comparator<Node<Pacient>> comparator = new Comparator<Node<Pacient>>() {
            @Override
            public int compare(Node<Pacient> n1, Node<Pacient> n2) {
                return Integer.compare(n2.getPriority(), n1.getPriority()); // Orden descendente por edad (prioridad)
            }
        };

        PriorityQueueExample<Pacient> queue = new PriorityQueueExample<>(comparator);

        // Crear el productor y consumidor
        Producer producer = new Producer(queue, 10); // Productor añade hasta 10 pacientes
        Consumer consumer = new Consumer(queue); // Consumidor atiende pacientes

        // Crear hilos para el productor y el consumidor
        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        // Iniciar los hilos
        producerThread.start();
        consumerThread.start();

        // Esperar a que ambos hilos terminen
        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupción de hilos.");
            Thread.currentThread().interrupt();
        }
    }
}