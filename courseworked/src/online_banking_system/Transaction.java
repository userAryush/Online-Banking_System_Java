package online_banking_system;

class Transaction {
    public void transfer(Account source, Account destination, int amount) {
        source.withdraw(amount);
        destination.deposit(amount);
}
}
