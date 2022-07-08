package com.leonardo.courseproject.domain.repositories;

import com.leonardo.courseproject.domain.models.Address;
import com.leonardo.courseproject.domain.models.City;
import com.leonardo.courseproject.domain.models.Client;
import com.leonardo.courseproject.domain.models.State;
import com.leonardo.courseproject.domain.models.enums.ClientType;
import com.leonardo.courseproject.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    private Long totalClients;

    @BeforeEach
    void Setup() throws Exception {
        totalClients = 0L;
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
}
