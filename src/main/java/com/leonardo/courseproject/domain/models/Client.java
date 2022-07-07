package com.leonardo.courseproject.domain.models;

import com.leonardo.courseproject.domain.models.enums.ClientType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Client {

    private Long id;
    private String name;
    private String email;
    private String EINOrIDNo;
    private ClientType clientType;

    private List<Address> addresses = new ArrayList<>();

    private Set<String> phones = new HashSet<>();
}
