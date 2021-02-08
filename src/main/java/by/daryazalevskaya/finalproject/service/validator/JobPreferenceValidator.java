package by.daryazalevskaya.finalproject.service.validator;

public class JobPreferenceValidator extends Validator {
    private static final int MAX_EXPERIENCE=60;
    private static final int MIN_EXPERIENCE=0;
    private static final int MIN_SALARY=0;
    private static final int POSITION_MAX_LENGTH=255;

    public boolean isValidExperience(int experience) {
        return (experience>=MIN_EXPERIENCE && experience<=MAX_EXPERIENCE);
    }

    public boolean isValidSalary(int salary) {
        return salary>=MIN_SALARY;
    }

    public boolean isValidPosition(String position) {
        return (position.length()<=POSITION_MAX_LENGTH && super.isValid(NOT_TAG_REGEX, position));
    }
}
