import javax.swing.*;
import Estudiante.estudiantes;
import Estudiante.form1;

public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Sistema de gestión de estudiantes");
        frame.setContentPane(new form1().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 700);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);


    }
}