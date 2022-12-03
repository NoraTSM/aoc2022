package aoc.two;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class RochambeauTest {
    static String input;

    Rochambeau unit = new Rochambeau();

    String testInput = """
            A Y
            B X
            C Z
            """;


    @BeforeTest
    static void setup() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("src/test/resources/2.txt");
        input = new String(fileInputStream.readAllBytes());
    }

    @Test
    public void testCalculateScore_testInput() {
        long result = unit.calculateScore(testInput);
        assertThat(result).isEqualTo(15);
    }

    @Test
    public void testCalculateScore_input() {
        long result = unit.calculateScore(input);
        assertThat(result).isEqualTo(14375);
    }

    @Test
    public void testCalculateScoreTwo_testInput() {
        long result = unit.calculateScoreTwo(testInput);
        assertThat(result).isEqualTo(12);
    }

    @Test
    public void testCalculateScoreTwo_input() {
        long result = unit.calculateScoreTwo(input);
        assertThat(result).isEqualTo(10274);
    }


}