package ru.geekbrains.dropbox.backend.service;

import org.springframework.stereotype.Service;
import ru.geekbrains.dropbox.backend.dao.Users;

@Service
public class Authentication implements AuthenticationService {
    @Override
    public boolean login(String login,String pass) {
        if(Users.user.equals(login)&&Users.pass.equals(pass)){

        return true;}
        return false;
    }

    @Override
    public void logout() {

    }
}
