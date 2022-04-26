package com.example.kata_3_1_2ver2.entities;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private int salary;

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    public User() {
    }

    public User(String username, String password, int salary, Collection<Role> roles) {
        this.username = username;
        this.password = password;
        this.salary = salary;
        this.roles = roles;
    }

    public User(Long id, String username, String password, int salary, Collection<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.salary = salary;
        this.roles = roles;
    }

    public String rolesToString (){
        StringBuilder stringBuilder = new StringBuilder();
        for (Role r:roles) {
            stringBuilder.append(r.getAuthority().replace("ROLE_", ""));
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
