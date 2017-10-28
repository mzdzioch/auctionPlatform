package test;

import com.company.CredentialsToShortException;
import com.company.LoginNullException;
import com.company.User;
import org.junit.Test;


public class UserTest {


    User testUser;



    @Test(expected = LoginNullException.class)
    public void shouldThrowExceptionWhenLoginIsNull() throws LoginNullException, CredentialsToShortException {
        new User(null, "asdas");
    }

    @Test(expected = LoginNullException.class)
    public void shouldThrowExceptionWhenPasswordIsNull() throws LoginNullException, CredentialsToShortException {
        new User("fjkebejkbf", null);
    }


    @Test (expected = CredentialsToShortException.class)
    public void shouldThrowExceptionWhenLoginIsTooShort() throws CredentialsToShortException, LoginNullException {
        new User("fjke", "dsbjsbjbd");
    }

    @Test (expected = CredentialsToShortException.class)
    public void shouldThrowExceptionWhenPasswordIsTooShort() throws CredentialsToShortException, LoginNullException {
        new User("fjkeff", "dsbj");
    }



}

