package org.sid.securityservice.service;

import java.util.List;

import org.sid.securityservice.entities.AppRole;
import org.sid.securityservice.entities.AppUser;
import org.sid.securityservice.repository.AppRoleRepository;
import org.sid.securityservice.repository.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImp implements AccountService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;
    public  AccountServiceImp(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository,
            PasswordEncoder passwordEncoder){
        this.appUserRepository = appUserRepository; this.appRoleRepository = appRoleRepository;
        this.passwordEncoder = passwordEncoder;
        
    }
    @Override
    public AppUser addNewUser(AppUser appUser) {
        String pw=appUser.getPassword();
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        System.out.println(pw);
        return appUserRepository.save(appUser);
    }
    @Override
    public AppRole addNewRole(AppRole appRole) {
        return appRoleRepository.save(appRole);
    }
        @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser appUser=appUserRepository.findByUsername(username); AppRole appRole=appRoleRepository.findByRoleName(roleName);
            appUser.getAppRoles().add(appRole);
    }
    @Override
    public AppUser loadUserByUsername(String username) { return appUserRepository.findByUsername(username); };
        @Override
        public List<AppUser> listUsers() {
            return appUserRepository.findAll();
    }
}