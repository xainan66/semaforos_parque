/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author postal
 */
public class Taquilla implements Runnable {
    private Museo museo;
    private Semaphore semMuseo;
    
    public Taquilla(Museo museo) {
        this.museo = museo;
        this.semMuseo = museo.getSemMuseo();
    }

    public Museo getMuseo() {
        return museo;
    }

    public void setMuseo(Museo museo) {
        this.museo = museo;
    }

    public Semaphore getSem() {
        return semMuseo;
    }

    public void setSem(Semaphore sem) {
        this.semMuseo = sem;
    }

    @Override
    public void run() {
        while(true) {
            System.out.println("TAQUILLA");
            if(museo.getClientes()<10) {
                try {
                    semMuseo.acquire();
                    museo.setClientes(museo.getClientes()+1);
                    System.out.println("Ha entrado un cliente al museo. CLIENTES: "+museo.getClientes());
                } catch (Exception ex) {
                    Logger.getLogger(Taquilla.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
