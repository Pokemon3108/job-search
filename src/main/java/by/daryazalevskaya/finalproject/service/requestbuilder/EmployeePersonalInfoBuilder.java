package by.daryazalevskaya.finalproject.service.requestbuilder;

import by.daryazalevskaya.finalproject.model.Country;
import by.daryazalevskaya.finalproject.model.employee.EmployeePersonalInfo;
import by.daryazalevskaya.finalproject.model.type.Gender;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EmployeePersonalInfoBuilder implements RequestBuilder<EmployeePersonalInfo> {
    @Override
    public EmployeePersonalInfo build(HttpServletRequest request) {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String strDate = request.getParameter("birthday");
        LocalDate localDate = null;
        if (!strDate.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            localDate = LocalDate.parse(strDate, formatter);
        }

        String countryName=request.getParameter("country");
        Country country=new Country(countryName);

        Gender gender = Gender.valueOf(request.getParameter("gender"));
        String city = request.getParameter("city");

        EmployeePersonalInfo employeePersonalInfo= EmployeePersonalInfo.builder()
                .gender(gender)
                .birthday(localDate)
                .surname(surname)
                .name(name)
                .country(country)
                .city(city)
                .build();

        if (!request.getParameter("id").isEmpty()) {
            employeePersonalInfo.setId(Integer.parseInt(request.getParameter("id")));
        }

        return employeePersonalInfo;
    }
}
