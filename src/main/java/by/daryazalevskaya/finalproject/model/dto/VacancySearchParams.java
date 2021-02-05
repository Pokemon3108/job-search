package by.daryazalevskaya.finalproject.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * DTO object for keeping search params from request
 */
@Getter
@Setter

public class VacancySearchParams {
    private Integer countryId;
    private Integer specializationId;
    private String position;

    /**
     * @return true if country wasn't chosen as a search param, else - false
     */
    public boolean isEmptyCountry() {
        return Objects.isNull(countryId);
    }

    /**
     * @return true if specialization wasn't chosen as a search param, else - false
     */
    public boolean isEmptySpecialization() {
        return Objects.isNull(specializationId);
    }

    /**
     * @return true if position wasn't chosen as a search param, else - false
     */
    public boolean isEmptyPosition() {
        return (position==null || position.isEmpty());
    }

    /**
     * @return true if all search params are empty, else - false
     */
    public boolean isEmptyFull() {
        return isEmptyCountry() && isEmptyPosition() && isEmptySpecialization();
    }
}
