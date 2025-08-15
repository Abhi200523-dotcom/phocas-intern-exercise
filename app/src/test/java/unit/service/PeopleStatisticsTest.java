package unit.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import app.entity.Person;
import app.service.PeopleStatistics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.OptionalDouble;
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
}
