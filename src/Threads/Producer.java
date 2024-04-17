package Threads;
import Model.Pacient;
import Model.PriorityQueueExample;

import java.util.Random;

public class Producer implements Runnable {
    private PriorityQueueExample<Pacient> queue;
    private Random random = new Random();
    private int maxPatients;

    // Constructor para inicializar el productor con la cola de prioridad y el número máximo de pacientes
    public Producer(PriorityQueueExample<Pacient> queue, int maxPatients) {
        this.queue = queue;
        this.maxPatients = maxPatients;
    }

    @Override
    public void run() {
        for (int i = 0; i < maxPatients; i++) {
            // Genera un nombre y una edad aleatorios para los pacientes
            String name = "Paciente " + (i + 1);
            int age = random.nextInt(80) + 20; // Edad entre 20 y 100

            // Crea un nuevo paciente y lo agrega a la cola de prioridad
            boolean isLast = (i == maxPatients - 1); // Es el último paciente
            queue.add(new Pacient(name, age), age, isLast);

            // Simula un retraso para observar el funcionamiento
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Productor interrumpido.");
            }
        }
    }
}
