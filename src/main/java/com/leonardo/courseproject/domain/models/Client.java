package com.leonardo.courseproject.domain.models;

import com.leonardo.courseproject.domain.models.enums.ClientType;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "tb_client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "client_name")
    private String name;
    private String email;
    @Column(name = "EIN_or_IDno")
    private String EINOrIDNo;
    @Column(name = "client_type")
    private Integer clientType;

    @OneToMany(mappedBy = "client")
    private List<Address> addresses = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "tb_phone")
    private Set<String> phones = new HashSet<>();

    public Client() {
    }

    public Client(Long id, String name, String email, String EINOrIDNo, ClientType clientType) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.EINOrIDNo = EINOrIDNo;
        this.clientType = clientType.getCod();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEINOrIDNo() {
        return EINOrIDNo;
    }

    public void setEINOrIDNo(String EINOrIDNo) {
        this.EINOrIDNo = EINOrIDNo;
    }

    public ClientType getClientType() {
        return ClientType.toEnum(clientType);
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType.getCod();
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public Set<String> getPhones() {
        return phones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
