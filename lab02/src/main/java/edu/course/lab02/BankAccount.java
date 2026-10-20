package edu.course.lab02;

public class BankAccount {

    private int balance;

    public BankAccount(int balance) {
        if (balance < 0) {
            throw new IllegalArgumentException();
        }

        this.balance = balance;
    }

    public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException();
        }

        balance += amount;
    }

    public void withdraw(int amount) {
        if (amount <= 0 || amount > balance) {
            throw new IllegalArgumentException();
        }

        balance -= amount;
    }

    public int getBalance() {
        return balance;
    }
}