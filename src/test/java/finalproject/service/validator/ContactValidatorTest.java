package finalproject.service.validator;

import by.daryazalevskaya.finalproject.service.validator.ContactValidator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ContactValidatorTest {

    private ContactValidator validator = new ContactValidator();

    @DataProvider(name = "phones")
    public Object[][] testPhone() {
        return new Object[][]{
                {"+375296637779", true},
                {"375296637779", false},
                {"1", false},
                {"+79265777313", true}
        };
    }

    @Test(dataProvider = "phones")
    public void isValidPhoneTest(String phone, boolean isValid) {
        Assert.assertEquals(validator.isValidPhone(phone), isValid);
    }

    @DataProvider(name = "emails")
    public Object[][] testEmail() {
        return new Object[][]{
                {"1@1", false},
                {"zdashka@tut", false},
                {"pokemon31@tut.by", true},
                {"kokoko@tut.", false}
        };
    }

    @Test(dataProvider = "emails")
    public void isValidEmailTest(String email, boolean isValid) {
        Assert.assertEquals(validator.isValidEmail(email), isValid);
    }
}
