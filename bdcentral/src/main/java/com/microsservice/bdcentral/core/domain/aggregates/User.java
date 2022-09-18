package com.microsservice.bdcentral.core.domain.aggregates;

public class User {
    private String id;
    private String name;
    private String cpf;

    public User() {
    }

    public User(String id, String name, String cpf) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
    }
    public User create(String id, String name, String cpf){

        return new User(id,name,cpf);
    }
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }
}
