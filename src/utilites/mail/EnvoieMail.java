/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilites.mail;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;


/**
 *
 * @author daniel
 */
public class EnvoieMail {
                String SMTP_HOST_NAME = "smtp.gmail.com";  
                String SMTP_PORT = "465";   
                  
                String SMTP_FROM_ADDRESS="jllad2010@gmail.com";  
                String SMTP_TO_ADDRESS="ladacoqui@yahoo.fr";  
                String subject="Textmsg";  
                String fileAttachment = "D:\\monetat.xls";  
              
                Properties props = new Properties();  
      
          public void sendEmail(){
                  props.put("mail.smtp.host", SMTP_HOST_NAME);  
                  props.put("mail.smtp.auth", "true");  
                  props.put("mail.debug", "true");  
                  props.put("mail.smtp.port", SMTP_PORT ); 
                  
                  Session session = Session.getInstance(props,new javax.mail.Authenticator()  
                    {protected javax.mail.PasswordAuthentication   
                        getPasswordAuthentication()   
                    {return new javax.mail.PasswordAuthentication("jllad2010@gmail.com","loveline");}});  
                  try{  
                  
                  Message msg = new MimeMessage(session);  
                
                  msg.setFrom(new InternetAddress(SMTP_FROM_ADDRESS));  
               // create the message part   
                  MimeBodyPart messageBodyPart =   
                    new MimeBodyPart();  
              //fill message  
                  messageBodyPart.setText("Test mail one");  
                  Multipart multipart = new MimeMultipart();  
                  multipart.addBodyPart(messageBodyPart);  
               // Part two is attachment  
                  messageBodyPart = new MimeBodyPart();  
                  DataSource source =   
                    new FileDataSource(fileAttachment);  
                  messageBodyPart.setDataHandler(  
                    new DataHandler(source));  
                  messageBodyPart.setFileName(fileAttachment);  
                  multipart.addBodyPart(messageBodyPart);  
              // Put parts in message  
                  msg.setContent(multipart);  
               
      
                  msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(SMTP_TO_ADDRESS));  
      
                  msg.setSubject(subject);  
                 // msg.setContent(content, "text/plain");  
      
                  Transport.send(msg);  
                  System.out.println("success....................................");  
                  }  
                  catch(Exception e){  
                      e.printStackTrace();        
                  }  
        }
          
        public static void main(String[] args) {
            new EnvoieMail().sendEmail();
        }
}
