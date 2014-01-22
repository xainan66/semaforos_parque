/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package parque;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Coche;
import modelo.Museo;
import modelo.Taquilla;

/**
 *
 * @author postal
 */
public class Parque {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Semaphore semMuseo = new Semaphore(0);
        Semaphore semV = new Semaphore(0);
        Semaphore semR = new Semaphore(0);
        Semaphore semA = new Semaphore(0);
        
        Coche verde = new Coche(semV,"verde");
        Coche rojo = new Coche(semR,"rojo");
        Coche azul = new Coche(semA,"azul");
        Museo museo = new Museo(semMuseo,verde,rojo,azul);
        Taquilla taquilla = new Taquilla(museo);
        
        Thread tVerde = new Thread(verde);
        Thread tRojo = new Thread(rojo);
        Thread tAzul = new Thread(azul);
        Thread tMuseo = new Thread(museo);
        Thread tTaquilla = new Thread(taquilla);
        
        tVerde.setName("verde");
        tRojo.setName("rojo");
        tAzul.setName("azul");
        tMuseo.setName("museo");
        tTaquilla.setName("taquilla");
        tMuseo.start();
        tVerde.start();
//        tRojo.start();
//        tAzul.start();
        tTaquilla.start();
    }
    
}
