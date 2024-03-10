package Interfaces;

import Entities.Account;
import Entities.LibraryCard;
import java.util.ArrayList;

public interface LibraryCardDAOInterface<T> {
	
	boolean insertLibraryCard(int id);
	LibraryCard getLibraryCardByAccount(Account a);
        ArrayList<T> getLibraryCards();
	ArrayList<T> getActiveLibraryCards();
        ArrayList<T> getNonActiveLibraryCards();
        T getInfoById(int id);
        T getInfoByEmail(String email);
        
	
}
