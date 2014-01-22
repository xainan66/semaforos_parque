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
public class Museo implements Runnable{
    private long clientes;
    private Semaphore semMuseo;
    private Coche verde;
    private Coche rojo;
    private Coche azul;
    
    public Museo(Semaphore semMuseo,Coche verde,Coche rojo,Coche azul) {
        this.clientes = 0;
        this.semMuseo = semMuseo;
        this.verde = verde;
        this.rojo = rojo;
        this.azul = azul;
    }

    public Semaphore getSemMuseo() {
        return semMuseo;
    }

    public void setSemMuseo(Semaphore semMuseo) {
        this.semMuseo = semMuseo;
    }

    public long getClientes() {
        return clientes;
    }

    public void setClientes(long clientes) throws Exception {
        if(clientes<=10) {
            this.clientes = clientes;
        } else throw new Exception("n clientes");
    }
    
    private void entradaCliente() {
        if(this.getClientes()<=10) {
            semMuseo.release();
        }
    }
    
    private void salidaCliente() {
        if(verde.compruebaDisponible()) {
            try {
                verde.getSem().acquire();
                this.setClientes(this.getClientes()-1);
                verde.setClientes(verde.getClientes()+1);
                System.out.println("Ha salido un cliente hacia el coche"+verde.getNombre());
            } catch (Exception ex) {
                Logger.getLogger(Museo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } //else if(azul.compruebaDisponible()) {
//            try {
//                azul.getSem().acquire();
//                this.setClientes(this.getClientes()-1);
//                azul.setClientes(azul.getClientes()+1);
//                System.out.println("Ha salido un cliente hacia el coche"+azul.getNombre());
//            } catch (Exception ex) {
//                Logger.getLogger(Museo.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } else if(rojo.compruebaDisponible()) {
//            try {
//                rojo.getSem().acquire();
//                this.setClientes(this.getClientes()-1);
//                rojo.setClientes(rojo.getClientes()+1);
//                System.out.println("Ha salido un cliente hacia el coche"+rojo.getNombre());
//            } catch (Exception ex) {
//                Logger.getLogger(Museo.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    }

    @Override
    public void run() {
        while(true) {
            System.out.println("MUSEO");
            entradaCliente();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Museo.class.getName()).log(Level.SEVERE, null, ex);
            }
            salidaCliente();
        }
    }

}
