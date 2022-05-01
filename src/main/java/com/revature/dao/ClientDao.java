package com.revature.dao;

import com.revature.model.Client;
import com.revature.utility.ConnectionUtility;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// TODO 10 Create a DAO (data access object) class for clients
public class ClientDao {

// TODO 11 Create the methods for the "CRUD" operations

    //    add (Create) a client (POST '/clients')
    public Client addClient(Client client) throws SQLException {
        try(Connection con = ConnectionUtility.getConnection()){
            String query = "INSERT INTO clients (first_name, last_name, email) VALUES (?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, client.getFirstName());
            pstmt.setString(2, client.getLastName());
            pstmt.setString(3, client.getEmail());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();

            if(rs.next()){
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");

                return new Client(id, firstName, lastName, email);
            }
        }
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
    public Client getClientById(int clientId) throws SQLException {
        try(Connection con = ConnectionUtility.getConnection()){
            String query = "SELECT * FROM clients WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1,clientId);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");

                return new Client(id, firstName, lastName, email);
            }
        }
        return null;
    }

//    update client by id (PUT '/clients/{client_id}'
    public Client updateClientById(int clientId, Client client) throws SQLException {
        try(Connection con = ConnectionUtility.getConnection()){
            String query = "UPDATE clients " +
                    "SET first_name = ?, " +
                    "last_name = ?, " +
                    "email = ? " +
                    "WHERE id = ?";

            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, client.getFirstName());
            pstmt.setString(2,client.getLastName());
            pstmt.setString(3,client.getEmail());
            pstmt.setInt(4,clientId);

            pstmt.executeUpdate();
        }
        return getClientById(clientId);
    }

//    delete a client by id (DELETE '/clients/{client_id})
    public boolean deleteClientById(int id) throws SQLException {
        try (Connection con = ConnectionUtility.getConnection()){
            String query = "DELETE FROM clients WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1,id);

            int numberOfRecordsDeleted = pstmt.executeUpdate();

            if(numberOfRecordsDeleted == 1){
                return true;
            }
        }
        return false;
    }
}
