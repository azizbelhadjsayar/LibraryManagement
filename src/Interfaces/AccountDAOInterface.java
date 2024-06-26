package Interfaces;
import Entities.Account;
import java.util.ArrayList;

public interface AccountDAOInterface {
	boolean insertAccount (Account a);
	Account getAccountbyEmail(String email);
	Account getAccountbyId(int id);
	Account getAccountbyUsername(String username);
	boolean deleteAccount(int id);
	Account checkAccount(String email, String password);
	boolean updateAccount();
        ArrayList<Account> getAllMembers();
}
