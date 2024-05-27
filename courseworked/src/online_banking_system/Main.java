package online_banking_system;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList<Account> accounts = new LinkedList<>();
        String file = "./src/online_banking_system/accounts.csv";
        ReadAccounts readAccounts = new ReadAccounts(file);

        LinkedList<String> firstNames = readAccounts.getFirstNames();
		LinkedList<String> lastNames = readAccounts.getLastNames();
		LinkedList<Integer> accountList = readAccounts.getAccounts();
		LinkedList<Integer> balanceList = readAccounts.getBalances();

		for (int i = 0; i < firstNames.size(); i++) { //making individual list of each person
		    Account account = new Account(firstNames.get(i), lastNames.get(i), balanceList.get(i), accountList.get(i));
		    accounts.add(account);
		}

		for (Account account : accounts) {
		    System.out.println("First Name: " + account.getFirstName());
		    System.out.println("Last Name: " + account.getLastName());
		    System.out.println("Account Number: " + account.getAccountNum());
		    System.out.println("Balance: " + account.getBalance());
		    System.out.println();
		}

        GUI gui = new GUI(accounts);//making an object of GUI
        gui.setVisible(true); //showing GUI
        
        
        

    }
}
