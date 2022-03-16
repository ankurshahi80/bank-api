package com.revature.dao;

import com.revature.model.Client;
import com.revature.utility.ConnectionUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// TODO 10 Create a DAO (data access object) class for clients
public class ClientDao {

// TODO 11 Create the methods for the "CRUD" operations

    //    add (Create) a client (POST '/clients')
    public Client addClient(Client client) {
        return null;
    }

// TODO 12 Create the method to get all clients

//    get (Read) all clients (GET '/clients')
    public List<Client> getAllClients() throws SQLException {

//        create an arraylist to hold the clients
        List<Client> clients = new ArrayList<>();


        try(Connection con = ConnectionUtility.getConnection()) {
            String query = "SELECT * FROM clients";
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");

                Client client = new Client(id,firstName,lastName,email);
                clients.add(client); // Add to the client arraylist
            }
        }
        return clients;
    }

//    get (Read) a client by id (GET '/clients/{client_id}')
    public Client getClientById(int id){
        return null;
    }

//    get (

//    update client by id (PUT '/clients/{client_id}'
    public Client updateClientById(int id){
        return null;
    }

//    delete a client by id (DELETE '/clients/{client_id})
    public Client deleteClientById(int id){
        return null;
    }
}
