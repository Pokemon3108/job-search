package by.daryazalevskaya.finalproject.service;

import by.daryazalevskaya.finalproject.model.Country;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortingService {

    public List<Country> sortCountriesByAlphabet(List<Country> list) {
        return list.stream()
                .sorted(Comparator.comparing(Country::getName))
                .collect(Collectors.toList());
    }
}
