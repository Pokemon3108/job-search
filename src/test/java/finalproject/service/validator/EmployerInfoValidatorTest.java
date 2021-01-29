package finalproject.service.validator;

import by.daryazalevskaya.finalproject.service.validator.EmployerInfoValidator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class EmployerInfoValidatorTest {
    private EmployerInfoValidator validator=new EmployerInfoValidator();

    @DataProvider(name = "companies")
    public Object[][] testCompanies() {
        return new Object[][]{
                {"<p>", false},
                {"q".repeat(51), false},
                {"s".repeat(50), true},
                {"hello*", false},
                {"EPAM", true},
                {"h", false}
        };
    }

    @Test(dataProvider = "companies")
    public void isValidSkillsTest(String companyName, boolean isValid) {
        Assert.assertEquals(validator.isValidCompanyName(companyName), isValid);
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
}
