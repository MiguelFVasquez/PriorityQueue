package Model;
public class Pacient {
    private String name;
    private int age;

    // Constructor para inicializar un paciente con nombre y edad
    public Pacient(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Métodos de acceso
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // Método toString para representar un paciente como una cadena de texto
    @Override
    public String toString() {
        return "Paciente: " + name + ", Edad: " + age;
    }
}
