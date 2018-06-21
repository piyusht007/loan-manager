package com.example.loan.management.loanmanager.model;

import java.util.UUID;

public class User {

    private UUID id;
    private String name;
    private Role role;

    public User(final UUID id,
                final String name,
                final Role role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public boolean equals(final Object o) {
        if( this == o ) {
            return true;
        }
        if( o == null || getClass() != o.getClass() ) {
            return false;
        }

        final User user = (User) o;

        if( id != null ? !id.equals(user.id) : user.id != null ) {
            return false;
        }
        if( name != null ? !name.equals(user.name) : user.name != null ) {
            return false;
        }
        return role == user.role;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + ( name != null ? name.hashCode() : 0 );
        result = 31 * result + ( role != null ? role.hashCode() : 0 );
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", role=").append(role);
        sb.append('}');
        return sb.toString();
    }
}
