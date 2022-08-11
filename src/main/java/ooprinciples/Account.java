package ooprinciples;

public class Account {

    private AccountType type;
    protected double balance;

    public Account(AccountType type) {
        this.type = type;
        this.balance = 0;
    }

    public double getBalance() {
        return this.balance;
    }

    public void deposit(double amount) throws DepositException {
        if (amount < 0) {
            throw new DepositException("You cannot deposit a negative amount!");
        }
        this.balance += amount;
    }

    public void withdraw(double amount) throws WithdrawException {
        if (amount < 0) {
            throw new WithdrawException("You cannot withdraw a negative amount!");
        }
        if (amount > this.balance && this.type.equals(AccountType.SAVINGS)) {
            throw new WithdrawException("You cannot overdraw on a savings account!");
        }
        this.balance -= amount;
    }
}
