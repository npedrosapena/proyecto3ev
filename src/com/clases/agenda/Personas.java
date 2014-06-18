/*
superclase personas
 */

package com.clases.agenda;

/**
 *
 * @author nelson
 */
public class Personas
{
    private String 
            nombre,
            apellidos,
            direccion,
            poblacion,
            provincia,
            nacionalidad,
            email;
    
    private Integer
            telefono;
    
    //constructores
    public Personas(){}
    public Personas(String nombre,String apellidos,String direccion,String poblacion,String provincia,String nacionalidad,String email,Integer telefono)
    {
        this.nombre=nombre;
        this.apellidos=apellidos;
        this.direccion=direccion;
        this.provincia=provincia;
        this.nacionalidad=nacionalidad;
        this.email=email;
        this.telefono=telefono;
    }
    
    //métodos get

    /**
     * @return the nombre
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * @return the apellidos
     */
    public String getApellidos()
    {
        return apellidos;
    }

    /**
     * @return the direccion
     */
    public String getDireccion()
    {
        return direccion;
    }

    /**
     * @return the poblacion
     */
    public String getPoblacion()
    {
        return poblacion;
    }

    /**
     * @return the provincia
     */
    public String getProvincia()
    {
        return provincia;
    }

    /**
     * @return the nacionalidad
     */
    public String getNacionalidad()
    {
        return nacionalidad;
    }

    /**
     * @return the email
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * @return the telefono
     */
    public Integer getTelefono()
    {
        return telefono;
    }

    
    //métodos set
    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos)
    {
        this.apellidos = apellidos;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion)
    {
        this.direccion = direccion;
    }

    /**
     * @param poblacion the poblacion to set
     */
    public void setPoblacion(String poblacion)
    {
        this.poblacion = poblacion;
    }

    /**
     * @param provincia the provincia to set
     */
    public void setProvincia(String provincia)
    {
        this.provincia = provincia;
    }

    /**
     * @param nacionalidad the nacionalidad to set
     */
    public void setNacionalidad(String nacionalidad)
    {
        this.nacionalidad = nacionalidad;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(Integer telefono)
    {
        this.telefono = telefono;
    }
       
}