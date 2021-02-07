package by.daryazalevskaya.finalproject.service.utils;

import by.daryazalevskaya.finalproject.model.Country;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The service uses for sorting.
 */
public class SortingService {

    /**
     * Sort countries by alphabet.
     *
     * @param list the list of countries to be sorted
     * @return sorted list
     */
    public List<Country> sortCountriesByAlphabet(List<Country> list) {
        return list.stream()
                .sorted(Comparator.comparing(country -> country.getName().toLowerCase()))
                .collect(Collectors.toList());
    }
}
