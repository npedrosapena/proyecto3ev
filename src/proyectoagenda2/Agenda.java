/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyectoagenda2;

import com.BaseDatos.agenda.BaseDatos;
import com.clases.agenda.Personas;
import com.clases.agenda.Usuarios;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author nelson
 */
public class Agenda
{
    public Agenda(){}
    
    BaseDatos bd= new BaseDatos();
    
    /**Pasandole un contacto y el id del usuario que lo crea, se inserta
     * en la BD
     * 
     * @param contacto
     * @param idusuario
     * @return 
     */
    public boolean crearContacto(Personas contacto,String idusuario)
    {
        //datos contacto
        String datos[]=
        {
            idusuario,
            contacto.getNombre(),
            contacto.getApellidos(),
            contacto.getDireccion(),
            contacto.getPoblacion(),
            contacto.getProvincia(),
            contacto.getNacionalidad(),
            contacto.getTelefono().toString(),
            contacto.getEmail()
        };
        
        //campos BD
        String campos[]=
        {
            "idusuario",
            "nombre",
            "apellidos",
            "direccion",
            "poblacion",
            "provincia",
            "nacionalidad",
            "telefono",
            "email"     
        };
        
        bd.consulta("insert", "contactos", campos, datos, null, null);
        
        return true;
    }
    
    public boolean borrarContacto(Personas contacto,String id)
    {
        try
        {
            String idContacto;
            
            String datos[]={"idcontacto"};
            
            String c[]=
            {
                contacto.getNombre(),
                contacto.getApellidos(),
                contacto.getDireccion(),
                contacto.getPoblacion(),
                contacto.getProvincia(),
                contacto.getNacionalidad(),
                contacto.getTelefono().toString(),
                contacto.getEmail()
            };
            
            String condicional=" where `idusuario`='"+id+"' and `telefono`="+contacto.getTelefono();
           
            ResultSet rs=bd.consulta("select", "contactos", datos, c, condicional, null);//obtenemos el id del usuario a eliminar
           
            rs.first();//nos ponemos en la primera posicion del resultset
            
           String f[]={"idcontacto"};
           String v[]={rs.getString(1)};//array con el id del contacto
           
            bd.consulta("delete", "contactos", f, v, null, null);//eliminamos el usuario
            
            return true;
            
        } catch (Exception ex)
        {
            Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    /**
     * Pasado un contacto y un array con sus valores nuevos, recogidos en
     * un array, este actualiza sus datos en la base de datos
     * 
     * @param contacto
     * @param valores
     * @return 
     */
    public boolean modificarContacto(Personas contacto,String identificador)
    {
        try
        {
            BaseDatos bd = new BaseDatos();
            
            String campos[]=
            {
                "nombre",
                "apellidos",
                "direccion",
                "poblacion",
                "provincia",
                "nacionalidad",
                "telefono",
                "email"
            };
            
            String valores[]=
            {
                contacto.getNombre(),
                contacto.getApellidos(),
                contacto.getDireccion(),
                contacto.getPoblacion(),
                contacto.getProvincia(),
                contacto.getNacionalidad(),
                contacto.getTelefono().toString(),
                contacto.getEmail()
            };
            
            bd.consulta("update", "contactos", campos, valores, "where `idcontacto`='"+identificador+"'", null);
            
            return true;
        }catch(Exception ex)
        {
            Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean crearUsuario(Usuarios u)
    {
        //guardamos el usuario y la contraseña en la tabla usuarios
        String campos[]={"nomusuario","claveusuario"};//array de campos usuario DATOS FIJOS!!!
        String valores[]={u.getNomUsuario(),u.getClaveUsuario()};//valores para los campos
        
        this.bd.consulta("insert", "usuarios", campos, valores, null, null);//realizamos la consulta y guardamos los datos en su tabla correspondiente
        
        //guardamos el resto de datos en su tabla correspondiente
        //primero obtendremos su id
        String camposSelect[]={"id"};
        
            String condicional=" where `nomUsuario`='"+u.getNomUsuario()+"' and `claveusuario`='"+u.getClaveUsuario()+"'";//condicional FIJO
           
            ResultSet rs=bd.consulta("select", "usuarios", camposSelect, null, condicional, null);//obtenemos el id del usuario
       
       try           
        {
                rs.first();//nos ponemos en la primera posicion del resultset
                
                String f[]={
                    "idusuario",
                    "nombre",
                    "apellidos",
                    "direccion",
                    "poblacion",
                    "provincia",
                    "nacionalidad",
                    "telefono",
                    "email"
                };//la configuración de la tabla no nos deja
                                          //repetir campos, por lo tanto, siempre va a devolver 1 valor
                String v[]=
                {
                    rs.getString(1),
                    u.getNombre(),
                    u.getApellidos(),
                    u.getDireccion(),
                    u.getPoblacion(),
                    u.getProvincia(),
                    u.getNacionalidad(),
                    u.getTelefono().toString(),
                    u.getEmail()
                };
                                             
                
                System.out.println("id usuario: "+v[0]);
                
                bd.consulta("insert", "datosusuario", f, v, null , null);
                
                
        } catch (SQLException ex)
        {
            Logger.getLogger(Agenda.class.getName()).log(Level.SEVERE, null, ex);
        }
            
          
        
        
        return true;
    }
}
