package com.revature.controller;

import com.revature.model.Account;
import com.revature.service.AccountService;
import io.javalin.Javalin;
import io.javalin.http.Handler;

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
    @Override
    public void mapEndPoints(Javalin app) {
        app.post("/clients/{id}/accounts",addAccount);
    }
}
