package finalproject.service.validator;

import by.daryazalevskaya.finalproject.service.validator.EmployeePersonalInfoValidator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class EmployeePersonalInfoValidatorTest {

    private EmployeePersonalInfoValidator validator=new EmployeePersonalInfoValidator();

    @DataProvider(name = "names")
    public Object[][] testNames() {
        return new Object[][]{
                {"dasha", true},
                {"dasha-1", false},
                {"dasha-dasha", true},
                {"стул", true},
                {"й".repeat(35), false},
                {"qwerty uiop", true}
        };
    }

    @Test(dataProvider = "names")
    public void isValidNameTest(String name, boolean isValid) {
        Assert.assertEquals(validator.isValidFirstName(name), isValid);
    }

    @DataProvider(name = "surnames")
    public Object[][] testSurnames() {
        return new Object[][]{
                {"bill", true},
                {"bill-klinton", true},
                {"Складовская-Кюри", true},
                {"11", false},
                {"й".repeat(55), false}
        };
    }

    @Test(dataProvider = "surnames")
    public void isValidSurnameTest(String surname, boolean isValid) {
        Assert.assertEquals(validator.isValidSurname(surname), isValid);
    }

    @DataProvider(name = "cities")
    public Object[][] testCities() {
        return new Object[][]{
                {"Moscow", true},
                {"Франкфурт-на-Майне", true},
                {"Франкфурт-на-Майне1", false},
                {"11", false},
                {"й".repeat(101), false}
        };
    }

    @Test(dataProvider = "cities")
    public void isValidCityTest(String city, boolean isValid) {
        Assert.assertEquals(validator.isValidCity(city), isValid);
    }

    @DataProvider(name = "dates")
    public Object[][] testDates() {
        return new Object[][]{
                {LocalDate.of(2012,12,12), true},
                {LocalDate.now(), false},
                {LocalDate.of(2034,8, 31), false},
        };
    }

    @Test(dataProvider = "dates")
    public void isValidBirthdayTest(LocalDate localDate, boolean isValid) {
        Assert.assertEquals(validator.isValidBirthday(localDate), isValid);
    }
}
