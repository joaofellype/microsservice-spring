package com.pagamento.pix.core.domain.aggregates.users;

import org.springframework.data.annotation.Id;

public class User {
    @Id
    private String id;
    private String name;
    private String cpf;

    private User(String id, String name, String cpf) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
    }

    public static User create(String id, String name, String cpf) {
        return new User(id, name, cpf);
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
