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
                    BIS.addBookItem(isbn);
                    sleep(5);
                }
            }
            statement.close();
                
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteBook(Book b) {
        return false;
    }

    @Override
    public boolean editBook(Book b) {
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
}
