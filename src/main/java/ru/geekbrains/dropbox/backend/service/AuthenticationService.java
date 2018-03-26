package ru.geekbrains.dropbox.backend.service;

public interface AuthenticationService {
    boolean login();
    void logout();
}
