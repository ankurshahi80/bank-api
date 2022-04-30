package com.revature.service;

import com.revature.dao.ClientDao;
import com.revature.exception.ClientNotFoundException;
import com.revature.model.Client;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClientServiceTests {

    @Test
    public void getAllClients() throws SQLException {
        ClientDao mockClientDao = mock(ClientDao.class);
        ClientService clientService = new ClientService(mockClientDao);
        clientService.getAllClients();
        verify(mockClientDao,times(1)).getAllClients();
    }

    @Test
    public void getAllClientsWithFakeClients() throws SQLException {
        ClientDao mockClientDao = mock(ClientDao.class);

        List<Client> fakeClients = new ArrayList<>();
        fakeClients.add(new Client(1,"John","Doe","JohnDoe@gmail.com"));
        fakeClients.add(new Client(2,"Jane","Doe","JaneDoe@gmail.com"));

        when(mockClientDao.getAllClients()).thenReturn(fakeClients);

        ClientService clientService = new ClientService(mockClientDao);
        List<Client> actual = clientService.getAllClients();

        assertEquals(fakeClients,actual);

    }

    @Test
    public void clientNotFoundException(){
        ClientDao mockClientDao = mock(ClientDao.class);
        String clientId = "10";

        ClientService clientService = new ClientService(mockClientDao);

        assertThrows(ClientNotFoundException.class,()->{
            clientService.getClientById(clientId);
        });
    }

    @Test
    public void clientWithNonNumericIdThrowsIllegalArgumentException(){
        ClientDao mockClientDao = mock(ClientDao.class);
        String clientId = "abc";
        ClientService clientService = new ClientService(mockClientDao);

        assertThrows(IllegalArgumentException.class,()->{
            clientService.getClientById(clientId);
        });
    }
}
