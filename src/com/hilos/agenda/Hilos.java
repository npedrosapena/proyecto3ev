/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hilos.agenda;

import com.correo.agenda.correo;
import com.graficos.agenda.Ventanas;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;

/**
 *
 * @author nelson
 */
public class Hilos extends Thread
{
    private String correoReceptor,correoEmisor;
    
    
    public Hilos(String correoReceptor,String correoEmisor)
    {
        this.correoReceptor=correoReceptor;
        this.correoEmisor=correoEmisor;
    }
    
    @Override
    public void run () 
    {
        try
        {
            correo enviarCorreo = new correo("Envio Correo",500,500,correoReceptor,correoEmisor);
            System.out.println("Hilo lanzado");
            enviarCorreo.mostrar();
        } catch (Exception ex)
        {
            Logger.getLogger(Hilos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
