package MailSender;
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
        	message.setSubject("Welcome to our Library System - Your New Account Information");
        else if (type.toLowerCase().equals("newlending"))
        	message.setSubject("NEW BOOK LENDING");


        // Create a multipart message
        Multipart multipart = new MimeMultipart();

        // Text part
        BodyPart textPart = new MimeBodyPart();
        textPart.setText("Congratulations! Your account has been successfully created in our library system at \"BIBLIOTHEQUE TUNISIE\".\n\n"
                        + "Thank you for joining our library community. With your new account, you can now enjoy the benefits of our extensive collection of books and resources.\n"
                        + "Attached to this email, you will find an image of your unique library barcode. Please make sure to keep it safe, as you may need it for library transactions.\n"
                        + "If you have any questions or need assistance, feel free to reach out to our support team.\n"
                        + "Happy reading!\n\n"
                        + "Best regards,\n"
                        + "BIBLIOTHEQUE TUNISIE");
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
