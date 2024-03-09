package mailtest;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class mailing {
public static void EmailSender(String type, String receiver, String imagePath) {
		
	final String username = "bibliotunisie@gmail.com";
    final String password = "pglc sebl geux kewn";

    Properties props = new Properties();
    props.put("mail.smtp.user",username); 
    props.put("mail.smtp.host", "smtp.gmail.com"); 
    props.put("mail.smtp.port", "25"); 
    props.put("mail.debug", "true"); 
    props.put("mail.smtp.auth", "true"); 
    props.put("mail.smtp.starttls.enable","true"); 
    props.put("mail.smtp.EnableSSL.enable","true");
    props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
    props.setProperty("mail.smtp.socketFactory.fallback", "false");   
    props.setProperty("mail.smtp.port", "465");   
    props.setProperty("mail.smtp.socketFactory.port", "465"); 

    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    });

    try {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("bibliotunisie@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
        if(type.toLowerCase().equals("newaccount"))
        	message.setSubject("ACCOUNT CREATION BIBLIOTHEQUE TUNISIE");
        else if (type.toLowerCase().equals("newlending"))
        	message.setSubject("NEW BOOK LENDING");


        // Create a multipart message
        Multipart multipart = new MimeMultipart();

        // Text part
        BodyPart textPart = new MimeBodyPart();
        textPart.setText("This is the message body");
        multipart.addBodyPart(textPart);

        MimeBodyPart imagePart = new MimeBodyPart();
        String filePath = imagePath;
        FileDataSource fileDataSource = new FileDataSource(filePath);
        imagePart.setDataHandler(new DataHandler(fileDataSource));
        imagePart.setFileName("barcode.jpg");
        imagePart.setHeader("Content-ID", "<image>");
        multipart.addBodyPart(imagePart);

        // Set the content of the message
        message.setContent(multipart);

        // Send the message
        Transport.send(message);

        System.out.println("Email sent successfully!");

    } catch (Exception e) {
        e.printStackTrace();
    }
	}
	
}
