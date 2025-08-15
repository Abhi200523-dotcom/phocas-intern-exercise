package unit.io;

import app.entity.Person;
import app.io.LoadPeople;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LoadPeopleIoTest {

    @Test
    public void load_validAndInvalidJson_returnsOnlyValidPeople_andLogsError() throws IOException {
        Path tempFile = Files.createTempFile("people", ".json");

        String valid1 = "{\"firstName\":\"John\",\"lastName\":\"Doe\",\"country\":\"USA\",\"age\":30}";
        String valid2 = "{\"firstName\":\"Jane\",\"lastName\":\"Smith\",\"country\":\"Canada\",\"age\":25}";
        String invalid = "{\"firstName\":\"Bad JSON\"";

        Files.writeString(tempFile, valid1 + "\n" + valid2 + "\n" + invalid);

        java.io.ByteArrayOutputStream errContent = new java.io.ByteArrayOutputStream();
        java.io.PrintStream originalErr = System.err;
        System.setErr(new java.io.PrintStream(errContent));

        try {
            LoadPeople loader = new LoadPeople();
            List<Person> people = loader.load(tempFile);

            assertEquals(2, people.size());
            assertEquals("John", people.getFirst().firstName());
            assertEquals("Doe", people.getFirst().lastName());
            assertEquals("USA", people.get(0).country());
            assertEquals(30, people.get(0).age());

            assertEquals("Jane", people.get(1).firstName());
            assertEquals("Smith", people.get(1).lastName());
            assertEquals("Canada", people.get(1).country());
            assertEquals(25, people.get(1).age());

            String errorOutput = errContent.toString();
            assertTrue(errorOutput.contains("Skipped invalid JSON line"));
        } finally {
            System.setErr(originalErr);
        }
    }

}
