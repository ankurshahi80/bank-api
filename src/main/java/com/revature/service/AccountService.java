package com.revature.service;

import com.revature.dao.AccountDao;
import com.revature.model.Account;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountService {

    private AccountDao accountDao;

    public AccountService() {
        this.accountDao = new AccountDao();
    }

    public Account addAccount(String clientId, Account account) throws SQLException {
        int intId = Integer.parseInt(clientId);
        Account newAccount = this.accountDao.addAccount(intId, account);

        return newAccount;
    }

    public List<Account> getAccountsByClientId(String clientId, String amountLessThan, String amountGreaterThan) throws SQLException {
        int intId = Integer.parseInt(clientId);
        List<Account> clientAccounts = this.accountDao.getAccountsByClientId(intId);
        List<Account> filteredList = new ArrayList<>();

        if((amountLessThan != null) && (amountGreaterThan != null)){
            Double max = Double.parseDouble(amountLessThan);
            Double min = Double.parseDouble(amountGreaterThan);

            for (Account account: clientAccounts) {
                if((account.getBalance()>min) && (account.getBalance()<max)){
                    filteredList.add(account);
                }
            }
            return filteredList;
        }

        return clientAccounts;
    }

    public Account getAccountWithIds(String clientId, String accountNo) throws SQLException {
        int clntId = Integer.parseInt(clientId);
        int actNo = Integer.parseInt(accountNo);

        Account account = this.accountDao.getAccountWithIds(clntId, actNo );

        return account;
    }

    public boolean deleteAccount(String clientId, String accountNo) throws SQLException {
        int clntId = Integer.parseInt(clientId);
        int actNo = Integer.parseInt(accountNo);

        boolean accountDeleted = this.accountDao.deleteAccount(clntId, actNo);

        return accountDeleted;
    }

    public Account updateAccountByIds(String clientId, String accountNo, double balance) throws SQLException {
        int clntId = Integer.parseInt(clientId);
        int actNo = Integer.parseInt(accountNo);

        Account updatedAccount = this.accountDao.updateAccountByIds(clntId, actNo, balance);

        return updatedAccount;
    }
}
