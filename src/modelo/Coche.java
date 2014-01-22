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
public class Coche implements Runnable {
    private String nombre;
    private long clientes;
    private Semaphore sem;
    
    public Coche(Semaphore sem,String nombre) {
        this.nombre = nombre;
        this.clientes = 0;
        this.sem = sem;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Semaphore getSem() {
        return sem;
    }

    public void setSem(Semaphore sem) {
        this.sem = sem;
    }

    public long getClientes() {
        return clientes;
    }

    public void setClientes(long clientes) throws Exception {
        if(clientes<=3) {
            this.clientes = clientes;
        } else throw new Exception("n clientes");
    }
    
    public boolean compruebaDisponible() {
        if(clientes<2) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void run() {
        while(true) {
            System.out.println("COCHE");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Coche.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(this.getClientes()==2) {
                try {
                    System.out.println("El coche "+this.getNombre()+ " ha arrancado");
                    Thread.sleep(10000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Coche.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    this.setClientes(0);
                    System.out.println("El coche "+this.getNombre()+ " ha dejado a 2 clientes fuera");
                    sem.release();
                } catch (Exception ex) {
                    Logger.getLogger(Coche.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
