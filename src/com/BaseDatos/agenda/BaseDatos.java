/*
Clase para base de datos
*/

package com.BaseDatos.agenda;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author nelson
 */
public class BaseDatos
{
    private String DRIVER = "com.mysql.jdbc.Driver";
    private String USUARIO = "root";
    private String PASS = "";
    private String BD = "agenda";
    private String SERVER = "jdbc:mysql://localhost/"+BD;
            
    //constructores
    public BaseDatos(){}
    
    /***
     * Constructor el cual le pasamos un array de Strings con el orden
     * campo[0]/valor[1].
     * 
     * Los campos posibles son:
     * DRIVER,USUARIO,BD,SERVER
     * 
     * @param datos 
     */
    public BaseDatos(String datos[][])
    {
        if(datos.length<=0 || datos.length>4)
        {
            System.out.println("Constructor creado incorrectamente");
            
        }else
        {
            for(int fila=0;fila<datos.length;fila++)
            {
                switch(datos[fila][0].toUpperCase())
                {
                    case "DRIVER":
                        this.DRIVER=datos[fila][1];
                        break;
                    case "USUARIO":
                        this.USUARIO=datos[fila][1];
                        break;
                    case "PASS":
                        this.PASS=datos[fila][1];
                        break;
                    case "BD":
                        this.BD=datos[fila][1];
                        break;
                    case "SERVER":
                        this.SERVER=datos[fila][1];
                        break;

                    default:
                        JOptionPane.showMessageDialog(null,"Error en el campo/valor. Los campos posibles son: \n"
                                + "DRIVER\n"
                                +"USUARIO\n"
                                +"PASS\n"
                                +"BD\n"
                                +"SERVER",
                                "ERROR!!",JOptionPane.ERROR_MESSAGE
                        );
                }
            }
        }
    }
        
    public BaseDatos(String driver,String usuario,String pass,String bd,String server)
    {
            this.DRIVER=driver;
            this.USUARIO=usuario;
            this.PASS=pass;
            this.BD=bd;
            this.SERVER=server;
        }

    
    
    //zona getters

    /**
     * @return the DRIVER
     */
    public String getDRIVER()
    {
        return DRIVER;
    }

    /**
     * @return the USUARIO
     */
    public String getUSUARIO()
    {
        return USUARIO;
    }

    /**
     * @return the PASS
     */
    public String getPASS()
    {
        return PASS;
    }

    /**
     * @return the BD
     */
    public String getBD()
    {
        return BD;
    }

    /**
     * @return the SERVER
     */
    public String getSERVER()
    {
        return SERVER;
    }

    //zona setters
    
    
    /**
     * @param DRIVER the DRIVER to set
     */
    public void setDRIVER(String DRIVER)
    {
        this.DRIVER = DRIVER;
    }

    /**
     * @param USUARIO the USUARIO to set
     */
    public void setUSUARIO(String USUARIO)
    {
        this.USUARIO = USUARIO;
    }

    /**
     * @param PASS the PASS to set
     */
    public void setPASS(String PASS)
    {
        this.PASS = PASS;
    }

    /**
     * @param BD the BD to set
     */
    public void setBD(String BD)
    {
        this.BD = BD;
    }

    /**
     * @param SERVER the SERVER to set
     */
    public void setSERVER(String SERVER)
    {
        this.SERVER = SERVER;
    }
    
    /**
     * Método que devuelve una conexión a la base de datos
     * 
     * @return Connection 
     */
   private Connection conexion()
   {
       
       try
       {
           Class.forName(this.getDRIVER()).newInstance();
           
           Connection connection=DriverManager.getConnection(this.getSERVER(), this.getUSUARIO(),this.getPASS());
           
           //debug info
           if (connection !=null)
           {
               System.out.println("connected");
           }else
           {
               System.out.println("no connected");
           }
           
           return connection;
           
       }catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex)
       {
           JOptionPane.showMessageDialog(null, "Error en la conexión con la base de datos: "+ex.getMessage(),"Error!",JOptionPane.ERROR_MESSAGE);
           System.out.println("error: "+ex.getMessage());
       }
       return null;
   }
   
   /**
    * Desconexión de una base de datos
    * 
    * @param connect
    * @return true desconectado/false error
    */
   private boolean desconectar(Connection connect)
   {
       try
       {
           connect.close();
           System.out.println("disconnected");
           return true;
           
       }catch(SQLException ex)
       {
           JOptionPane.showMessageDialog(null, "Error en la desconexión de la base de datos: "+ex.getMessage(),"Error!",JOptionPane.ERROR_MESSAGE);
           System.out.println("error: "+ex.getMessage());
           return false;
       }catch(Exception ex)
       {
           JOptionPane.showMessageDialog(null, "Error en la desconexión de la base de datos: "+ex.getMessage(),"Error!",JOptionPane.ERROR_MESSAGE);
           System.out.println("error: "+ex.getMessage());
           return false;
       }
       
   }
   
   /**
    * Método por el cual recibimos datos al pasarle los datos
    * 
    * @param type tipo consulta SELECT, UPDATE, INSERT...
    * @param table tabla en la que consultamos
    * @param fields campos que usamos o queremos recibir
    * @param values campos con valores a insertar
    */
   public ResultSet consulta(String type,String table,String fields[],String values[],String conditional,String compare)
   {
       String sqlQuery="";
       
       PreparedStatement ps;
       ResultSet rs;
       Boolean esSelect=false;
       Connection con=this.conexion();
       
       try
       {
           
           String camposBusqueda="";
           String camposValores="";
           
           //preparación variables consulta
           //campos
           for (int campos=0;campos<fields.length;campos++)
                   {
                       camposBusqueda=camposBusqueda+",`"+fields[campos]+"`";
                   }
           
                        camposBusqueda=camposBusqueda.substring(1,camposBusqueda.length());//elimino la primera ,
           
                  
           
           //seleccion y preparación de sqlQuery
                        
           switch(type.toUpperCase())
           {
               case "SELECT":
                   
                   if(camposBusqueda.equals("`*`"))
                   {
                       sqlQuery="SELECT * from "+table+" ";
                   }else
                   {
                    sqlQuery="SELECT "+camposBusqueda+" from "+table+" ";
                   }
                   //control sobre condicionales where id...etc en select
                   if (conditional!=null)
                   {
                       sqlQuery=sqlQuery+conditional;
                   }
                   
                   sqlQuery=sqlQuery+";";
                   
                    System.out.println(sqlQuery);
                    esSelect=true;
                   break;
                   
               case "INSERT":
                   int campos=0;
                   for (campos=0;campos<values.length;campos++)
                   {
                       camposValores="'"+camposValores+",'"+values[campos]+"'";
                   }
           
                        camposValores=camposValores.substring(campos+1,camposValores.length());//elimino la primera ,
           
                   sqlQuery="INSERT INTO `"+table+"` ("+camposBusqueda+") VALUES ("+camposValores+");";
                   
                   System.out.println(sqlQuery);
                   break;
                   
               case "UPDATE":
                   
                   for (campos=0;campos<values.length;campos++)
                   {
                       camposValores="`"+camposValores+"`"+fields[campos]+"`='"+values[campos]+"', ";
                   }
                   camposValores=camposValores.substring(campos-1,camposValores.length());//elimino la primera ,
                   camposValores=camposValores.substring(1,camposValores.length()-2);//elimino la ultima ,
                   
                   sqlQuery="UPDATE `"+table+"` SET "+camposValores+" ";
                   
                   if (conditional!=null)
                   {
                       sqlQuery=sqlQuery+conditional;
                   }
                   
                   sqlQuery=sqlQuery+";";
                   
                   System.out.println(sqlQuery);
                   
                   break;
                   
               case "DELETE":
                   for ( campos=0;campos<values.length;campos++)
                   {
                       if(values.length>1)
                       {
                            camposValores=camposValores+"`"+fields[campos]+"`='"+values[campos]+"' "+compare+" ";
                       }else
                       {
                           camposValores=camposValores+"`"+fields[campos]+"`='"+values[campos]+"'";
                       }
                   }
                   
                   if(values.length>1)
                   {
                       camposValores=camposValores.substring(0,camposValores.length()-4);//elimino la ultima ,
                   }
                   
                   sqlQuery="DELETE FROM `"+table+"` WHERE "+camposValores+";";
                   System.out.println(sqlQuery);
                   break;
           }
           
           ps=con.prepareStatement(sqlQuery);
           
           if(!esSelect)
           {
               if(!ps.execute(sqlQuery))
               {
                   //JOptionPane.showMessageDialog(null, "Operación realizada con éxito","Correcto",JOptionPane.INFORMATION_MESSAGE);
                   this.desconectar(con);
                   System.out.println("datos guardados");
               }else
               {
                   JOptionPane.showMessageDialog(null, "Operación fallida","Error",JOptionPane.ERROR_MESSAGE);
                   this.desconectar(con);
                   System.out.println("error al guardar los datos");
               }
           }else
           {
               rs=ps.executeQuery(sqlQuery);
               ResultSetMetaData rsmd=rs.getMetaData();//obtencion metadatos de la tabla
               int numeroColumnas=rsmd.getColumnCount();
               
               while(rs.next())
               {
                   if(esSelect && camposBusqueda.equals("`*`"))
                   {
                       for(int c=1;c<=numeroColumnas;c++)
                       {
                           System.out.print(rs.getString(rsmd.getColumnLabel(c))+" "); 
                       }
                       System.out.println("");
                   }
                   else
                   {
                       for(int c=0;c<fields.length;c++)
                       {
                           System.out.print(rs.getString(fields[c])+" ");
                       }
                       System.out.println("");
                   }
              }
               
                   rs.first();
                   return rs;
           }
              
       }catch(Exception ex)
       {
           System.out.println("error: "+ex.getMessage());
       }
       
        return null;
   }
        
}
