package Interfaces;

import Entities.Account;
import Entities.LibraryCard;

public interface LibraryCardDAOInterface {
	
	boolean insertLibraryCard(Account account);
	LibraryCard getLibraryCardByAccount(Account a);
	
	
}
