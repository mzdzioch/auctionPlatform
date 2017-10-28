package com.company;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class UserStorageTest {
    String filename;
    User user ;
    UserStorage userStorage;

    @Before
    public void setUp() throws Exception {
        filename = "users.txt";
        user = new User("Misiek", "1234");
        userStorage = new UserStorage(filename);
    }

    @Test
    public void writeUser() throws Exception {
        userStorage.writeUser(user);
        assertTrue(userStorage.checkIfUserExist(user));

    }

    @Test
    public void checkIfUserExist() throws Exception {
        User user = new User("Misiek", "1234");

    }

    @Test
    public void isLoginAndPasswordCorrect() throws Exception {
    }

}