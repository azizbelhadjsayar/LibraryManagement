/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import BARCODE.Barcode_Image;
import Entities.Account;
import Entities.BookItem;
import Interfaces.BookLendingDAOInterface;
import MailSender.mailing;
import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author LENOVO
 */
public class BookLendingService implements BookLendingDAOInterface{

    private String getDate() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return currentDateTime.format(formatter);
    }
    
    @Override
    public Boolean addBookLending(int Days, Account a, BookItem bi) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            Connection connection = BibliothequeDAO.getConnection();
            LocalDateTime creationDate = LocalDateTime.now();
            LocalDateTime dueDate = creationDate.plusDays(Days);
            String INSERT_BOOKITEM_SQL = "INSERT INTO booklending (bookitem_barcode, account_id, creation_date, due_date) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(INSERT_BOOKITEM_SQL);
            statement.setString(1, bi.getBarcode());
            statement.setInt(2, a.getId());
            statement.setObject(3, creationDate);
            statement.setObject(4, dueDate);
            int affectedRows = statement.executeUpdate();
            if (affectedRows==1) {
                BookItemService bis = new BookItemService();
                BookService bs = new BookService();
                bis.setUnavailableBookItem(bi.getBarcode());
                Barcode_Image.createImage(bi.getBarcode()+a.getId()+".png", bi.getBarcode()+a.getId(), "bookLendingsBarcodes");
                sleep(1000);
                mailing.NewLendingEmailSender(a, bs.getBookbyISBN(bi.getBookISBN()), creationDate.format(formatter), dueDate.format(formatter), bi.getBarcode()+a.getId()+".png");
                statement.close();
                return true;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
