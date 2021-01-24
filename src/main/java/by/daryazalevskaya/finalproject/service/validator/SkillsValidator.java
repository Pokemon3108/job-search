package by.daryazalevskaya.finalproject.service.validator;

public class SkillsValidator extends Validator {
    private static final int SKILLS_LENGTH = 1000;
    private static final String NOT_TAG_REGEX = "[^<>*]+$";

    public boolean isValidSkills(String skills) {
        return (super.isValid(NOT_TAG_REGEX, skills) && skills.length() < SKILLS_LENGTH);
    }
}
