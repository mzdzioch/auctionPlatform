package com.company;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

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