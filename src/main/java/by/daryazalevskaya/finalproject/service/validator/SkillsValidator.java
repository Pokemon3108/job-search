package by.daryazalevskaya.finalproject.service.validator;

public class SkillsValidator  {
    private static final int SKILLS_LENGTH = 10;

    public boolean isValidSkills(String skills) {
        return skills.length()<SKILLS_LENGTH;
    }
}
