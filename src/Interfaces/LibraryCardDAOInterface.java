package Interfaces;

import Entities.Account;
import Entities.LibraryCard;
import java.util.ArrayList;

public interface LibraryCardDAOInterface<T> {
	
	boolean insertLibraryCard(int id);
	LibraryCard getLibraryCardByAccount(Account a);
        ArrayList<T> getLibraryCards();
	
        
	
}
