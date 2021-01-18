package finalproject.service.validator;

import by.daryazalevskaya.finalproject.service.validator.EmployeePersonalInfoValidator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PersonalInfoValidatorTest {

    private EmployeePersonalInfoValidator validator=new EmployeePersonalInfoValidator();

    @Test
    public void testValidName() {
        String name="Darya";
        Assert.assertTrue(validator.isValidFirstName(name));
    }
}
