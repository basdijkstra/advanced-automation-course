package ooprinciples;

public class SavingsAccount extends Account {

    private final double interestRate;

    public SavingsAccount() {

        super(AccountType.SAVINGS);
        this.interestRate = 0.03;
    }

    public SavingsAccount(double interestRate) {

        super(AccountType.SAVINGS);
        this.interestRate = interestRate;
    }

    public void addInterest() {

        this.balance *= (1 + this.interestRate);
    }

    @Override
    public void withdraw(double amount) throws WithdrawException {
        if (amount < 0) {
            throw new WithdrawException("You cannot withdraw a negative amount!");
        }
        if (amount > this.balance) {
            throw new WithdrawException("You cannot overdraw on a savings account!");
        }
        this.balance -= amount;
    }
}
