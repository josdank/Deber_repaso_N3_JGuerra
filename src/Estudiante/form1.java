package Estudiante;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class form1 {
    public JButton buscar;
    public JButton registrar;
    public JPanel mainPanel;

    public form1() {
        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Bienvenido");
                JFrame frame = new JFrame("Bienvenido");
                frame.setContentPane(new busqueda().mainPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setPreferredSize(new Dimension(1024, 768));
                frame.pack();
                frame.setVisible(true);

                // Cierra la ventana actual
                ((JFrame) SwingUtilities.getWindowAncestor(buscar)).dispose();
            }
        });
        registrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Bienvenido");
                frame.setContentPane(new ingreso().jpanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setPreferredSize(new Dimension(1000, 700));
                frame.pack();
                frame.setVisible(true);

                // Cierra la ventana actual
                ((JFrame) SwingUtilities.getWindowAncestor(registrar)).dispose();
            }
        });
    }
}