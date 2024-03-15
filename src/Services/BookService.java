/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;


import Entities.Book;
import Entities.BookItem;
import Interfaces.BookDAOInterface;
import java.sql.Statement;
import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author LENOVO
 */
public class BookService implements BookDAOInterface {

    @Override
    public boolean addBook(Book b) {
        try {   
            Connection connection = BibliothequeDAO.getConnection();
            String INSERT_BOOK_SQL = "INSERT INTO book (title, author_id, language, numberofpages, numberofitems, price) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(INSERT_BOOK_SQL, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, b.getTitle());
            statement.setInt(2, b.getAuthorID());
            statement.setString(3, b.getLanguage());
            statement.setInt(4, b.getNbPages());
            statement.setInt(5, b.getNbItems());
            statement.setDouble(6, b.getPrice());
            int affectedRows = statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int isbn = generatedKeys.getInt(1);
                int nbitems =  b.getNbItems();
                BookItemService BIS = new BookItemService();
                for(int i=0; i<nbitems; i++) {
                    BIS.addBookItem(isbn, b.getItemsBarcodes()[i]);
                }
            }
            statement.close();
                
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteBook(int isbn) {
        try {
            Connection connection = BibliothequeDAO.getConnection();
            PreparedStatement preparedStatement;
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select l.bookitem_barcode from booklending l join bookitem bi on l.bookitem_barcode = bi.barcode where bi.isbn_book="+isbn+";");
            while(rs.next()) {
                String deleteLendings = "delete from booklending where bookitem_barcode = ?";
                preparedStatement = connection.prepareStatement(deleteLendings);
                preparedStatement.setString(1, rs.getString(1));
                int lendingDeleted = preparedStatement.executeUpdate();
            }
            String deleteBooks = "delete from book where isbn = ?";
            String deleteItems = "delete from bookitem where isbn_book = ?";
            preparedStatement = connection.prepareStatement(deleteItems);
            preparedStatement.setInt(1, isbn);
            int itemsDeleted = preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(deleteBooks);
            preparedStatement.setInt(1, isbn);
            int booksDeleted = preparedStatement.executeUpdate();
            return booksDeleted==1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean editBook(int isbn, String title, int authorID, String lang, int pages, double price) {
        try {
            Connection connection = BibliothequeDAO.getConnection();
            String updateQuery = "UPDATE book SET title = ?, author_id = ?, language = ?, numberofpages = ?, price = ? WHERE isbn = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, title);
            preparedStatement.setInt(2, authorID);
            preparedStatement.setString(3, lang);
            preparedStatement.setInt(4, pages);
            preparedStatement.setDouble(5, price);
            preparedStatement.setInt(6, isbn);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected==1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Book searchBookbyTitle(String title) {
        return null;
    }
    
    public String searchTitleByISBN(int isbn) {
        try {
            Connection connection = BibliothequeDAO.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select title from book where isbn="+isbn+";");
            while(rs.next()) {
                 return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public Book getBookbyISBN(int isbn) {
        try {
            Connection connection = BibliothequeDAO.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from book where isbn="+isbn+";");
            while(rs.next()) {
                 return new Book(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getInt(5), rs.getInt(6), rs.getDouble(7));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public record bookRow(int ISBN, String title, String author, String language){
    }    
    
    public ArrayList<bookRow> getBooks(){
        try{
            Connection connection = BibliothequeDAO.getConnection();
            Statement statement = connection.createStatement();
            ArrayList<bookRow> bookRows = new ArrayList<>();
            ResultSet rs = statement.executeQuery("SELECT b.isbn, b.title, a.fullname, b.language FROM Book b JOIN Author a ON(b.author_id = a.id)");
            while(rs.next()){
                bookRow bookRow = new bookRow(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                bookRows.add(bookRow);
            }
            return bookRows;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
