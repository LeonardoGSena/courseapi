package com.leonardo.courseproject.tests;

import com.leonardo.courseproject.domain.models.*;
import com.leonardo.courseproject.domain.models.enums.ClientType;

public class Factory {

    public static Category createCategory() {
        return  new Category(2L, "TV");
    }

    public static Product createProduct() {
        Product product = new Product(1L, "Iphone", 5999.99);
        product.getCategories().add(createCategory());
        return product;
    }

    public static Client createClient() {
        Client client = new Client(1L, "Barbara", "barbara@gmail.com", "11122233-00", ClientType.PERSON);
        client.getAddresses().add(createAdress());
        return client;
    }

    private static Address createAdress() {
        Address address = new Address(1L, "Rua dos passarinhos azuis", "100", "Casa", "Torre", "55444-444", new Client(), new City());
        return address;
    }

    public static City createCity() {
        return new City(1L, "Recife", new State());
    }

    public static State createState() {
        State state = new State(1L, "Pernambuco");
        state.getCities().add(createCity());
        return state;
    }
}
