package finalproject.service.utils;

import by.daryazalevskaya.finalproject.model.Country;
import by.daryazalevskaya.finalproject.service.utils.SortingService;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SortingServiceTest {
    private SortingService sortingService=new SortingService();

    @Test
    public void sortCountriesByAlphabetTest() {
        List<Country> countryList=List.of(new Country("Russia"), new Country("Belarus"), new Country ("Ukraine"), new Country ("USA"));
        List<Country> sortedServiceList=sortingService.sortCountriesByAlphabet(countryList);
        List<Country> sortedList= List.of(new Country("Belarus"), new Country("Russia"), new Country ("Ukraine"),new Country ("USA"));
        Assert.assertEquals(sortedList, sortedServiceList);
    }
}
