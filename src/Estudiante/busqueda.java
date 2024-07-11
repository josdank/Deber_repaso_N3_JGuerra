package Estudiante;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class busqueda {
    private JLabel nombre;
    private JLabel cedula;
    private JTextField Nombre;
    private JTextField Cedula;
    public JButton buscar;
    public  JPanel mainPanel;
    private JButton volverButton;

    public busqueda() {
        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreEstudiante = Nombre.getText();
                String cedulaEstudiante = Cedula.getText();

                // Validación de los campos
                if (nombreEstudiante.isEmpty() && cedulaEstudiante.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese nombre o cédula para buscar.");
                    return;
                }

                // Búsqueda en la base de datos
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/base", "root", "");
                    String sql = "SELECT * FROM base_estudiante WHERE nombre = ? OR cedula = ?";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, nombreEstudiante);
                    pstmt.setString(2, cedulaEstudiante);
                    ResultSet rs = pstmt.executeQuery();

                    if (rs.next()) {
                        String nombre = rs.getString("nombre");
                        String apellido = rs.getString("apellido");
                        String cedula = rs.getString("cedula");
                        double b1 = rs.getDouble("b1");
                        double b2 = rs.getDouble("b2");

                        JOptionPane.showMessageDialog(null, "Estudiante encontrado:\n" +
                                "Nombre: " + nombre + "\nApellido: " + apellido + "\nCédula: " + cedula +
                                "\nB1: " + b1 + "\nB2: " + b2);
                    } else {
                        int option = JOptionPane.showConfirmDialog(null, "Estudiante no encontrado. ¿Desea registrar un nuevo estudiante?",
                                "Estudiante no encontrado", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION) {
                            JFrame registroFrame = new JFrame("Registro de Estudiantes");
                            registroFrame.setContentPane(new ingreso().jpanel);
                            registroFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            registroFrame.pack();
                            registroFrame.setVisible(true);
                        }
                    }
                    conn.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error al buscar estudiante: " + ex.getMessage());
                }
            }
        });
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Sistema de gestión de estudiantes");
                frame.setContentPane(new form1().mainPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800, 700);
                frame.pack();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);

                // Cierra la ventana actual
                ((JFrame) SwingUtilities.getWindowAncestor(volverButton)).dispose();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Búsqueda de Estudiantes");
        frame.setContentPane(new busqueda().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}