package com.revature.model;

import java.util.Objects;

public class Account {

//    Declare Account fields
    private int accountNo;
    private String accountType;
    private double balance;
    private int customer_id;

    public Account() {
    }

    public Account(int accountNo, String accountType, double balance, int customer_id) {
        this.accountNo = accountNo;
        this.accountType = accountType;
        this.balance = balance;
        this.customer_id = customer_id;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountNo == account.accountNo && Double.compare(account.balance, balance) == 0 && customer_id == account.customer_id && Objects.equals(accountType, account.accountType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNo, accountType, balance, customer_id);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNo=" + accountNo +
                ", accountType='" + accountType + '\'' +
                ", balance=" + balance +
                ", customer_id=" + customer_id +
                '}';
    }
}
