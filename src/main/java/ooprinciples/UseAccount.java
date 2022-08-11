package ooprinciples;

public class UseAccount {

    public static void main(String[] args) throws DepositException {

        SavingsAccount sa = new SavingsAccount();
        sa.deposit(500);
    }
}
