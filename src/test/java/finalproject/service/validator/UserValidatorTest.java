package finalproject.service.validator;

import by.daryazalevskaya.finalproject.service.validator.UserValidator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UserValidatorTest {

    private UserValidator validator=new UserValidator();

    @DataProvider(name = "passwords")
    public Object[][] testPasswords() {
        return new Object[][]{
                {"1Aa!", false},
                {"12345678", false},
                {"QWqw31!!", true},
                {"QW31!!!!!/", false},
                {"31082001qw", false},
        };
    }

    @Test(dataProvider = "passwords")
    public void isValidPasswordTest(String password, boolean isValid) {
        Assert.assertEquals(validator.isValidPassword(password), isValid);
    }


    @DataProvider(name = "emails")
    public Object[][] testEmails() {
        return new Object[][]{
                {"1@1", false},
                {"s@t", false},
                {"pololo@gmail", false},
                {"pololo@gmail.com", true},
                {"@gmail.com", false},
        };
    }

    @Test(dataProvider = "emails")
    public void isValidEmailTest(String email, boolean isValid) {
        Assert.assertEquals(validator.isValidEmail(email), isValid);
    }

}
