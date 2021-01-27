package by.daryazalevskaya.finalproject.service.validator;

/**
 * Class {@code SkillsValidator} is a service for validation {@code Employer} objects
 *
 * @author Darya Zalevskaya
 * @version 1.0
 */
public class SkillsValidator extends Validator {
    private static final int SKILLS_LENGTH = 1000;
    private static final String NOT_TAG_REGEX = "[^<>*]+$";

    /**
     *
     * @param skills string of person skills
     * @return true if validation is successful, else - false
     */
    public boolean isValidSkills(String skills) {
        return (super.isValid(NOT_TAG_REGEX, skills) && skills.length() < SKILLS_LENGTH);
    }
}
