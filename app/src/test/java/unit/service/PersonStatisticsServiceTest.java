package unit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import app.entity.Person;
import app.service.PersonStatistics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Test;

public class PersonStatisticsServiceTest {



    @Test
    public void findOldestName_uniqueAges_returnsOldestName() {
        List<Person> people = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            people.add(new Person("John" + i, "Doe" + i, "USA", 20 + i));
        }
        Optional<String> person = PersonStatistics.findOldestName(people);
        assert person.isPresent();
        assertEquals("John9 Doe9", person.get());
    }

    @Test
    public void findOldestName_nonUniqueAgesSameLastName_returnsOldestName() {
        List<Person> people = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            people.add(new Person("John" + i, "Doe" + i, "USA", 20 + i));
        }
        for(int i = 0; i < 5; i++) {
            people.add(new Person("Jane" + i, "Doe" + i, "USA", 20 + i));
        }
        Optional<String> person = PersonStatistics.findOldestName(people);
        assert person.isPresent();
        assertEquals("Jane4 Doe4", person.get());
    }

    @Test
    public void findOldestName_nonUniqueAgesSameFirstName_returnsOldestName() {
        List<Person> people = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            people.add(new Person("John" + i, "Doe" + i, "USA", 20 + i));
        }
        for(int i = 0; i < 5; i++) {
            people.add(new Person("John" + i, "Dae" + i, "USA", 20 + i));
        }
        Optional<String> person = PersonStatistics.findOldestName(people);
        assert person.isPresent();
        assertEquals("John4 Dae4", person.get());
    }

    @Test
    public void findYoungestNameByCountry_validData_returnsYoungestNameByCountry() {
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

        Map<String, String> actual = PersonStatistics.findYoungestNameByCountry(people);

        Map<String, String> expected = Map.of(
                "USA",   "John0 Doe0",
                "China", "Jane0 Doe0",
                "Nepal", "Daniel0 Doe0"
        );

        assertEquals(expected.size(), actual.size());

        expected.forEach((country, name) ->
                assertEquals(name, actual.get(country))
        );
    }

    @Test
    public void findYoungestNameByCountry_emptyList_returnsEmptyMap() {
        Map<String, String> actual = PersonStatistics.findYoungestNameByCountry(Collections.emptyList());
        assertTrue(actual.isEmpty());
    }
}
