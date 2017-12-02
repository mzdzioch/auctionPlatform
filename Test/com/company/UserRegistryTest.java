package com.company;

import com.company.exceptions.LoginExistException;
import com.company.model.User;
import com.company.repository.UserRegistry;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserRegistryTest {

    UserRegistry userRegistry;
    String filename;
    User user;

    //    @Test(expected = FileNotFoundException.class)
//    public void shouldCheckIfUserNotExist() {
//        userRegistry = new UserRegistry("");
//    }
    @Before
    public void setUp() throws Exception {
        filename = "user.txt";
        user = new User("misiek", "bbbbbb");
        userRegistry = new UserRegistry(filename);
    }

    @Test(expected = LoginExistException.class)
    public void shouldThrowExceptionLoginExistExeception() throws Exception {
        userRegistry = new UserRegistry("user.txt");
        //userRegistry.addUser(new User("misiek", "bbbbbb"));
        //userRegistry.addUser(new User("misiek", "bbbbbb"));
    }

    @Test
    public void shouldReturnTrueIfLoginExist() throws Exception {
        userRegistry = new UserRegistry("user.txt");

        //assertThat(userRegistry.existUser(new User("misiek", "bbbbbb"))).isTrue();
    }

    @Test
    public void shouldReturnFalseIfLoginNotExist() throws Exception {
        userRegistry = new UserRegistry("user.txt");

        //assertThat(userRegistry.existUser(new User("misiek1", "bbbbbb"))).isFalse();
    }

    @Test
    public void shouldReturnTrueIfLoginAndPasswordCorrect() throws Exception {
        userRegistry = new UserRegistry("user.txt");

        assertThat(userRegistry.isLoginAndPasswordCorrect(new User("misiek", "bbbbbb"))).isTrue();
    }

//    @Test
//    public void shouldReturnFalseIfLoginAndPasswordCorrect() throws Exception {
//        userRegistry = new UserRegistry("user.txt");
//
//        assertThat(userRegistry.isLoginAndPasswordCorrect(new User("misiek", "bbbbbb"))).isFalse();
//    }


    @Test
    public void writeUser() throws Exception {
        userRegistry.writeUser(user);
        //assertTrue(userRegistry.checkIfUserExist(user));

    }

    @Test
    public void checkIfUserExist() throws Exception {
        User user = new User("misiek", "bbbbbb");

    }

    @Test
    public void isLoginAndPasswordCorrect() throws Exception {
    }

}


