package app.io;

import app.entity.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * The LoadPeople class provides functionality to read a file containing JSON-encoded
 * lines representing Person objects and convert them into a list of Person instances.
 */
public class LoadPeople {

    private final ObjectMapper mapper = JsonMapper.builder().build();

    /**
     * Loads a file containing JSON-encoded lines representing Person objects and
     * returns a list of parsed Person instances. Invalid lines are skipped and
     * logged to standard error output.
     *
     * @param file the path of the file to load
     *
     * @return a list of Person objects parsed from the given file
     * @throws IOException if an I/O exception occurs while reading the file
     */
    public List<Person> load(Path file) throws IOException {
        List<Person> people = new ArrayList<>();
        try(BufferedReader reader = Files.newBufferedReader(file)) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Person person = mapper.readValue(line, Person.class);
                    people.add(person);
                } catch (JsonProcessingException e) {
                    System.err.println("Skipped invalid JSON line: " + line);
                }
            }
            return people;
        }
    }
}
