package aoc.four;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class CampCleaningTest {
    static String input;


    String testInput = """
            2-4,6-8
            2-3,4-5
            5-7,7-9
            2-8,3-7
            6-6,4-6
            2-6,4-8
            """;


    CampCleaning unit = new CampCleaning();

    @BeforeTest
    static void setup() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("src/test/resources/4.txt");
        input = new String(fileInputStream.readAllBytes());
    }

    @Test
    public void testSubset_testInput() {

        long result = unit.subset(testInput);
        assertThat(result).isEqualTo(2);
    }

    @Test
    public void testSubset_input() {

        long result = unit.subset(input);
        assertThat(result).isEqualTo(423);
    }

    @Test
    public void testOverlap_testInput() {

        long result = unit.overlap(testInput);
        assertThat(result).isEqualTo(4);
    }

    @Test
    public void testOverlap_input() {

        long result = unit.overlap(input);
        assertThat(result).isEqualTo(861);
    }
}