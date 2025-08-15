package app.service;

import app.entity.Person;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * A service class that provides statistical operations and data analysis
 * on a list of {@code Person} objects.
 */
public class PersonStatistics {
    /**
     * Finds the name of the oldest person from a list of people.
     * If there are multiple people with the same maximum age, the method then
     * considers their first and last names to determine the result.
     *
     * @param people the list of people to search for the oldest person;
     *               must not be null, may be empty
     * @return an {@code Optional} containing the full name (first name and
     *         last name concatenated and separated by a space) of the oldest person;
     *         or an empty {@code Optional} if the list is empty
     */
    public static Optional<String> findOldestName(List<Person> people) {
        return people.stream()
                .max(Comparator.comparingInt(Person::age)
                        .thenComparing(Person::firstName,Comparator.reverseOrder())
                        .thenComparing(Person::lastName,Comparator.reverseOrder()))
                .map(Person -> Person.firstName() + " " + Person.lastName());
    }

}
