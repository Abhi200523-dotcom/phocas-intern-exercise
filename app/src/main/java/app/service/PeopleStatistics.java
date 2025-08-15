package app.service;

import app.entity.Person;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

/**
 * A service class that provides statistical operations and data analysis
 * which consists of many people.
 */
public class PeopleStatistics {


    /**
     * Returns the average age of the given people as an {@link OptionalDouble}.
     *
     * If the list is empty, the result will be empty.
     *
     *
     * @param people list of people (must not be {@code null})
     * @return average age to 2 decimal point, or empty if list is empty
     */
    public static OptionalDouble findAverageAge(List<Person> people) {
        return people.stream()
                .mapToInt(Person::age)
                .average()
                .stream()
                .map(a -> Math.round(a * 100.0) / 100.0)
                .findFirst();
    }

    /**
     * Returns a map of each country to average age in that country.
     *
     * @param people list of people
     * @return map from country to average age of that country, or {@code null} if none
     */
    public static Map<String, Double> findAverageAgeByCountry(List<Person> people) {
        return people.stream()
                .collect(Collectors.groupingBy(
                        Person::country,
                        Collectors.collectingAndThen(
                                Collectors.averagingInt(Person::age),
                                        avg -> Math.round(avg * 100.0) / 100.0)
                        ));
    }


}
