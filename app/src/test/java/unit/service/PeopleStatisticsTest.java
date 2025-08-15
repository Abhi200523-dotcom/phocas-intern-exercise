package unit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import app.entity.Person;
import app.service.PeopleStatistics;
import app.service.PersonStatistics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.TreeMap;
import org.junit.jupiter.api.Test;

public class PeopleStatisticsTest {
    @Test
    public void findAverageAge_validData_returnsAverageAge() {
        List<Person> people = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            people.add(new Person("John" + i, "Doe" + i, "USA", 25 + i));
        }
        OptionalDouble age = PeopleStatistics.findAverageAge(people);
        assertEquals(27.0, age.getAsDouble(), 0.001);
    }

    @Test
    public void findAverageAge_emptyList_returnsEmptyOptional() {
        List<Person> people = Collections.emptyList();
        OptionalDouble age = PeopleStatistics.findAverageAge(people);
        assertFalse(age.isPresent());
    }

    @Test
    public void findAverageAgeByCountry_validData_returnsAverageAgeByCountry() {
        List<Person> people = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            people.add(new Person("John" + i, "Doe" + i, "USA", 20 + i));
        }
        for(int i = 0; i < 5; i++) {
            people.add(new Person("Jane" + i, "Doe" + i, "China", 20 + i));
        }
        for(int i = 0; i < 5; i++) {
            people.add(new Person("Daniel" + i, "Doe" + i, "Nepal", 20 + i));
        }

        Map<String, Double> actual = PeopleStatistics.findAverageAgeByCountry(people);

        Map<String, Double> expected = Map.of(
                "USA",   22.0,
                "China", 22.0,
                "Nepal", 22.0
        );

        assertEquals(expected.size(), actual.size());

        expected.forEach((country, age) ->
                assertEquals(age, actual.get(country))
        );
    }
    @Test
    public void findAgeByByCountry_emptyList_returnsEmptyMap() {
        Map<String, Double> actual = PeopleStatistics.findAverageAgeByCountry(Collections.emptyList());
        assertTrue(actual.isEmpty());
    }


    @Test
    public void findCountOfPeopleInAgeGroupInNewZealand_emptyList_returnsEmptyMap() {
        List<Person> people = new ArrayList<>();
        for(int i = 0; i < 7; i++) {
            people.add(new Person("John" + i, "Doe" + i, "New Zealand", 20 + i));
        }
        for(int i = 0; i < 5; i++) {
            people.add(new Person("Jane" + i, "Doe" + i, "New Zealand", 10 + i));
        }
        for(int i = 0; i < 5; i++) {
            people.add(new Person("Daniel" + i, "Doe" + i, "Nepal", 20 + i));
        }

        Map<String, Long> actual = PeopleStatistics.findAgeGroupForNewZealand(people);

        Map<String, Long> expected = Map.of(
                "10-19",   5L,
                "20-29", 7L
        );

        assertEquals(expected.size(), actual.size());

        expected.forEach((ageGroup, count) ->
                assertEquals(count, actual.get(ageGroup))
        );
    }

    @Test
    public void findCountOfPeopleInAgeGroupInNewZealand_validData_returnsCountOfPeopleInAgeGroupInNewZealand() {
        TreeMap<String, Long> actual = PeopleStatistics.findAgeGroupForNewZealand(Collections.emptyList());
        assertTrue(actual.isEmpty());
    }
}
