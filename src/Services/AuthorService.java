/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Entities.Account;
import Entities.Author;
import Interfaces.AuthorDAOInterface;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class AuthorService implements AuthorDAOInterface{

    @Override
    public ArrayList<Author> getAllAuthors() {
        try {
                Connection connection = BibliothequeDAO.getConnection();
                ArrayList<Author> authors = new ArrayList<Author>();
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery("select * from author");
                while(rs.next()) {
                        Author a = new Author(rs.getInt(1), rs.getString(2));
                        authors.add(a);
                }
                return authors;
        }
        catch(SQLException e) {
                e.printStackTrace();
        }
        catch(RuntimeException e) {
                e.printStackTrace();
        }
        return null;
    }
    
}
