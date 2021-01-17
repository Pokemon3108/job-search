package finalproject.service.validator;

import by.daryazalevskaya.finalproject.service.validator.PersonalInfoValidator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PersonalInfoValidatorTest {

    private PersonalInfoValidator validator=new PersonalInfoValidator();

    @Test
    public void testValidName() {
        String name="Darya";
        Assert.assertTrue(validator.isValidFirstName(name));
    }
}
