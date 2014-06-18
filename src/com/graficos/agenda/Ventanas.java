
package com.graficos.agenda;

import com.BaseDatos.agenda.BaseDatos;
import com.clases.agenda.Contactos;
import com.clases.agenda.Personas;
import com.clases.agenda.Usuarios;
import com.hilos.agenda.Hilos;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import proyectoagenda2.Agenda;

/**
 *
 * @author nelson
 */
public class Ventanas extends JFrame
{
    Agenda ag= new Agenda();
    BaseDatos bd= new BaseDatos();
    DefaultTableModel modelo;
    public String identificador;
    //String correoReceptor;
    
    final JTable tablaDatos= new JTable();
    
    public Ventanas(){}
    
    /**
     * Ventana inicio, en la cual nos logueamos o bien, creamos un usuario
     */
    public void ventanaLogin()
    {
        final JFrame marcoLogin;
        
        
        JButton 
                btnAcceder,
                btnSalir,
                btnCrearUsuario;
        
        JLabel 
                lblNombreUsuario,
                lblClaveUsuario;
        
       final JTextField txtNombreUsuario;
        
       final JPasswordField txtPassword;
        
        marcoLogin=new JFrame("gestor usuario 2014");
        marcoLogin.setLayout(null);
        
        marcoLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marcoLogin.setSize(450,150);
        marcoLogin.setLocationRelativeTo(null);
        marcoLogin.setResizable(false);
        
        //nombre
        lblNombreUsuario= new JLabel("Nombre: ");
        lblNombreUsuario.setBounds(85, 10, 80, 20);
        marcoLogin.add(lblNombreUsuario);
        
        txtNombreUsuario = new JTextField();
        txtNombreUsuario.setBounds(150,10,80,20);
        txtNombreUsuario.transferFocus();
        marcoLogin.add(txtNombreUsuario);
        
        //pass
        lblClaveUsuario= new JLabel("Contraseña: ");
        lblClaveUsuario.setBounds(57, 35, 95, 20);
        marcoLogin.add(lblClaveUsuario);
        
        txtPassword= new JPasswordField();
        txtPassword.setBounds(150, 35, 80, 20);
        txtPassword.requestFocus();
        marcoLogin.add(txtPassword);
        
        //botones
        btnCrearUsuario= new JButton("Crear Usuario");
        btnCrearUsuario.setBounds(10, 80, 135, 20);
        btnCrearUsuario.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent ae)
                    {
                        System.out.println("Crear Usuario");
                        ventanaCrearUsuario();
                    }
                }
                );
        
        marcoLogin.add(btnCrearUsuario);
        
        btnAcceder= new JButton("Acceder");
        btnAcceder.setBounds(160, 80, 135, 20);
        btnAcceder.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent ae)
                    {
                        System.out.println("Acceder");
                        String nombre=txtNombreUsuario.getText();
                        
                            // Obtener el password 
                        char passArray[] = txtPassword.getPassword(); 
                            // Revisar que sean letras y numeros 
                        for (int i = 0; i < passArray.length; i++) 
                        { 
                            char c = passArray[i];
                                // Si no es letra o numero entonces no es valido 
                            if (!Character.isLetterOrDigit(c)) 
                            {
                                System.out.println("no es caracter válido");  
                            }
                        } 

                        // Convertir el password a String 
                        String pass = new String(passArray);
                        
                        String campos[]={"id"};
                        String condicional="where `nomusuario`='"+nombre+"' and `claveusuario`='"+pass+"'";
                        
                        ResultSet rs= bd.consulta("select", "usuarios", campos, null, condicional, null);
                
                try            
                    {
                        //control de resultados

                        rs.first();
                        String id=rs.getString("id");//pregunto si el resultset tiene esta columna
                                                     //en caso de que no la tenga, salta una excepción
                        identificador=id;

                        /*
                        recogida del valor, ID del usuario para poder usar en la ventana de contactos etc...
                        aprobechando que tenemos una consulta hecha, para pasar este valor (id) e ir jugando con
                        el por el programa
                        */
                        
                        //JOptionPane.showMessageDialog(null, "Estas dentro","dentron", JOptionPane.INFORMATION_MESSAGE);
                        
                        marcoLogin.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                        marcoLogin.hide();
                        ventanaAgendaUsuario(id);


                    } catch (SQLException ex)
                    {
                        JOptionPane.showMessageDialog(null, "La clave/usuario introducido/s no es/son correctos.","Fallo autenticación", JOptionPane.WARNING_MESSAGE);
                    }

                    }
                }
        );
        
        marcoLogin.add(btnAcceder);
        
        btnSalir=new JButton("Salir");
        btnSalir.setBounds(310, 80, 125, 20);
        btnSalir.addActionListener(
                new ActionListener()
                {
                    @Override
                            public void actionPerformed(ActionEvent ae)
                            {
                                System.out.println("Adios!");
                                System.exit(0);
                            }
                }
        );
        marcoLogin.add(btnSalir);
        
        
        marcoLogin.setVisible(true);
    }
    
    /**
     * Método crear ventana del usuario, donde recogemos la información
     * del contacto
     */
    public void ventanaCrearUsuario()
    {
        final JFrame marcoCrearUsuario;
        
        final JButton btnCrearContacto,
                btnLimpiar,
                btnCancelar;
        
        final JTextField
                txtNombreUsuario,
                txtApellido,
                txtNick,
                txtClaveUsuario,
                txtDireccion,
                txtPoblacion,
                txtProvincia,
                txtNacionalidad,
                txtTelefono,
                txtEmail;
        
        
        JLabel 
                lblNombreUsuario,
                lblApellido,
                lblNick,
                lblClaveUsuario,
                lblDireccion,
                lblPoblacion,
                lblProvincia,
                lblNacionalidad,
                lblTelefono,
                lblEmail;
        

        
        marcoCrearUsuario= new JFrame("Crear usuario");
        marcoCrearUsuario.setLayout(null);
        marcoCrearUsuario.setSize(440,250);
        marcoCrearUsuario.setLocationRelativeTo(null);
        marcoCrearUsuario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        marcoCrearUsuario.setResizable(false);
        
        
        lblNombreUsuario= new JLabel("Nombre Usuario: ");
        lblNombreUsuario.setBounds(10, 10, 145, 20);
        marcoCrearUsuario.add(lblNombreUsuario);
        
        txtNombreUsuario= new JTextField();
        txtNombreUsuario.setBounds(140,10, 85, 20);
        marcoCrearUsuario.add(txtNombreUsuario);
        
        lblApellido= new JLabel("Apellidos: ");
        lblApellido.setBounds(250, 10, 90, 20);
        marcoCrearUsuario.add(lblApellido);
        
        txtApellido= new JTextField();
        txtApellido.setBounds(325, 10, 90, 20);
        txtApellido.requestFocus();
        marcoCrearUsuario.add(txtApellido);
        
        lblNick= new JLabel("Nick: ");
        lblNick.setBounds(10, 40, 50, 20);
        marcoCrearUsuario.add(lblNick);
        
        txtNick= new JTextField();
        txtNick.setBounds(55, 40, 90, 20);
        marcoCrearUsuario.add(txtNick);
        
        lblClaveUsuario= new JLabel("Clave usuario: ");
        lblClaveUsuario.setBounds(180, 40, 200, 20);
        marcoCrearUsuario.add(lblClaveUsuario);
        
        txtClaveUsuario= new JTextField();
        txtClaveUsuario.setBounds(290, 40, 110, 20);
        marcoCrearUsuario.add(txtClaveUsuario);
        
        lblDireccion= new JLabel("Dirección: ");
        lblDireccion.setBounds(10, 70, 75, 20);
        marcoCrearUsuario.add(lblDireccion);
        
        txtDireccion= new JTextField();
        txtDireccion.setBounds(85, 70, 300, 20);
        marcoCrearUsuario.add(txtDireccion);
        
        lblPoblacion= new JLabel("Población: ");
        lblPoblacion.setBounds(10, 100, 90, 20);
        marcoCrearUsuario.add(lblPoblacion);
        
        txtPoblacion= new JTextField();
        txtPoblacion.setBounds(90, 100, 120, 20);
        marcoCrearUsuario.add(txtPoblacion);
        
        lblProvincia= new JLabel("Provincia: ");
        lblProvincia.setBounds(220, 100, 80, 20);
        marcoCrearUsuario.add(lblProvincia);
        
        txtProvincia= new JTextField();
        txtProvincia.setBounds(290, 100, 95, 20);
        marcoCrearUsuario.add(txtProvincia);
        
        lblNacionalidad= new JLabel("Nacionalidad: ");
        lblNacionalidad.setBounds(10, 130, 110, 20);
        marcoCrearUsuario.add(lblNacionalidad);
        
        txtNacionalidad= new JTextField();
        txtNacionalidad.setBounds(110, 130, 90, 20);
        marcoCrearUsuario.add(txtNacionalidad);
        
        lblEmail= new JLabel("Email: ");
        lblEmail.setBounds(205, 130, 90, 20);
        marcoCrearUsuario.add(lblEmail);
        
        txtEmail= new JTextField();
        txtEmail.setBounds(250, 130, 160, 20);
        marcoCrearUsuario.add(txtEmail);
        
        lblTelefono= new JLabel("Telefono: ");
        lblTelefono.setBounds(10, 160, 90, 20);
        marcoCrearUsuario.add(lblTelefono);
        
        txtTelefono= new JTextField();
        txtTelefono.setBounds(90, 160, 90, 20);
        txtTelefono.addKeyListener(
                new KeyListener()
                {
                    @Override
                    public void keyTyped(KeyEvent e)
                    {
                        
                    }

                @Override
                public void keyPressed(KeyEvent ke)
                {
                    
                }

                @Override
                public void keyReleased(KeyEvent ke)
                {
                    
                    try
                    {
                        Integer.parseInt(txtTelefono.getText());
                    }catch(Exception ex)
                    {
                        JOptionPane.showMessageDialog(null, "Número no válido","Error",JOptionPane.ERROR_MESSAGE);
                        txtTelefono.setText(null);
                        txtTelefono.requestFocus();
                    }
                }
                });
        marcoCrearUsuario.add(txtTelefono);
        
        //botones
        btnCancelar= new JButton("Cancelar");
        btnCancelar.setBounds(10, 200, 100, 20);
        btnCancelar.addActionListener(
        new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                System.out.println("Salimos de la ventana crear contacto");
                marcoCrearUsuario.dispose();
            }
        }
        );
        marcoCrearUsuario.add(btnCancelar);
        
        
        btnLimpiar=new JButton("Limpiar");
        btnLimpiar.setBounds(150, 200, 100, 20);
        btnLimpiar.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent ae)
                    {
                            System.out.println("bonton Limpiar ventana crear contacto");
                            txtNombreUsuario.setText(null);
                            txtApellido.setText(null);
                            txtClaveUsuario.setText(null);
                            txtDireccion.setText(null);
                            txtEmail.setText(null);
                            txtNacionalidad.setText(null);
                            txtNick.setText(null);
                            txtPoblacion.setText(null);
                            txtProvincia.setText(null);
                            txtTelefono.setText(null);
                            txtNombreUsuario.requestFocus();
                    }
                }
        );
        marcoCrearUsuario.add(btnLimpiar);
        
        btnCrearContacto=new JButton("Guardar");
        btnCrearContacto.setBounds(300, 200, 100, 20);
        btnCrearContacto.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent ae)
                    {
                        if(
                        (txtApellido.getText().length()>0)&&
                        (txtDireccion.getText().length()>0)&&
                        (txtEmail.getText().length()>0)&&
                        (txtNacionalidad.getText().length()>0)&&
                        (txtNombreUsuario.getText().length()>0)&&
                        (txtPoblacion.getText().length()>0)&&
                        (txtProvincia.getText().length()>0)&&  
                        (txtTelefono.getText().length()>0)
                      )
                        {
                            
                        
                        System.out.println("guardar usuario");
                        
                        //creamos un objeto usuario
                        Personas usuario= new Usuarios
                        (   txtNick.getText(),
                            txtClaveUsuario.getText(),
                            txtNombreUsuario.getText(),
                            txtApellido.getText(),
                            txtDireccion.getText(),
                            txtPoblacion.getText(),
                            txtProvincia.getText(),
                            txtNacionalidad.getText(),
                            txtEmail.getText(),
                            Integer.parseInt(txtTelefono.getText())
                        );
                        
                            //lo pasamos a agenda para procesar los datos/el objeto
                          if (ag.crearUsuario((Usuarios)usuario))
                          {
                              JOptionPane.showMessageDialog(null, "Usuario guardado correctamente", "Usuario Guardado", JOptionPane.INFORMATION_MESSAGE);
                              marcoCrearUsuario.dispose();
                          }else
                          {
                              JOptionPane.showMessageDialog(null, "Error al guardad el usuario", "Error", JOptionPane.ERROR_MESSAGE);
                          }
                        }else
                        {
                            JOptionPane.showMessageDialog(null, "Hay campos vacíos", "Error", JOptionPane.ERROR_MESSAGE);
                        }
 
                    }
                });
        marcoCrearUsuario.add(btnCrearContacto);
        
       // marcoCrearUsuario.setAlwaysOnTop(true);
        marcoCrearUsuario.setVisible(true);

    }
   
    /**
     * Ventana la cual nos muestra los contactos del usuario y las acciones
     * que puede realizar
     * 
     * @param idUsuario 
     */
    public void ventanaAgendaUsuario(String idUsuario)
    {
        System.out.println("ventanaAgendaUsuario");
        
        final JButton 
                btnNuevoContacto,
                btnSalir,
                btnBuscar,
                btnRecargar;
        
        btnRecargar= new JButton("Recargar");
        
        btnRecargar.setVisible(false);
        btnRecargar.setEnabled(false);
        
        final JTextField txtTextoBuscar = new JTextField();
        JFrame ventanaAgendaUsuario;
        final JComboBox camposBusqueda=new JComboBox();
        
        
        //menu botón derecho
        final JPopupMenu popupMenu = new JPopupMenu();
        
        JMenuItem deleteItem = new JMenuItem("Borrar");//añadir elementos
        deleteItem.addActionListener(new ActionListener() {//añado actionListener

            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    Component c = (Component)e.getSource();
                    JPopupMenu popup = (JPopupMenu)c.getParent();
                    JTable table = (JTable)popup.getInvoker();
                    System.out.println(table.getSelectedRow() + " : " + table.getSelectedColumn());
                    System.out.println("celda id: "+table.getModel().getValueAt(table.getSelectedRow(), 0));//obtengo el valor del id
                    
                    if(JOptionPane.showConfirmDialog(null, "Se va a proceder a borrar el contacto. ¿Desea continuar?","Borrar contacto",JOptionPane.YES_NO_OPTION)<1)
                    {
                        String f[]={"idcontacto"};
                        String v[]={(table.getModel().getValueAt(table.getSelectedRow(), 0).toString())};

                            bd.consulta("delete", "contactos", f, v, "where `idcontacto`='"+(table.getModel().getValueAt(table.getSelectedRow(), 0))+"'", null);

                             String condicion="where `idusuario`='"+identificador+"'";
                         tablaDatos.setModel(cargaDatos(condicion));

                    }else
                    {
                            System.out.println("no borrado");
                    }
                }catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null, "Seleccione una fila de la tabla","Selección",JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        popupMenu.add(deleteItem);//los añado al popup
        
        JMenuItem modificarItem= new JMenuItem("Modificar");
        modificarItem.addActionListener(
        new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
               try
               {
                Component c = (Component)e.getSource();
                JPopupMenu popup = (JPopupMenu)c.getParent();
                JTable table = (JTable)popup.getInvoker();
                System.out.println(table.getSelectedRow() + " : " + table.getSelectedColumn());
                System.out.println("celda id: "+table.getModel().getValueAt(table.getSelectedRow(), 0));//obtengo el valor del id
                
                String v=table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
                
                ventanaModificarContacto(v);
               }catch(Exception ex)
               {
                   JOptionPane.showMessageDialog(null, "Seleccione una fila de la tabla","Selección",JOptionPane.WARNING_MESSAGE);
               }
               
            }
        });
        
        popupMenu.add(modificarItem);
        
        JMenuItem enviarMail= new JMenuItem("Enviar mail");
        enviarMail.addActionListener(
        new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
               {
                Component c = (Component)e.getSource();
                JPopupMenu popup = (JPopupMenu)c.getParent();
                JTable table = (JTable)popup.getInvoker();
                
                
                System.out.println(table.getSelectedRow() + " : " + table.getSelectedColumn());
                
                String correoReceptor=table.getModel().getValueAt(table.getSelectedRow(), 8).toString();
                String v=table.getModel().getValueAt(table.getSelectedRow(), 0).toString();
                
                   System.out.println("correo: "+correoReceptor);
                   System.out.println("id: "+v);
                   
                   
                    Hilos hilo= new Hilos(correoReceptor,ag.obtenerCorreoUsuario(identificador));
                    hilo.currentThread();
                    hilo.start();
                    System.out.println("estado hilo: "+hilo.getState());
                   
               }catch(Exception ex)
               {
                   JOptionPane.showMessageDialog(null, "Seleccione una fila de la tabla","Selección",JOptionPane.WARNING_MESSAGE);
                   System.out.println("error: "+ex.getMessage());
               }
            }
        });
        popupMenu.add(enviarMail);
        
        tablaDatos.setComponentPopupMenu(popupMenu);//añado el popup a la tabla
        //fin menu boton derecho
        
        try
        {
            tablaDatos.setModel(this.cargaDatos(idUsuario));
        }catch(Exception ex)
        {
            System.out.println("error: "+ex.getMessage());
        }
        
        JScrollPane scrollTabla= new JScrollPane(tablaDatos);
        scrollTabla.setBounds(10, 10, 620, 300);
        
        ventanaAgendaUsuario= new JFrame("Ventana del usuario");
        ventanaAgendaUsuario.setLayout(null);
        ventanaAgendaUsuario.setSize(640, 480);
        ventanaAgendaUsuario.setLocationRelativeTo(null);
        ventanaAgendaUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaAgendaUsuario.setResizable(false);
        
        btnSalir= new JButton("Salir");
        btnSalir.setBounds(10, 400, 80, 20);
        btnSalir.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent ae)
                    {
                        
                        if(JOptionPane.showConfirmDialog(null, "¿Realmente desea salir?","Salir",JOptionPane.YES_NO_OPTION)<1)
                        {
                            System.exit(0);
                        }
                    }
                });
        ventanaAgendaUsuario.add(btnSalir);

        btnNuevoContacto= new JButton("Agregar contacto");
        btnNuevoContacto.setBounds(110, 400, 180, 20);
        btnNuevoContacto.addActionListener(
        new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                ventanaCrearContacto(identificador);
            }
        });
        ventanaAgendaUsuario.add(btnNuevoContacto);
        
        btnBuscar=new JButton("Buscar...");
        btnBuscar.setBounds(300, 400, 100, 20);
        btnBuscar.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent ae)
                    {
                        String campoAcomparar=camposBusqueda.getSelectedItem().toString();
                        String textoAcomparar=txtTextoBuscar.getText();
                        
                        String condicion="where `idusuario`='"+identificador+"' and `"+campoAcomparar+"` LIKE '%"+textoAcomparar+"%'";
                        
                       tablaDatos.setModel(cargaDatos(condicion));
                       btnRecargar.setVisible(true);
                       btnRecargar.setEnabled(true);
                    }
                }
            );
        btnBuscar.setEnabled(false);
        ventanaAgendaUsuario.add(btnBuscar);
        
        camposBusqueda.addItem("nombre");
        camposBusqueda.addItem("apellidos");
        camposBusqueda.addItem("direccion");
        camposBusqueda.addItem("poblacion");
        camposBusqueda.addItem("provincia");
        camposBusqueda.addItem("nacinalidad");
        camposBusqueda.addItem("email");
        camposBusqueda.setSelectedIndex(0);
        camposBusqueda.setBounds(530, 400, 80, 20);
        ventanaAgendaUsuario.add(camposBusqueda);
        
        //txtTextoBuscar= new JTextField();
        txtTextoBuscar.setBounds(410, 400, 110, 20);
        txtTextoBuscar.addKeyListener(
                new KeyListener()
                {
                    @Override
                    public void keyTyped(KeyEvent e)
                    {
                        
                    }

                @Override
                public void keyPressed(KeyEvent ke)
                {
                    
                }

                @Override
                public void keyReleased(KeyEvent ke)
                {
                    if(txtTextoBuscar.getText().length()>0)
                        {
                            btnBuscar.setEnabled(true);
                        }else
                        {
                            btnBuscar.setEnabled(false);
                        }
                }
                });
        
        ventanaAgendaUsuario.add(txtTextoBuscar);
        
        
        btnRecargar.setBounds(400, 330, 100, 20);
        btnRecargar.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent ae)
                    {
                        btnRecargar.setVisible(false);
                        btnRecargar.setEnabled(false);
                        
                        txtTextoBuscar.setText(null);
                        
                        String condicion="where `idusuario`='"+identificador+"'";
                        
                       tablaDatos.setModel(cargaDatos(condicion));
                    }
                }
            );
        
        ventanaAgendaUsuario.add(btnRecargar);
        
        ventanaAgendaUsuario.add(scrollTabla);
        ventanaAgendaUsuario.setVisible(true);
       
    }
    
    /**
     * pasado un id, nos devuelve un tablemodel con el resultado
     * de la consulta
     * 
     * @param id
     * @return 
     */
    public DefaultTableModel cargaDatos(String id)
    {
        String[] titulos = {"idusuario", "nombre", "apellidos", "direccion", "poblacion", "provincia", "nacionalidad", "telefono", "email"};
        String registro[]=new String[9];
        String campos[]={"*"};
        String sql="Select * from datos;";
        String condicion="where `idusuario`='"+id+"'";
     
        if(id.length()>5)
        {
            condicion=id;
        }else
        {
            condicion="where `idusuario`='"+id+"'";
        }
        
        DefaultTableModel dfm= new DefaultTableModel(null,titulos);
        
     try{
            ResultSet rs= bd.consulta("select", "contactos", campos, null,condicion , null);
            
            rs.first();
            
                registro[0]=rs.getString("idcontacto");
                registro[1]=rs.getString("nombre");
                registro[2]=rs.getString("apellidos");
                registro[3]=rs.getString("direccion");
                registro[4]=rs.getString("poblacion");
                registro[5]=rs.getString("provincia");
                registro[6]=rs.getString("nacionalidad");
                registro[7]=rs.getString("telefono");
                registro[8]=rs.getString("email");
                
                dfm.addRow(registro);
                
            while(rs.next())
            {
                registro[0]=rs.getString("idcontacto");
                registro[1]=rs.getString("nombre");
                registro[2]=rs.getString("apellidos");
                registro[3]=rs.getString("direccion");
                registro[4]=rs.getString("poblacion");
                registro[5]=rs.getString("provincia");
                registro[6]=rs.getString("nacionalidad");
                registro[7]=rs.getString("telefono");
                registro[8]=rs.getString("email");
                
                dfm.addRow(registro);
            }
            
            
        } catch (SQLException ex)
        {
            System.out.println("codigo error: "+ex.getErrorCode());
            if(ex.getErrorCode()==0)
            {
                JOptionPane.showMessageDialog(null, "Error!! no hay contactos que mostrar", "Error!", JOptionPane.ERROR_MESSAGE);
            }else
            {
                JOptionPane.showMessageDialog(null, "Error!!"+ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
            }
            
            return null;
        }
     
        return dfm;
    }
    
    /**
     * Muestra la ventana crear contacto. Pasándole un id lo asigna al usuario
     * @param idUsuario 
     */
     public void ventanaCrearContacto(final String idUsuario)
    {
        final JFrame marcoCrearContacto;
        
        final JButton btnCrearContacto,
                btnLimpiar,
                btnCancelar;
        
        btnCrearContacto=new JButton("Guardar");
        btnCrearContacto.setEnabled(false);
        
        final JTextField
                txtNombreUsuario,
                txtApellido,
                txtDireccion,
                txtPoblacion,
                txtProvincia,
                txtNacionalidad,
                txtTelefono,
                txtEmail;
        JLabel 
                lblNombreUsuario,
                lblApellido,
                lblDireccion,
                lblPoblacion,
                lblProvincia,
                lblNacionalidad,
                lblTelefono,
                lblEmail;
        
        marcoCrearContacto= new JFrame("Crear contacto");
        marcoCrearContacto.setLayout(null);
        marcoCrearContacto.setSize(440,230);
        marcoCrearContacto.setLocationRelativeTo(null);
        marcoCrearContacto.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        marcoCrearContacto.setResizable(false);
        
        lblNombreUsuario= new JLabel("Nombre contacto: ");
        lblNombreUsuario.setBounds(10, 10, 145, 20);
        marcoCrearContacto.add(lblNombreUsuario);
        
        txtNombreUsuario= new JTextField();
        txtNombreUsuario.setBounds(140,10, 85, 20);
        txtNombreUsuario.transferFocus();
        marcoCrearContacto.add(txtNombreUsuario);
        
        lblApellido= new JLabel("Apellidos: ");
        lblApellido.setBounds(250, 10, 90, 20);
        marcoCrearContacto.add(lblApellido);
        
        txtApellido= new JTextField();
        txtApellido.setBounds(325, 10, 90, 20);
        txtApellido.requestFocus();
        marcoCrearContacto.add(txtApellido);
        
        lblDireccion= new JLabel("Dirección: ");
        lblDireccion.setBounds(10, 40, 75, 20);
        marcoCrearContacto.add(lblDireccion);
        
        txtDireccion= new JTextField();
        txtDireccion.setBounds(85, 40, 300, 20);
        marcoCrearContacto.add(txtDireccion);
        
        lblPoblacion= new JLabel("Población: ");
        lblPoblacion.setBounds(10, 70, 90, 20);
        marcoCrearContacto.add(lblPoblacion);
        
        txtPoblacion= new JTextField();
        txtPoblacion.setBounds(90, 70, 120, 20);
        marcoCrearContacto.add(txtPoblacion);
        
        lblProvincia= new JLabel("Provincia: ");
        lblProvincia.setBounds(220, 70, 80, 20);
        marcoCrearContacto.add(lblProvincia);
        
        txtProvincia= new JTextField();
        txtProvincia.setBounds(290, 70, 95, 20);
        marcoCrearContacto.add(txtProvincia);
        
        lblNacionalidad= new JLabel("Nacionalidad: ");
        lblNacionalidad.setBounds(10, 100, 110, 20);
        marcoCrearContacto.add(lblNacionalidad);
        
        txtNacionalidad= new JTextField();
        txtNacionalidad.setBounds(110, 100, 90, 20);
        marcoCrearContacto.add(txtNacionalidad);
        
        lblEmail= new JLabel("Email: ");
        lblEmail.setBounds(205, 100, 90, 20);
        marcoCrearContacto.add(lblEmail);
        
        txtEmail= new JTextField();
        txtEmail.setBounds(250, 100, 160, 20);
        marcoCrearContacto.add(txtEmail);
        
        lblTelefono= new JLabel("Telefono: ");
        lblTelefono.setBounds(10, 130, 90, 20);
        marcoCrearContacto.add(lblTelefono);
        
        txtTelefono= new JTextField();
        txtTelefono.setBounds(90, 130, 90, 20);
        txtTelefono.addKeyListener(
                new KeyListener()
                {
                    @Override
                    public void keyTyped(KeyEvent e)
                    {
                        
                    }

                @Override
                public void keyPressed(KeyEvent ke)
                {
                    
                }

                @Override
                public void keyReleased(KeyEvent ke)
                {
                    if(
                        (txtApellido.getText().length()>0)&&
                        (txtDireccion.getText().length()>0)&&
                        (txtEmail.getText().length()>0)&&
                        (txtNacionalidad.getText().length()>0)&&
                        (txtNombreUsuario.getText().length()>0)&&
                        (txtPoblacion.getText().length()>0)&&
                        (txtProvincia.getText().length()>0)&&  
                        (txtTelefono.getText().length()>0)
                      )
                        {
                            btnCrearContacto.setEnabled(true);
                        }else
                        {
                            btnCrearContacto.setEnabled(false);
                        }
                    try
                    {
                        Integer.parseInt(txtTelefono.getText());
                        btnCrearContacto.setEnabled(true);
                    }catch(Exception ex)
                    {
                        JOptionPane.showMessageDialog(null, "Número no válido","Error",JOptionPane.ERROR_MESSAGE);
                        btnCrearContacto.setEnabled(false);
                        txtTelefono.setText(null);
                        txtTelefono.requestFocus();
                    }
                }
                });
        
        marcoCrearContacto.add(txtTelefono);
        
        //botones
        btnCancelar= new JButton("Cancelar");
        btnCancelar.setBounds(10, 170, 100, 20);
        btnCancelar.addActionListener(
        new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                System.out.println("Salimos de la ventana crear contacto");
                marcoCrearContacto.dispose();
            }
        }
        );
        marcoCrearContacto.add(btnCancelar);
        
        
        btnLimpiar=new JButton("Limpiar");
        btnLimpiar.setBounds(150, 170, 100, 20);
        btnLimpiar.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent ae)
                    {
                            System.out.println("bonton Limpiar ventana crear contacto");
                            txtNombreUsuario.setText(null);
                            txtApellido.setText(null);
                            txtDireccion.setText(null);
                            txtEmail.setText(null);
                            txtNacionalidad.setText(null);
                            txtPoblacion.setText(null);
                            txtProvincia.setText(null);
                            txtTelefono.setText(null);
                            txtNombreUsuario.requestFocus();
                            btnCrearContacto.setEnabled(false);
                    }
                }
        );
        marcoCrearContacto.add(btnLimpiar);
        
        
        btnCrearContacto.setBounds(300, 170, 100, 20);
        btnCrearContacto.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent ae)
                    {
                        System.out.println("guardar usuario");
                        
                        //creamos un objeto usuario
                        Personas contacto= new Contactos
                        (   idUsuario,
                            txtNombreUsuario.getText(),
                            txtApellido.getText(),
                            txtDireccion.getText(),
                            txtPoblacion.getText(),
                            txtProvincia.getText(),
                            txtNacionalidad.getText(),
                            txtEmail.getText(),
                            Integer.parseInt(txtTelefono.getText())
                        );
                        
                        //lo pasamos a agenda para procesar los datos/el objeto
                      if (ag.crearContacto((Personas)contacto,idUsuario))
                      {
                          JOptionPane.showMessageDialog(null, "Contacto guardado correctamente", "Contacto Guardado", JOptionPane.INFORMATION_MESSAGE);
                          tablaDatos.setModel(cargaDatos(identificador));//referescamos datos en la tabla
                            
                            txtNombreUsuario.setText(null);//limpiamos los campos de texto
                            txtApellido.setText(null);
                            txtDireccion.setText(null);
                            txtEmail.setText(null);
                            txtNacionalidad.setText(null);
                            txtPoblacion.setText(null);
                            txtProvincia.setText(null);
                            txtTelefono.setText(null);
                            txtNombreUsuario.requestFocus();
                            
                          marcoCrearContacto.dispose();//cerramos esta ventana
                          
                      }else
                      {
                          JOptionPane.showMessageDialog(null, "Error al guardad el contacto", "Error", JOptionPane.ERROR_MESSAGE);
                      }
 
                    }
                });
        marcoCrearContacto.add(btnCrearContacto);
        
        //marcoCrearContacto.setAlwaysOnTop(true);
        marcoCrearContacto.setVisible(true);
    }
   
     /**
      * Recibe el id del contacto, rellena sus campos y actualiza 
      * @param idContacto 
      */
  public void ventanaModificarContacto(final String idContacto)
    {
        String campos[]={"*"};
        ResultSet rs=bd.consulta("select", "contactos", campos, null, "where `idcontacto`='"+idContacto+"'", null);
        
        final JFrame marcoModificarContacto;
        
        final JButton btnModificarContacto,
                btnCancelar;
        
        btnModificarContacto=new JButton("Guardar");
        
        final JTextField
                txtNombreUsuario,
                txtApellido,
                txtDireccion,
                txtPoblacion,
                txtProvincia,
                txtNacionalidad,
                txtTelefono,
                txtEmail;
        
        txtApellido= new JTextField();
        txtNombreUsuario= new JTextField();
        txtDireccion= new JTextField();
        txtPoblacion= new JTextField();
        txtProvincia= new JTextField();
        txtNacionalidad= new JTextField();
        txtEmail= new JTextField();
        txtTelefono= new JTextField();
        
        
        JLabel 
                lblNombreUsuario,
                lblApellido,
                lblDireccion,
                lblPoblacion,
                lblProvincia,
                lblNacionalidad,
                lblTelefono,
                lblEmail;
        
        marcoModificarContacto= new JFrame("Modificar contacto");
        marcoModificarContacto.setLayout(null);
        marcoModificarContacto.setSize(440,220);
        marcoModificarContacto.setLocationRelativeTo(null);
        marcoModificarContacto.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        marcoModificarContacto.setResizable(false);
       
    try
      { 
        lblNombreUsuario= new JLabel("Nombre contacto: ");
        lblNombreUsuario.setBounds(10, 10, 145, 20);
        marcoModificarContacto.add(lblNombreUsuario);
        
        
        txtNombreUsuario.setBounds(140,10, 85, 20);
        txtNombreUsuario.transferFocus();
        txtNombreUsuario.setText(rs.getString(3).toString());
        marcoModificarContacto.add(txtNombreUsuario);
        
        lblApellido= new JLabel("Apellidos: ");
        lblApellido.setBounds(250, 10, 90, 20);
        marcoModificarContacto.add(lblApellido);
        
        
        txtApellido.setBounds(325, 10, 90, 20);
        txtApellido.requestFocus();
        txtApellido.setText(rs.getString(4).toString());
        marcoModificarContacto.add(txtApellido);
        
        lblDireccion= new JLabel("Dirección: ");
        lblDireccion.setBounds(10, 40, 75, 20);
        marcoModificarContacto.add(lblDireccion);
        
        txtDireccion.setBounds(85, 40, 300, 20);
        txtDireccion.setText(rs.getString(5).toString());
        marcoModificarContacto.add(txtDireccion);
        
        lblPoblacion= new JLabel("Población: ");
        lblPoblacion.setBounds(10, 80, 90, 20);
        marcoModificarContacto.add(lblPoblacion);
        
        txtPoblacion.setBounds(90, 80, 120, 20);
        txtPoblacion.setText(rs.getString(6).toString());
        marcoModificarContacto.add(txtPoblacion);
        
        lblProvincia= new JLabel("Provincia: ");
        lblProvincia.setBounds(220, 80, 80, 20);
        marcoModificarContacto.add(lblProvincia);
        
        
        txtProvincia.setBounds(290, 80, 95, 20);
        txtProvincia.setText(rs.getString(7).toString());
        marcoModificarContacto.add(txtProvincia);
        
        lblNacionalidad= new JLabel("Nacionalidad: ");
        lblNacionalidad.setBounds(10, 110, 110, 20);
        marcoModificarContacto.add(lblNacionalidad);
        
        
        txtNacionalidad.setBounds(110, 110, 90, 20);
        txtNacionalidad.setText(rs.getString(8).toString());
        marcoModificarContacto.add(txtNacionalidad);
        
        lblEmail= new JLabel("Email: ");
        lblEmail.setBounds(205, 110, 90, 20);
        marcoModificarContacto.add(lblEmail);
        
        
        txtEmail.setBounds(250, 110, 160, 20);
        txtEmail.setText(rs.getString(10).toString());
        marcoModificarContacto.add(txtEmail);
        
        lblTelefono= new JLabel("Telefono: ");
        lblTelefono.setBounds(10, 140, 90, 20);
        marcoModificarContacto.add(lblTelefono);
        
        
        txtTelefono.setBounds(90, 140, 90, 20);
        txtTelefono.setText(rs.getString(9).toString());
        txtTelefono.addKeyListener(
                new KeyListener()
                {
                    @Override
                    public void keyTyped(KeyEvent e)
                    {
                        
                    }

                @Override
                public void keyPressed(KeyEvent ke)
                {
                    
                }

                @Override
                public void keyReleased(KeyEvent ke)
                {
                    if(
                        (txtApellido.getText().length()>0)&&
                        (txtDireccion.getText().length()>0)&&
                        (txtEmail.getText().length()>0)&&
                        (txtNacionalidad.getText().length()>0)&&
                        (txtNombreUsuario.getText().length()>0)&&
                        (txtPoblacion.getText().length()>0)&&
                        (txtProvincia.getText().length()>0)&&  
                        (txtTelefono.getText().length()>0)
                      )
                        {
                            btnModificarContacto.setEnabled(true);
                        }else
                        {
                            btnModificarContacto.setEnabled(false);
                        }
                    try
                    {
                        Integer.parseInt(txtTelefono.getText());
                        btnModificarContacto.setEnabled(true);
                    }catch(Exception ex)
                    {
                        JOptionPane.showMessageDialog(null, "Número no válido","Error",JOptionPane.ERROR_MESSAGE);
                        btnModificarContacto.setEnabled(false);
                        txtTelefono.setText(null);
                        txtTelefono.requestFocus();
                    }
                }
                });
        marcoModificarContacto.add(txtTelefono);
        
        //botones
        btnCancelar= new JButton("Cancelar");
        btnCancelar.setBounds(10, 170, 100, 20);
        btnCancelar.addActionListener(
        new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                System.out.println("Salimos de la ventana modificar contacto");
                marcoModificarContacto.dispose();
            }
        }
        );
        marcoModificarContacto.add(btnCancelar);

        
        btnModificarContacto.setBounds(300, 170, 100, 20);
        btnModificarContacto.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent ae)
                    {
                        System.out.println("guardar usuario");
                        
                        if(
                        (txtApellido.getText().length()>0)&&
                        (txtDireccion.getText().length()>0)&&
                        (txtEmail.getText().length()>0)&&
                        (txtNacionalidad.getText().length()>0)&&
                        (txtNombreUsuario.getText().length()>0)&&
                        (txtPoblacion.getText().length()>0)&&
                        (txtProvincia.getText().length()>0)&&  
                        (txtTelefono.getText().length()>0)
                      )
                        {
                            //creamos un objeto usuario
                            Personas contacto= new Contactos
                            (   idContacto,
                                txtNombreUsuario.getText(),
                                txtApellido.getText(),
                                txtDireccion.getText(),
                                txtPoblacion.getText(),
                                txtProvincia.getText(),
                                txtNacionalidad.getText(),
                                txtEmail.getText(),
                                Integer.parseInt(txtTelefono.getText())
                            );

                            //lo pasamos a agenda para procesar los datos/el objeto
                          if (ag.modificarContacto((Personas)contacto,idContacto))
                          {
                              JOptionPane.showMessageDialog(null, "Contacto actualizado correctamente", "Contacto actualizado", JOptionPane.INFORMATION_MESSAGE);
                              tablaDatos.setModel(cargaDatos(identificador));//referescamos datos en la tabla

                                txtNombreUsuario.setText(null);//limpiamos los campos de texto
                                txtApellido.setText(null);
                                txtDireccion.setText(null);
                                txtEmail.setText(null);
                                txtNacionalidad.setText(null);
                                txtPoblacion.setText(null);
                                txtProvincia.setText(null);
                                txtTelefono.setText(null);
                                txtNombreUsuario.requestFocus();

                              marcoModificarContacto.dispose();//cerramos esta ventana

                          }else
                          {
                              JOptionPane.showMessageDialog(null, "Error al guardad el contacto", "Error", JOptionPane.ERROR_MESSAGE);
                          }
                        }else
                        {
                            JOptionPane.showMessageDialog(null, "Error hay campos vacíos", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        
 
                    }
                });
        marcoModificarContacto.add(btnModificarContacto);
        
      }catch(Exception ex)
      {
          System.out.println("error: "+ex.getMessage());
      }
    
        //marcoModificarContacto.setAlwaysOnTop(true);
        marcoModificarContacto.setVisible(true);
    }

}
