package ru.geekbrains.dropbox.backend.service;

public interface AuthenticationService {
    boolean login(String login,String pass);
    void logout();
}
