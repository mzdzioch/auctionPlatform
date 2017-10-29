package com.company;

import java.io.IOException;

public class UserRegistry {

    UserStorage userStorage;

    public UserRegistry(String filename) {
        this.userStorage = new UserStorage(filename);
    }

    public boolean addUser(User user) throws IOException {

        if(existUser(user))
            return false;
        else
            addToFile(user);
            return true;

    }

    public boolean existUser(User user) throws IOException {

        return userStorage.checkIfUserExist(user);

    }

    public boolean isLoginAndPasswordCorrect(User user) throws IOException {
        return userStorage.isLoginAndPasswordCorrect(user);
    }

    private void addToFile(User user){

        userStorage.writeUser(user);

    }
}
