package finalproject.service.validator;

import by.daryazalevskaya.finalproject.service.validator.SkillsValidator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SkillsValidatorTest {

    private SkillsValidator validator=new SkillsValidator();

    @DataProvider(name = "skills")
    public Object[][] testSkills() {
        return new Object[][]{
                {"<p>", false},
                {"q".repeat(2001), false},
                {"s".repeat(2000), true},
                {"hello*", false},
                {"hello", true},
        };
    }


    @Test(dataProvider = "skills")
    public void isValidSkillsTest(String skills, boolean isValid) {
        Assert.assertEquals(validator.isValidSkills(skills), isValid);
    }
}
