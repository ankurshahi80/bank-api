package com.revature.controller;

import com.revature.model.Account;
import com.revature.service.AccountService;
import io.javalin.Javalin;
import io.javalin.http.Handler;

import java.util.List;

public class AccountController implements Controller {

    private AccountService accountService;

    public AccountController(){
        this.accountService = new AccountService();
    }

    private Handler addAccount = (ctx) ->{
      String id = ctx.pathParam("id");
      Account accountToAdd = ctx.bodyAsClass(Account.class);

      Account newAccount = accountService.addAccount(id, accountToAdd);

      ctx.json(newAccount);
    };

    private Handler getAccountsByClientId = (ctx) -> {
        String id = ctx.pathParam("id");
        String amountLessThan = ctx.queryParam("amountLessThan");
        String amountGreaterThan = ctx.queryParam("amountGreaterThan");

        List<Account> clientAccounts = accountService.getAccountsByClientId(id, amountLessThan, amountGreaterThan);

        ctx.json(clientAccounts);
    };

    private Handler getAccountWithIds = (ctx) -> {
        String clientId = ctx.pathParam("id");
        String accountNo = ctx.pathParam("account_id");

        Account account = accountService.getAccountWithIds(clientId, accountNo);

        ctx.json(account);
    };

    private Handler deleteAccount = (ctx) -> {
        String clientId = ctx.pathParam("id");
        String accountNo = ctx.pathParam("account_id");

        boolean recordDeleted = accountService.deleteAccount(clientId, accountNo);

        ctx.json(recordDeleted);
    };

    @Override
    public void mapEndPoints(Javalin app) {
        app.post("/clients/{id}/accounts",addAccount);
        app.get("/clients/{id}/accounts",getAccountsByClientId);
        app.get("/clients/{id}/accounts/{account_id}", getAccountWithIds);
        app.delete("/clients/{id}/accounts/{account_id}",deleteAccount);
    }
}
