package finalproject.service.validator;

import by.daryazalevskaya.finalproject.service.validator.VacancyValidator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class VacancyValidatorTest  {
    private VacancyValidator validator=new VacancyValidator();

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

    @DataProvider(name = "positions")
    public Object[][] testPosition() {
        return new Object[][]{
                {"й".repeat(101), true},
                {"q".repeat(300), false}
        };
    }

    @Test(dataProvider = "positions")
    public void isValidPositionTest(String position, boolean isValid) {
        Assert.assertEquals(validator.isValidPosition(position), isValid);
    }

    @DataProvider(name = "longText")
    public Object[][] testLongText() {
        return new Object[][]{
                {"й".repeat(101), true},
                {"q".repeat(3000), false}
        };
    }

    @Test(dataProvider = "longText")
    public void isValidLongTexTest(String text, boolean isValid) {
        Assert.assertEquals(validator.isValidLongText(text), isValid);
    }
}
