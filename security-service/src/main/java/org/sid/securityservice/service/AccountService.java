package org.sid.securityservice.service;

import java.util.List;

import org.sid.securityservice.entities.AppRole;
import org.sid.securityservice.entities.AppUser;

public interface AccountService {
    AppUser addNewUser(AppUser appUser);
    AppRole addNewRole(AppRole appRole);
    void addRoleToUser(String username,String roleName);
    AppUser loadUserByUsername(String username);
    List<AppUser> listUsers();

}
