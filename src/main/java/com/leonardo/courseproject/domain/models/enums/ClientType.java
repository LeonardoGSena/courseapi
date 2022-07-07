package com.leonardo.courseproject.domain.models.enums;

public enum ClientType {
    PERSON(1, "Person"),
    ENTERPRISE(2, "Enterprise");

    private Integer cod;
    private String description;

    private ClientType(Integer cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDescription() {
        return description;
    }

    public static ClientType toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
        for (ClientType x : ClientType.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Invalid id: " + cod);
    }
}
