package com.example.loan.management.loanmanager.api;

import com.example.loan.management.loanmanager.model.Role;

public class UserCreateRequest {
    private String name;
    private Role role;

    public UserCreateRequest(){

    }

    public UserCreateRequest(final String name,
                      final Role role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserCreateRequest{");
        sb.append("name='").append(name).append('\'');
        sb.append(", role=").append(role);
        sb.append('}');
        return sb.toString();
    }
}
