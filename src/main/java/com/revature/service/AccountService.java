package com.revature.service;

import com.revature.dao.AccountDao;
import com.revature.model.Account;

import java.sql.SQLException;

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
}
