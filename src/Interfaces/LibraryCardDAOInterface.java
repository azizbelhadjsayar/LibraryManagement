package Interfaces;

import Entities.Account;
import Entities.LibraryCard;
import java.util.Vector;

public interface LibraryCardDAOInterface {
	
	boolean insertLibraryCard(int id);
	LibraryCard getLibraryCardByAccount(Account a);
        java.sql.ResultSet getLibraryCards();
	
        
	
}
