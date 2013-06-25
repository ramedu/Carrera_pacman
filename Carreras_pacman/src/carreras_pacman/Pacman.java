package carreras_pacman;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;

class Pacman extends Thread implements MouseListener, ActionListener {//<------------------------------

    Canvas canvas;
    int pos_y, pista;
    Graphics g;
    int recorrido;
    int ancho_pacman;
    int nombre;
    int opc;

    Pacman(Canvas canvas, int pos_y, int pista, int nombre) {
        this.canvas = canvas;
        this.canvas.addMouseListener(this);//<------------------------------
        this.nombre = nombre + 1;
        this.pos_y = pos_y;
        this.pista = pista;
        this.recorrido = 10;
        this.ancho_pacman = 20;
        this.opc = 0;

    }

    public void run() {

        while (recorrido < pista) {
            dibuja_pacman(recorrido, pos_y);
            try {
                Thread.sleep(500);
                borrar_pacman(recorrido, pos_y);
                recorrido += (int) (Math.random() * 30) + 10;
            } catch (InterruptedException ex) {
                Logger.getLogger(Pacman.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void dibuja_pacman(int x, int y) {
        g = canvas.getGraphics();
        g.setColor(Color.yellow);
        g.fillOval(x, y, ancho_pacman, ancho_pacman);
        g.setColor(Color.gray);
        if (opc == 0) {
            g.fillArc(x, y, ancho_pacman, ancho_pacman, 40, -50);
            opc = 1;
        } else {
            opc = 0;
        }

        g.drawString(String.valueOf(nombre), x + 2, y + 14);
    }

    public void borrar_pacman(int x, int y) {
        g.setColor(Color.gray);
        g.fillRect(x - 1, y - 1, ancho_pacman + 2, ancho_pacman + 2);
    }

    public void mousePressed(MouseEvent e) {//<------------------------------
        if (e.getX() > recorrido && e.getX() < recorrido + ancho_pacman && e.getY() > pos_y && e.getY() < pos_y + ancho_pacman) {
            
                g = canvas.getGraphics();
                g.setColor(Color.red);

                g.drawLine(recorrido, pos_y, recorrido + ancho_pacman, pos_y+ancho_pacman);
                g.drawLine(recorrido + ancho_pacman, pos_y, recorrido, pos_y + ancho_pacman);
                this.stop();      
        }
    }

    public void mouseReleased(MouseEvent me) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent me) {
    }

    

    public void mouseClicked(MouseEvent me) {
    }

    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
