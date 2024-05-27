package online_banking_system;

class Account extends Customer {
    private int balance;
    private int accountNumber;

    public Account(String fName, String lName, int balance, int accountNumber) {
        setFirstName(fName);
        setLastName(lName);
        this.balance = balance;
        this.accountNumber = accountNumber;
    }

    public int getBalance() {
        return balance;
    }

    public int getAccountNum() {
        return accountNumber;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }

}
