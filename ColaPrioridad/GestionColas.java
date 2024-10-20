package ColaPrioridad;

import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.Queue;

public class GestionColas {
    // Cola de prioridad para pacientes con gravedad mayor a 0 (Moderada, Grave, Crítica)
    private PriorityQueue<Paciente> colaPrioridad;
    // Cola normal para pacientes con gravedad 0 (Leve)
    private Queue<Paciente> colaNormal;

    public GestionColas() {
        // Ordenar por gravedad, mayor valor de gravedad tiene más prioridad
        colaPrioridad = new PriorityQueue<>((p1, p2) -> Integer.compare(p2.getGravedad(), p1.getGravedad()));
        colaNormal = new LinkedList<>();
    }

    // Agregar pacientes según su nivel de gravedad
    public void agregarPaciente(Paciente paciente) {
        if (paciente.getGravedad() > 0) { // Gravedad mayor que 0 va a la cola de prioridad
            colaPrioridad.offer(paciente);
        } else {
            colaNormal.offer(paciente); // Gravedad 0 (leve) va a la cola normal
        }
    }

    // Atender paciente: prioridad primero y luego normal
    public Paciente consumirPaciente() {
        if (!colaPrioridad.isEmpty()) {
            return colaPrioridad.poll();
        } else if (!colaNormal.isEmpty()) {
            return colaNormal.poll();
        }
        return null;
    }

    // Obtener la cola de pacientes con prioridad (gravedad > 0)
    public Queue<Paciente> getColaPrioridad() {
        return colaPrioridad;
    }

    // Obtener la cola de pacientes con gravedad leve (gravedad == 0)
    public Queue<Paciente> getColaNormal() {
        return colaNormal;
    }
}
