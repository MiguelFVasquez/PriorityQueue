package Threads;
import Model.Node;
import Model.Pacient;
import Model.PriorityQueueExample;

public class Consumer implements Runnable {
    private PriorityQueueExample<Pacient> queue;

    // Constructor para inicializar el consumidor con la cola de prioridad
    public Consumer(PriorityQueueExample<Pacient> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            // Extrae y atiende al paciente con mayor prioridad
            Node<Pacient> patientNode = queue.pollHighestPriorityNode();
            if (patientNode == null) {
                // El hilo se detiene si no hay más pacientes por atender
                System.out.println("Todos los pacientes han sido atendidos.");
                break;
            }

            // Simula un retraso para observar el funcionamiento
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Consumidor interrumpido.");
            }
        }
    }
}


