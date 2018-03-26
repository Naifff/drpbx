package ru.geekbrains.dropbox.backend.service;

import org.springframework.stereotype.Service;

@Service
public class Authentication implements AuthenticationService {
    @Override
    public boolean login() {
        return true;
    }

    @Override
    public void logout() {

    }
}
