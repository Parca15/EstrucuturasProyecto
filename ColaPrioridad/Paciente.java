package ColaPrioridad;

public class Paciente {
    private String nombre;
    private int gravedad; // 0: Leve, 1: Moderada, 2: Grave, 3: Crítica

    public Paciente(String nombre, int gravedad) {
        this.nombre = nombre;
        this.gravedad = gravedad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getGravedad() {
        return gravedad;
    }

    public void setGravedad(int gravedad) {
        this.gravedad = gravedad;
    }

    @Override
    public String toString() {
        String nivelGravedad = switch (gravedad) {
            case 0 -> "Leve";
            case 1 -> "Moderada";
            case 2 -> "Grave";
            case 3 -> "Crítica";
            default -> "Desconocida";
        };

        return "Paciente: " + nombre + " | Gravedad: " + nivelGravedad;
    }
}
