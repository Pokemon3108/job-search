package by.daryazalevskaya.finalproject.model.dto;

import lombok.Builder;
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
        return Objects.nonNull(countryId);
    }

    public boolean isEmptySpecialization() {
        return Objects.nonNull(specializationId);
    }

    public boolean isEmptyPosition() {
        return position.isEmpty();
    }
}
