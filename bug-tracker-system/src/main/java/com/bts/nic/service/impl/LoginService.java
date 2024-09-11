package com.bts.nic.service.impl;

import com.bts.nic.modal.Login;
import com.bts.nic.repositories.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<Login> findByUsername(String username) {
        return loginRepository.findByUsername(username);
    }

    public void saveUser(Login login) {
        login.setPassword(passwordEncoder.encode(login.getPassword()));
        loginRepository.save(login);
    }
}
