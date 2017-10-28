package com.company;

import java.util.ArrayList;
import java.util.List;

public class UserRegistry {

    public void addUser(User user) throws LoginExistException{

        if(findByUser(user))
            throw new LoginExistException("");
        else
            addToFile(user);

    }

    public boolean existUser(User user){

        return true;
    }

    private boolean findByUser(User user){

        return true;
    }

    private void addToFile(User user){

    }
}
