package com.leonardo.courseproject.domain.repositories;

import com.leonardo.courseproject.domain.models.Address;
import com.leonardo.courseproject.domain.models.City;
import com.leonardo.courseproject.domain.models.Client;
import com.leonardo.courseproject.domain.models.State;
import com.leonardo.courseproject.domain.models.enums.ClientType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    private Long totalClients;
    private Long existId;
    private Long nonExistId;
    @BeforeEach
    void Setup() throws Exception {
        totalClients = 0L;
        existId = 1L;
        nonExistId = 111L;
    }

    @Test
    @DisplayName("It should save with autoincrement when id is null")
    void itShouldPersistWithAutoincrementWhenIdIsNull() {

        State state = new State(1L, "Pernambuco");
        City city = new City(1L, "Recife", state);
        state.getCities().add(city);
        Client client = new Client(1L, "Barbara", "barbara@gmail.com", "11122233-00", ClientType.PERSON);
        Address address = new Address(1L, "Rua dos passarinhos azuis", "100", "Casa", "Torre", "55444-444", client, city);
        client.getAddresses().add(address);

        client = clientRepository.save(client);

        assertNotNull(client.getId());
        assertEquals(totalClients + 1, client.getId());
    }

    @Test
    @DisplayName("It should Delete client when id exists")
    void itShouldDeleteClientWhenIdExists() {
        clientRepository.deleteById(existId);
        Optional<Client> client = clientRepository.findById(existId);

        assertFalse(client.isPresent(), "It should return false");
    }

    @Test
    @DisplayName("It should throw EmptyResultDataAccessException when id does not exist")
    void itShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExist() {
        assertThrows(EmptyResultDataAccessException.class, () -> {
           clientRepository.deleteById(nonExistId);
        });
    }
}
