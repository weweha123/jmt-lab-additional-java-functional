package edu.hneu.mjt.makarenkonazar.model;

public class CreditBankCard extends BankCard {
    private double creditLimit;

    public CreditBankCard(String number, User user, double creditLimit) {
        super(number, user);
        this.creditLimit = creditLimit;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }
}
