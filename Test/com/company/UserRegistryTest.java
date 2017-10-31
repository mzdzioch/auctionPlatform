package com.company;

import com.company.exceptions.LoginExistException;
import com.company.model.User;
import com.company.service.UserRegistry;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

public class UserRegistryTest {

    UserRegistry userRegistry;

//    @Test(expected = FileNotFoundException.class)
//    public void shouldCheckIfUserNotExist() {
//        userRegistry = new UserRegistry("");
//    }

    @Test(expected = LoginExistException.class)
    public void shouldThrowExceptionLoginExistExeception() throws Exception {
        userRegistry = new UserRegistry("user.txt");
        userRegistry.addUser(new User("misiek", "bbbbbb"));
        userRegistry.addUser(new User("misiek", "bbbbbb"));
    }

    @Test
    public void shouldReturnTrueIfLoginExist() throws Exception {
        userRegistry = new UserRegistry("user.txt");

        assertThat(userRegistry.existUser(new User("misiek", "bbbbbb"))).isTrue();
    }

    @Test
    public void shouldReturnFalseIfLoginNotExist() throws Exception {
        userRegistry = new UserRegistry("user.txt");

        assertThat(userRegistry.existUser(new User("misiek1", "bbbbbb"))).isFalse();
    }

    @Test
    public void shouldReturnTrueIfLoginAndPasswordCorrect() throws Exception {
        userRegistry = new UserRegistry("user.txt");

        assertThat(userRegistry.isLoginAndPasswordCorrect(new User("misiek", "bbbbbb"))).isTrue();
    }

    @Test
    public void shouldReturnFalseIfLoginAndPasswordCorrect() throws Exception {
        userRegistry = new UserRegistry("user.txt");

        assertThat(userRegistry.isLoginAndPasswordCorrect(new User("misiek", "bbbbbba"))).isFalse();
    }

}