package by.daryazalevskaya.finalproject.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class VacancySearchParams {
    private Integer countryId;
    private Integer specializationId;
    private String position;

    public boolean isEmptyCountry() {
        return Objects.isNull(countryId);
    }

    public boolean isEmptySpecialization() {
        return Objects.isNull(specializationId);
    }

    public boolean isEmptyPosition() {
        return (position==null || position.isEmpty());
    }

    public boolean isEmptyFull() {
        return isEmptyCountry() && isEmptyPosition() && isEmptySpecialization();
    }
}
