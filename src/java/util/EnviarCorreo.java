package util;

import java.util.Properties; 
import javax.mail.Session; 
import javax.mail.Message; 
import javax.mail.Transport; 
import javax.mail.Authenticator; 
import javax.mail.MessagingException; 
import javax.mail.internet.InternetAddress;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.MimeMessage; 
import javax.swing.JOptionPane;

/**
 *
 * @author Kevin Castillo
 */
public class EnviarCorreo {
  
  /**
   * Metodo para enviar correo
   * @param to hacia quien va el correo
   * @param sub asunto del correo
   * @param msg mensaje del correo
   */
  public static void enviarCorreo(String to, String sub,String msg) {
    final String user = "rentacarkpr@gmail.com";
    final String pass = "prograbases1";
    Properties props = new Properties();     
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");	
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");  
    Session session = Session.getDefaultInstance(props,new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(user, pass); 
      }
    });   
    try {
      Message message = new MimeMessage(session);    
      message.setFrom(new InternetAddress(user));
      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
      message.setSubject(sub);
      message.setText(msg);
      Transport.send(message);
    } catch (MessagingException e) {
      JOptionPane.showMessageDialog(null,"Error!");
      throw new RuntimeException(e);
    }
  }
}