package com.revature.controller;

import com.revature.exception.ClientNotFoundException;
import com.revature.model.Client;
import com.revature.service.ClientService;
import io.javalin.Javalin;
import io.javalin.http.Handler;

import java.sql.SQLException;
import java.util.List;

public class ClientController implements Controller{

    private ClientService clientService;

    public ClientController() {
        this.clientService = new ClientService();
    }

    private Handler getAllClients = (ctx) -> {
        List<Client> clients = clientService.getAllClients();

        ctx.json(clients);
    };

    private Handler getClientById = (ctx) -> {
        String id = ctx.pathParam("id");
        try {
            Client client = clientService.getClientById(id);

            ctx.json(client);
        } catch (ClientNotFoundException e) {
            ctx.json(e.getMessage());
            ctx.status(404); // Not found
        } catch (IllegalArgumentException e){
            ctx.json(e.getMessage());
            ctx.status(400); // Bad request
        }
    };

    private Handler deleteClientById = (ctx) -> {
        String id = ctx.pathParam("id");
        boolean recordDeleted = clientService.deleteClientById(id);

        ctx.json(recordDeleted);
    };

    private Handler addClient = (ctx) -> {
        Client clientToAdd = ctx.bodyAsClass(Client.class);

        Client newClient = clientService.addClient(clientToAdd);

        ctx.json(newClient);
    };

    private Handler updateClient = ctx -> {
        String id = ctx.pathParam("id");
        Client clientToAdd = ctx.bodyAsClass(Client.class);

        Client updatedClient = clientService.updateClient(id, clientToAdd);

        ctx.json(updatedClient);
    };

    @Override
    public void mapEndPoints(Javalin app) {
        app.get("/clients", getAllClients);
        app.get("/clients/{id}", getClientById);
        app.delete("/clients/{id}", deleteClientById);
        app.post("/clients",addClient);
        app.put("/clients/{id}", updateClient);
    }
}
