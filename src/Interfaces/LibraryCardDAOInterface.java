package Interfaces;

import Entities.Account;
import Entities.LibraryCard;

public interface LibraryCardDAOInterface {
	
	boolean insertLibraryCard(int id);
	LibraryCard getLibraryCardByAccount(Account a);
	
	
}
