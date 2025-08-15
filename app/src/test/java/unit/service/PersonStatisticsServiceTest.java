package unit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import app.entity.Person;
import app.service.PersonStatistics;
import java.util.ArrayList;
import java.util.List;
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
}
