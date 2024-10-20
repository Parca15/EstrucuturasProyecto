package ColaPrioridad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazColas extends JFrame {
    private GestionColas gestionColas;
    private JTextArea colaPrioridadTextArea;
    private JTextArea colaNormalTextArea;
    private JLabel pacienteAtendidoLabel;

    public InterfazColas() {
        gestionColas = new GestionColas();

        setTitle("Gestión de Pacientes");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior para mostrar colas de pacientes
        JPanel panelColas = new JPanel(new GridLayout(1, 2, 10, 10));

        colaPrioridadTextArea = new JTextArea();
        colaPrioridadTextArea.setEditable(false);
        colaPrioridadTextArea.setBorder(BorderFactory.createTitledBorder("Pacientes con Alta Prioridad"));
        JScrollPane scrollPrioridad = new JScrollPane(colaPrioridadTextArea);

        colaNormalTextArea = new JTextArea();
        colaNormalTextArea.setEditable(false);
        colaNormalTextArea.setBorder(BorderFactory.createTitledBorder("Pacientes con Prioridad Normal"));
        JScrollPane scrollNormal = new JScrollPane(colaNormalTextArea);

        panelColas.add(scrollPrioridad);
        panelColas.add(scrollNormal);

        // Panel inferior para botones y resultados
        JPanel panelBotones = new JPanel(new GridLayout(2, 1, 10, 10));

        // Panel para añadir paciente
        JPanel panelAgregar = new JPanel();
        panelAgregar.setLayout(new FlowLayout());

        JButton btnAgregarPaciente = new JButton("Añadir Paciente");
        panelAgregar.add(btnAgregarPaciente);

        JLabel gravedadLabel = new JLabel("Gravedad:");
        panelAgregar.add(gravedadLabel);

        String[] opcionesGravedad = {"Leve", "Moderada", "Grave", "Crítica"};
        JComboBox<String> gravedadComboBox = new JComboBox<>(opcionesGravedad);
        panelAgregar.add(gravedadComboBox);

        panelBotones.add(panelAgregar);

        // Panel para atender paciente
        JPanel panelAtender = new JPanel();
        panelAtender.setLayout(new FlowLayout());

        JButton btnAtenderPaciente = new JButton("Atender Paciente");
        panelAtender.add(btnAtenderPaciente);

        pacienteAtendidoLabel = new JLabel("Último paciente atendido: Ninguno");
        panelAtender.add(pacienteAtendidoLabel);

        panelBotones.add(panelAtender);

        // Añadir listeners
        btnAgregarPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarPaciente(gravedadComboBox.getSelectedIndex());
            }
        });

        btnAtenderPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atenderPaciente();
            }
        });

        // Añadir paneles al layout principal
        add(panelColas, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void agregarPaciente(int gravedadSeleccionada) {
        String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre del paciente:");
        if (nombre != null && !nombre.trim().isEmpty()) {
            Paciente paciente = new Paciente(nombre, gravedadSeleccionada);
            gestionColas.agregarPaciente(paciente);
            actualizarColas();
        } else {
            JOptionPane.showMessageDialog(this, "El nombre del paciente no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atenderPaciente() {
        Paciente pacienteAtendido = gestionColas.consumirPaciente();
        if (pacienteAtendido != null) {
            pacienteAtendidoLabel.setText("Último paciente atendido: " + pacienteAtendido);
        } else {
            pacienteAtendidoLabel.setText("No hay pacientes para atender.");
        }
        actualizarColas();
    }

    private void actualizarColas() {
        colaPrioridadTextArea.setText("");
        colaNormalTextArea.setText("");

        for (Paciente paciente : gestionColas.getColaPrioridad()) {
            colaPrioridadTextArea.append(paciente + "\n");
        }

        for (Paciente paciente : gestionColas.getColaNormal()) {
            colaNormalTextArea.append(paciente + "\n");
        }
    }

    public static void main(String[] args) {
        new InterfazColas();
    }
}
