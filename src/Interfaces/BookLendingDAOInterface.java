/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
*/
package Interfaces;
import Entities.Account;
import Entities.BookItem;
/**
 *
 * @author LENOVO
 */
public interface BookLendingDAOInterface {
    Boolean addBookLending(int days, Account a, BookItem bi);
    
}
