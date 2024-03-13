package MailSender;
import Entities.Account;
import Entities.Book;
import Services.AuthorService;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class mailing {

    public static void NewAccountEmailSender(String receiver, String imagefilename) {
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
            message.setSubject("Welcome to our Library System - Your New Account Information");


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
            String filePath = "src/libraryCardsBarcodes/"+imagefilename;
            FileDataSource fileDataSource = new FileDataSource(filePath);
            imagePart.setDataHandler(new DataHandler(fileDataSource));
            imagePart.setFileName("AccountBarcode.jpg");
            imagePart.setHeader("Content-ID", "<image>");
            multipart.addBodyPart(imagePart);

            // Set the content of the message
            message.setContent(multipart);

            // Send the message
            Transport.send(message);

            //System.out.println("Email sent successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
	}

    public static void NewLendingEmailSender(Account ac, Book b, String creationDate, String dueDate, String imagefilename) {
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
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(ac.getEmail()));
            message.setSubject("Book Lending Confirmation - Due Date and Book Information");


            // Create a multipart message
            Multipart multipart = new MimeMultipart();

            // Text part
            AuthorService as = new AuthorService();
            BodyPart textPart = new MimeBodyPart();
            textPart.setText("Dear "+ac.getUsername()+",\n\n"
                            + "We are pleased to inform you that you have successfully borrowed a book from our library on "+creationDate+".\n\n"
                            + "Book Details:\n"
                            + "Title : "+b.getTitle()+"\n"
                            + "Author : "+as.getAuthorbyID(b.getAuthorID()).getFullname()+"\n"
                            + "Due Date : "+ dueDate+"\n\n"
                            + "Please ensure that the book is returned by the specified due date to avoid any late fees. If you need to extend the borrowing period or have any concerns, don't hesitate to contact our library staff.\n\n"
                            + "Attached to this email, you will find an image of the barcode associated with this book lending. This barcode may be required for library transactions related to this book.\n\n"
                            + "Thank you for using the services of BIBLIOTHEQUE TUNISIE. Happy reading!\n\n"
                            + "Best regards,\n"
                            + "BIBLIOTHEQUE TUNISIE");

            multipart.addBodyPart(textPart);
            MimeBodyPart imagePart = new MimeBodyPart();
            String filePath = "src/bookLendingsBarcodes/"+imagefilename;
            FileDataSource fileDataSource = new FileDataSource(filePath);
            imagePart.setDataHandler(new DataHandler(fileDataSource));
            imagePart.setFileName("AccountBarcode.jpg");
            imagePart.setHeader("Content-ID", "<image>");
            multipart.addBodyPart(imagePart);

            // Set the content of the message
            message.setContent(multipart);

            // Send the message
            Transport.send(message);

            //System.out.println("Email sent successfully!");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public static void ReturnConfirmationEmailSender(Account ac, String title, String author, String returnDate, Double fees) {
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
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(ac.getEmail()));
            message.setSubject("Book Return Confirmation");
            Multipart multipart = new MimeMultipart();
            BodyPart textPart = new MimeBodyPart();
            textPart.setText("Dear "+ac.getUsername()+",\n\n"
                            + "We hope this message finds you well.\n\n"
                            + "Thank you for returning the book from our library. Below are the details of the returned book:Thank you for returning the book from our library. Below are the details of the returned book:\n\n"
                            + "Book Details:\n"
                            + "  - Title : "+title+"\n"
                            + "  - Author : "+author+"\n"
                            + "  - Return Date : "+ returnDate+"\n\n"
                            + "Late fee information :\n"
                            + "  - Fee ammount : "+fees+" TND\n\n"
                            + "If you have any questions or concerns regarding this return or the late fee details, please feel free to contact our library staff.\n\n"
                            + "Thank you for using the services of BIBLIOTHEQUE TUNISIE. We appreciate your prompt return of the book.\n\n"
                            + "Best regards,\n"
                            + "BIBLIOTHEQUE TUNISIE");
            multipart.addBodyPart(textPart);
            message.setContent(multipart);
            Transport.send(message);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
	
}
