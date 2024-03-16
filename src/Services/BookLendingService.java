/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Barcode.Barcode_Image;
import Entities.Account;
import Entities.BookItem;
import Entities.Booklending;
import Interfaces.BookLendingDAOInterface;
import MailSender.mailing;
import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
    
    
    
    
    public record Row(int id, String bookitem_barcode, int account_id, Object creation_date, Object due_date , Object return_date){
        
    }
    
    
    public ArrayList<Row> getAllBookLendings() {
        try{
            Connection connection = BibliothequeDAO.getConnection();
            java.sql.Statement statement = connection.createStatement();
            String query = "select * from booklending";
            ResultSet rs = statement.executeQuery(query);
            ArrayList<Row> rows = new ArrayList<>();
            while(rs.next()){
                Row newRow = new Row(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getTimestamp(4), rs.getTimestamp(5), rs.getTimestamp(6));
                rows.add(newRow);
            }
            return rows;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<Row> getNotReturnedBookLendings() {
        try{
            Connection connection = BibliothequeDAO.getConnection();
            java.sql.Statement statement = connection.createStatement();
            String query = "select * from booklending where return_date is null";
            ResultSet rs = statement.executeQuery(query);
            ArrayList<Row> rows = new ArrayList<>();
            while(rs.next()){
                Row newRow = new Row(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getTimestamp(4), rs.getTimestamp(5), rs.getTimestamp(6));
                rows.add(newRow);
            }
            return rows;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
    
     public ArrayList<Row> getReturnedBookLendings() {
        try{
            Connection connection = BibliothequeDAO.getConnection();
            java.sql.Statement statement = connection.createStatement();
            String query = "select * from booklending where return_date is not null";
            ResultSet rs = statement.executeQuery(query);
            ArrayList<Row> rows = new ArrayList<>();
            while(rs.next()){
                Row newRow = new Row(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getTimestamp(4), rs.getTimestamp(5), rs.getTimestamp(6));
                rows.add(newRow);
            }
            return rows;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
     
    public Booklending getNotReturnedBookLendingbyID (int id) {
        try {
            Connection connection = BibliothequeDAO.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from booklending where id="+id+";");
            while(rs.next()) {
                 Booklending bl = new Booklending(rs.getInt(1),rs.getString(2),rs.getInt(3), rs.getTimestamp(4).toLocalDateTime(), rs.getTimestamp(5).toLocalDateTime());
                 return bl;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
        public Booklending BookLendingbyBarcode (String barcode) {
        try {
            Connection connection = BibliothequeDAO.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from booklending where return_date is null and concat(bookitem_barcode,account_id)='"+barcode+"';");
            while(rs.next()) {
                 Booklending bl = new Booklending(rs.getInt(1),rs.getString(2),rs.getInt(3), rs.getTimestamp(4).toLocalDateTime(), rs.getTimestamp(5).toLocalDateTime());
                 System.out.print(rs.getTimestamp(6));
                 return bl;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
        
        public boolean confirmReturn(String barcode) {
            try{
                Connection connection = BibliothequeDAO.getConnection();
                String query="UPDATE booklending SET return_date = now() WHERE concat(bookitem_barcode,account_id) = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, barcode);
                int affectedRows = statement.executeUpdate();
                if(affectedRows > 0) return true;
            }
            catch(SQLException ex){
                ex.printStackTrace();
            }
            return false;
        }
        
        public String getReturnDate (String barcode) {
        try {
            Connection connection = BibliothequeDAO.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select return_date from booklending where concat(bookitem_barcode,account_id)='"+barcode+"';");
            while(rs.next()) {
                 return rs.getTimestamp(1).toString();
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
