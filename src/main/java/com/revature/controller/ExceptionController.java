package com.revature.controller;

import com.revature.exception.ClientNotFoundException;
import io.javalin.Javalin;
import io.javalin.http.ExceptionHandler;

//
public class ExceptionController implements Controller{

    private ExceptionHandler clientNotFound= (e,ctx)->{
        ctx.status(404);
        ctx.json(e.getMessage());
    };

    private ExceptionHandler badArgument = (e, ctx) -> {
        ctx.status(400);
        ctx.json(e.getMessage());
    };

    @Override
    public void mapEndPoints(Javalin app) {
        app.exception(ClientNotFoundException.class, clientNotFound);
        app.exception(IllegalArgumentException.class,badArgument);
    }
}
