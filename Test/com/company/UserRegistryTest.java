package com.company;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.Assert.*;

public class UserRegistryTest {

    UserRegistry userRegistry;

    @Test
    public void shouldCheckIfUserIsAdded() throws Exception {
        userRegistry = new UserRegistry("user.txt");
        assertThat(userRegistry.existUser(new User("misiek", "bbbbbb"))).isFalse();
    }

    @Test
    public void shouldCheckIfUserExist() throws Exception {
        userRegistry = new UserRegistry("user.txt");
        userRegistry.existUser(new User("misiek12", "test"));
        assertThat(userRegistry.existUser(new User("misiek12", "test12"))).isTrue();

    }

    @Test
    public void shouldCheckIfLoginAndPasswordCorrect() throws Exception {
    }

}