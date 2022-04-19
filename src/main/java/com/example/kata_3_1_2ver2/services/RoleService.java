package com.example.kata_3_1_2ver2.services;

import com.example.kata_3_1_2ver2.entities.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role findByName(String name);
}
