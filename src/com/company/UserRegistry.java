package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserRegistry {

    UserStorage userStorage;

    public UserRegistry(String filename) {
        this.userStorage = new UserStorage(filename);
    }

    public void addUser(User user) throws LoginExistException, IOException {

        if(existUser(user))
            throw new LoginExistException("");
        else
            addToFile(user);

    }

    public boolean existUser(User user) throws IOException {

        return userStorage.checkIfUserExist(user);

    }




    private void addToFile(User user){

        userStorage.writeUser(user);

    }
}
