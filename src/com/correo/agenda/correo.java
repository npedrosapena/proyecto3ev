
package com.correo.agenda;
  
import javax.swing.*;
import java.awt.*;
 
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
 
import java.util.Properties;
 
//Para manejo de JavaMail
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class correo extends JFrame implements Runnable {
    private String correoReceptor;
    private String correoRemitente;
    //Constructor
    public correo(String correoReceptor,String correoRemitente)
    {
        this.correoReceptor=correoReceptor;
        this.correoRemitente=correoRemitente;
    }

 
    //Para el cuadro de dialogo de Opciones
    JPanel pnlServidor;
    JPanel pnlBotones;
    JPanel pnlRemitente;
    JPanel pnlPasswordRemitente;
    JPanel pnlDestino;
    JPanel pnlAsunto;
    JPanel pnlMensaje;
     
    JLabel lblServidor;     JTextField jtfServidor;
     
    JLabel lblRemitente;    JTextField jtfRemitente;    JPasswordField jpfPasswordRemitente;
    JLabel lblDestino;  JTextField jtfDestino;
    JLabel lblAsunto;   JTextField jtfAsunto;
    JLabel lblMensaje;  JTextArea jtaMensaje;
     
    JButton btnEnviar;
    JButton btnCancelar;
     
    public correo(String strTitulo, int i_ancho, int i_alto ,String correoReceptor,String correoRemitente) {
        this.correoReceptor=correoReceptor;
        this.correoRemitente=correoRemitente;
        this.setTitle(strTitulo);
    this.setSize(i_ancho,i_alto);   
    this.setLocationRelativeTo(null);
 
        this.setResizable(false);
         
        Box boxPrincipal = Box.createVerticalBox();
        crearCamposVentana();
        crearBotones();
        boxPrincipal.add(Box.createVerticalStrut(15));
        boxPrincipal.add(pnlServidor);
        boxPrincipal.add(Box.createVerticalStrut(15));
        boxPrincipal.add(pnlRemitente);
        boxPrincipal.add(Box.createVerticalStrut(15));
        boxPrincipal.add(pnlDestino);
        boxPrincipal.add(Box.createVerticalStrut(15));
        boxPrincipal.add(pnlAsunto);
        boxPrincipal.add(Box.createVerticalStrut(15));
        boxPrincipal.add(pnlMensaje);
        boxPrincipal.add(Box.createVerticalStrut(15));
        boxPrincipal.add(pnlBotones);
         
        add(boxPrincipal);
         
        btnCancelar.addActionListener( new ActionListener() {          
                public void actionPerformed(ActionEvent evt) {
                    dispose();
                }
            }
        );
         
        btnEnviar.addActionListener( new ActionListener() {        
                public void actionPerformed(ActionEvent e) {
                    if(enviarEmail() ) {
                        JOptionPane.showMessageDialog(null,"Mensaje enviado!");
                        jtaMensaje.requestFocus(true);
                    } else {
                        JOptionPane.showMessageDialog(null,"Por el momento NO SE PUDO ENVIAR el mensaje.");
                    }
                    
                     
                }          
            }          
        );
         
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
    }
     
    private void crearCamposVentana() {
        Box boxServidor =  Box.createHorizontalBox();
        lblServidor = new JLabel("Servidor");
        jtfServidor = new JTextField("smtp.gmail.com",15);     
        boxServidor.add(Box.createHorizontalStrut(15));
        boxServidor.add(lblServidor);
        boxServidor.add(Box.createHorizontalStrut(20));
        boxServidor.add(jtfServidor);
        pnlServidor = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlServidor.add(boxServidor);
         
        //Remitente
        Box boxRemitente =  Box.createHorizontalBox();
        lblRemitente = new JLabel("<html><font color='#0000FF'>Remitente</font></html>");
        jtfRemitente = new JTextField(correoRemitente,15);
        jpfPasswordRemitente =  new JPasswordField(15);
        jpfPasswordRemitente.setToolTipText("Escriba el password de este remitente");
        boxRemitente.add(Box.createHorizontalStrut(15));
        boxRemitente.add(lblRemitente);
        boxRemitente.add(Box.createHorizontalStrut(20));
        boxRemitente.add(jtfRemitente);
        boxRemitente.add(Box.createHorizontalStrut(5));
        boxRemitente.add(jpfPasswordRemitente);
        pnlRemitente = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlRemitente.add(boxRemitente);
     
        //Destino
        Box boxDestino =  Box.createHorizontalBox();
        lblDestino = new JLabel("<html><font color='#0000FF'>Destino</font></html>");
        jtfDestino = new JTextField(correoReceptor,15);
        jtfDestino.setToolTipText("Puede escribir varios destinatarios separados con coma");
        boxDestino.add(Box.createHorizontalStrut(15));
        boxDestino.add(lblDestino);
        boxDestino.add(Box.createHorizontalStrut(20));
        boxDestino.add(jtfDestino);
        pnlDestino = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlDestino.add(boxDestino);        
             
        //Asunto
        Box boxAsunto =  Box.createHorizontalBox();
        lblAsunto = new JLabel("<html><font color='#0000FF'>Asunto</font></html>");
        jtfAsunto = new JTextField("asunto",15);    
        boxAsunto.add(Box.createHorizontalStrut(15));
        boxAsunto.add(lblAsunto);
        boxAsunto.add(Box.createHorizontalStrut(20));
        boxAsunto.add(jtfAsunto);
        pnlAsunto = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlAsunto.add(boxAsunto);      
             
        //Mensaje
        Box boxMensaje =  Box.createHorizontalBox();
        lblMensaje = new JLabel("<html><font color='#0000FF'>Mensaje</font></html>");
        jtaMensaje = new JTextArea("",5,30);
        jtaMensaje.setLineWrap(true);
        JScrollPane jspScrollMsg = new JScrollPane(jtaMensaje);
 
        jtaMensaje.setAutoscrolls(true);
        boxMensaje.add(Box.createHorizontalStrut(15));
        boxMensaje.add(lblMensaje);
        boxMensaje.add(Box.createHorizontalStrut(20));
        boxMensaje.add(jspScrollMsg);
        pnlMensaje = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlMensaje.add(boxMensaje);
    }
     
    private void crearBotones() {
        btnEnviar = new JButton("Enviar email");
        btnCancelar = new JButton("Cancelar");
        Box boxBotones = Box.createHorizontalBox();
        boxBotones.add(btnEnviar);
        boxBotones.add(Box.createHorizontalStrut(25));
        boxBotones.add(btnCancelar);
        boxBotones.add(Box.createHorizontalStrut(25));
        pnlBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pnlBotones.add(boxBotones);
                 
    }
     
    public void mostrar() {
        setVisible(true);
       
    }
     
    //Devuelve true en caso de que se envie el email de manera correcta, o
    //devuelve false si no se pudo enviar el email
    public boolean enviarEmail() {
    try
    {
        // Propiedades de la conexión
        Properties props = new Properties();
        props.setProperty("mail.smtp.host",  "smtp.gmail.com");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.auth", "true");
 
        // Preparamos la sesion
        Session session = Session.getDefaultInstance(props);
                 
    //Recoger los datos
    String str_De       = jtfRemitente.getText();
    String str_PwRemitente      = jpfPasswordRemitente.getText();              
    String str_Para     = jtfDestino.getText();
    String str_Asunto = jtfAsunto.getText();
    String str_Mensaje = jtaMensaje.getText();
    //Obtenemos los destinatarios
    String destinos[] = str_Para.split(",");
                 
        // Construimos el mensaje
        MimeMessage message = new MimeMessage(session);
         
        message.setFrom(new InternetAddress( str_De ));
 
        Address [] receptores = new Address [ destinos.length ];
        int j = 0;
    while(j<destinos.length){                   
        receptores[j] = new InternetAddress ( destinos[j] ) ;                  
        j++;               
    }
 
         
        //receptores.
        message.addRecipients(Message.RecipientType.TO, receptores);       
        message.setSubject( str_Asunto );       
        message.setText( str_Mensaje );
             
        // Lo enviamos.
        Transport t = session.getTransport("smtp");
        t.connect(str_De, str_PwRemitente);
        t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
                 
        // Cierre de la conexion.
        t.close();
        return true;
    }
    catch (Exception e)
    {
        e.printStackTrace();
        return false;
    }      
  }

    @Override
    public void run()
    {
        this.enviarEmail();
    }
    
}
