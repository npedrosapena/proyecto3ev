/*
Clase Usuarios
*/
package com.clases.agenda;

/**
 *
 * @author nelson
 */
public class Usuarios extends Personas
{
    //atributos de clase
    private String
            nomUsuario,
            claveUsuario;
    
    //constructores
    public Usuarios()
    {
        super();
    }
    
    public Usuarios(String nomUsuario,String claveUsuario,String nombre,String apellidos,String direccion,String poblacion,String provincia,String nacionalidad,String email,Integer telefono)
    {
        this.nomUsuario=nomUsuario;
        this.claveUsuario=claveUsuario;
        super.setNombre(nombre);
        super.setApellidos(apellidos);
        super.setDireccion(direccion);
        super.setPoblacion(poblacion);
        super.setProvincia(provincia);
        super.setNacionalidad(nacionalidad);
        super.setEmail(email);
        super.setTelefono(telefono);
    }
    
    //zona getters

    /**
     * @return the nomUsuario
     */
    public String getNomUsuario()
    {
        return nomUsuario;
    }

    /**
     * @return the claveUsuario
     */
    public String getClaveUsuario()
    {
        return claveUsuario;
    }

    //zona setters
    
    /**
     * @param nomUsuario the nomUsuario to set
     */
    public void setNomUsuario(String nomUsuario)
    {
        this.nomUsuario = nomUsuario;
    }

    /**
     * @param claveUsuario the claveUsuario to set
     */
    public void setClaveUsuario(String claveUsuario)
    {
        this.claveUsuario = claveUsuario;
    }
    
    //zona funciones
    public void ventanaAgenda()
    {
        
    }
}
