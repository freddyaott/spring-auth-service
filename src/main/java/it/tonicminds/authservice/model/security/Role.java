package it.tonicminds.authservice.model.security;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {


    private String role;

    public Role(){}

    public Role(String role){
        this.role=role;
    }

    @Override
    public String getAuthority() {
        return role;
    }

    public void setRole(String role){
        this.role=role;
    }
}
