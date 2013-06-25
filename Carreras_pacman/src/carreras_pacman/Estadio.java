package carreras_pacman;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class Estadio implements ActionListener {

    JFrame jf;
    JPanel jp2;
    Canvas canvas;
    JButton iniciar, parar;
    JTextField hilos;
    JLabel jl;
    int pista, opc;
    ArrayList<Pacman> array_pacman;

    Estadio() {
        opc = 0;
        array_pacman = new ArrayList<Pacman>();
        pista = 650;
        jf = new JFrame();
        jf.setLayout(null);

        canvas = new Canvas();
        canvas.setBounds(5, 5, 700, 350);
        canvas.setBackground(Color.gray);

        jp2 = new JPanel();
        jp2.setBounds(705, 5, 185, 350);
        jp2.setBackground(Color.BLACK);
        jp2.setLayout(null);

        iniciar = new JButton("INICIA");
        iniciar.setBounds(60, 200, 90, 30);
        iniciar.setBackground(Color.CYAN);
        iniciar.setActionCommand("inicia");
        iniciar.addActionListener(this);

        parar = new JButton("DET/REAN");
        parar.setBounds(60, 245, 90, 30);
        parar.setBackground(Color.yellow);
        parar.setActionCommand("parar");
        parar.addActionListener(this);

        jl = new JLabel("¿Cuantos participantes?");
        jl.setBounds(30, 100, 150, 30);
        jl.setBackground(Color.white);

        hilos = new JTextField();
        hilos.setBounds(70, 125, 30, 30);

        jp2.add(jl);
        jp2.add(hilos);
        jp2.add(iniciar);
        jp2.add(parar);

        jf.add(canvas);
        jf.add(jp2);

        jf.setSize(910, 400);
        jf.setBackground(Color.yellow);
        jf.setLocationRelativeTo(null);
        jf.setResizable(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }

    public static void main(String[] args) {
        new Estadio();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("inicia")) {

            if (hilos.getText() != null && valida(hilos.getText())) {
                int jugadores = Integer.parseInt(hilos.getText());
                if (jugadores <= 10 && jugadores > 0) {
                    int espacio = (canvas.getHeight() - 40) / jugadores;
                    int distancia = 20;
                    for (int i = 0; i < jugadores; i++) {
                        array_pacman.add(new Pacman(canvas, distancia, pista, i));
                        distancia += espacio;
                    }
                    for (int i = 0; i < array_pacman.size(); i++) {
                        array_pacman.get(i).start();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "MENOS DE 10 JUGADORES");
                }

            } else {
            }

        }
        if (e.getActionCommand().equals("parar")) {
            if (opc == 0) {
                for (int i = 0; i < array_pacman.size(); i++) {
                    array_pacman.get(i).suspend();
                }
                opc=1;
            }else{
                 for (int i = 0; i < array_pacman.size(); i++) {
                    array_pacman.get(i).resume();
                }
                opc=0;
            }
        }

    }

    public boolean valida(String cadena) {
        boolean ban = true;
        for (int i = 0; i < cadena.length() && ban; i++) {
            if (!Character.isDigit(cadena.charAt(i))) {
                ban = false;
            }
        }
        return ban;
    }
}
