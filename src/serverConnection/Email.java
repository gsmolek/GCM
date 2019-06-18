package serverConnection;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class Email {
	private Properties properties;
	private String host="smtp.gmail.com";
	private Session session;
	private String username="global.city.maps@gmail.com";
	private String password="Aa!@#$%^";
	private MimeMessage message;
	private String[] recipients;
	private String subject;
	private String body;
	
	public Email(String[] recipients,String subject, String body)
	{
		this.recipients=recipients;
		this.subject=subject;
		this.body=body;
		properties=System.getProperties();
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.user", username);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		
		 session = Session.getInstance(properties, new Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(username,
	                		password);
	            }
	            });
		message=new MimeMessage(session);
	}
	
	public void sendMail()
	{
        try {
            message.setFrom(new InternetAddress(username));
            InternetAddress[] toAddress = new InternetAddress[recipients.length];

            // To get the array of addresses
            for( int i = 0; i < recipients.length; i++ ) {
                toAddress[i] = new InternetAddress(recipients[i]);
            }

            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, username, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("==========================================================================================");
            System.out.println("Emails has been sent to all subscription about to end users ");
            System.out.println("==========================================================================================");
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
	}
}
