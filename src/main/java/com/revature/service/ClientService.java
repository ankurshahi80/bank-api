package com.revature.service;

import com.revature.dao.ClientDao;
import com.revature.model.Client;

import java.sql.SQLException;
import java.util.List;

public class ClientService {

    private ClientDao clientDao;

    public ClientService() {
        this.clientDao = new ClientDao();
    }

    public List<Client> getAllClients() throws SQLException {
        return this.clientDao.getAllClients();
    }

    public Client getClientById(String clientId) throws SQLException {

        int intId = Integer.parseInt(clientId);
        Client client = this.clientDao.getClientById(intId);

        return client;
    }
}
