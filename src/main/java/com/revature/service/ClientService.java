package com.revature.service;

import com.revature.dao.ClientDao;
import com.revature.exception.ClientNotFoundException;
import com.revature.model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class ClientService {

    private static Logger logger = LoggerFactory.getLogger(ClientService.class);

    private ClientDao clientDao;

    public ClientService() {
        this.clientDao = new ClientDao();
    }
    public ClientService(ClientDao mockClientDao){this.clientDao = mockClientDao;}

    public List<Client> getAllClients() throws SQLException {
        return this.clientDao.getAllClients();
    }

    public Client getClientById(String clientId) throws SQLException, ClientNotFoundException {

        try {
            int intId = Integer.parseInt(clientId);
            Client client = this.clientDao.getClientById(intId);

            if(client == null){
                throw new ClientNotFoundException("Client with id: "+ clientId + " was not found");
            }
            return client;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Non numeric ID entered. Please enter a numeric Client Id");
        }
    }

    public boolean deleteClientById(String clientId) throws SQLException {
        int intId = Integer.parseInt(clientId);
        Boolean recordDeleted = this.clientDao.deleteClientById(intId);

        return recordDeleted;
    }

    public Client addClient(Client client) throws SQLException {

        validateClientInformation(client);
        Client newClient = this.clientDao.addClient(client);

        return newClient;
    }

    public Client updateClient(String clientId, Client client) throws SQLException, ClientNotFoundException {
        try {
            int intId = Integer.parseInt(clientId);

            if(clientDao.getClientById(intId) == null){
                throw new ClientNotFoundException("Client with "+ clientId + " not found. Please check the client id.");
            };

            validateClientInformation(client);

            Client updatedClient = this.clientDao.updateClientById(intId,client);

            return updatedClient;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Id provided must be a valid Integer");
        }
    }

    private void validateClientInformation(Client client){
        client.setFirstName(client.getFirstName().trim());
        client.setLastName(client.getLastName().trim());

        if(!client.getFirstName().matches("[a-zA-Z]+")){
            throw new IllegalArgumentException("First name must only have alphabetical characters. First name input was "+ client.getFirstName());
        }

        if(!client.getLastName().matches("[a-zA-Z]+")){
            throw new IllegalArgumentException("Last name must only have alphabetical characters. Last name input was " + client.getLastName());
        }

        if(!client.getEmail().matches("^(.+)@(\\S+)$")){
            throw new IllegalArgumentException("Incorrect email address. Email provided was " + client.getEmail());
        }
    }
}
