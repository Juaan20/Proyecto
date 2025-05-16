/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bloop;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 *
 * @author Victus
 */
public class Hilos extends Thread {
    
        Random aleatorio = new Random();
    JProgressBar L;
    JLabel label;
    JDialog dialogo;

    public Hilos() {
    }

    public Hilos(JProgressBar L, JLabel label, JDialog dialogo) {
        this.L = L;
        this.label = label;
        this.dialogo = dialogo;
    }

    @Override
    public void run() {
        int valor = L.getValue();

        while (valor < 100) {
            try {
                int tiempoEspera = 100 + aleatorio.nextInt(900);
                Thread.sleep(tiempoEspera);

                int incremento = 1 + aleatorio.nextInt(5);
                valor = Math.min(valor + incremento, 100);

                L.setValue(valor);
                label.setText(valor + "%");

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        
        dialogo.dispose();
    }
}
