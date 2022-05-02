package com.revature.main;

import com.revature.controller.AccountController;
import com.revature.controller.ClientController;
import com.revature.controller.Controller;
import com.revature.controller.ExceptionController;
import io.javalin.Javalin;

public class Driver {

    public static void main(String[] args) {
        Javalin app = Javalin.create();

        map(app, new ClientController());
        map(app, new AccountController());
        map(app, new ExceptionController());

        app.start();
    }

    public static void map(Javalin app, Controller... controllers){
        for (Controller c: controllers){
            c.mapEndPoints(app);
        }
    }
}
