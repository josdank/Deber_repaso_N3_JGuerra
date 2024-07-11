package Estudiante;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ingreso {
    private JTextField nombre;
    private JTextField apellido;
    private JTextField cedula;
    private JTextField b1;
    private JTextField b2;
    private JButton Registrar;
    public JPanel jpanel;
    private JButton volverButton;

    public ingreso() {
        Registrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreEstudiante = nombre.getText();
                String apellidoEstudiante = apellido.getText();
                String cedulaEstudiante = cedula.getText();
                String b1Estudiante = b1.getText();
                String b2Estudiante = b2.getText();

                // Validaciión de los campos
                if (nombreEstudiante.isEmpty() || apellidoEstudiante.isEmpty() ||
                        cedulaEstudiante.isEmpty() || b1Estudiante.isEmpty() || b2Estudiante.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.");
                    return;
                }

                //Inserción de la base
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/base", "root", "");
                    String sql = "INSERT INTO base_estudiante (nombre, apellido, cedula, b1, b2) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, nombreEstudiante);
                    pstmt.setString(2, apellidoEstudiante);
                    pstmt.setString(3, cedulaEstudiante);
                    pstmt.setDouble(4, Double.parseDouble(b1Estudiante));
                    pstmt.setDouble(5, Double.parseDouble(b2Estudiante));
                    pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Estudiante registrado correctamente.");
                    conn.close();
                } catch (SQLException | NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error al insertar estudiante: " + ex.getMessage());
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
}
