/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.clases.agenda;

/**
 *
 * @author nelson
 */
public class Contactos extends Personas
{
    String idUsuario;
    
    public Contactos()
    {
        super();
    }
    
    public Contactos(String idUsuario,String nombre,String apellidos,String direccion,String poblacion,String provincia,String nacionalidad,String email,Integer telefono)
    {
        this.idUsuario=idUsuario;
        super.setNombre(nombre);
        super.setApellidos(apellidos);
        super.setDireccion(direccion);
        super.setPoblacion(poblacion);
        super.setProvincia(provincia);
        super.setNacionalidad(nacionalidad);
        super.setEmail(email);
        super.setTelefono(telefono);
    }
}
