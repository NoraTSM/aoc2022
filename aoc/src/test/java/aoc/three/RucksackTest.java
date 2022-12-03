package aoc.three;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.*;

public class RucksackTest {

    static String input;

    Rucksack unit = new Rucksack();

    String testInput = """
            vJrwpWtwJgWrhcsFMMfFFhFp
            jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
            PmmdzqPrVvPwwTWBwg
            wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
            ttgJtRGJQctTZtZT
            CrZsJsPPZsGzwwsLwLmpwMDw
            """;

    @BeforeTest
    static void setup() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("src/test/resources/3.txt");
        input = new String(fileInputStream.readAllBytes());
    }

    @Test
    public void testCalculateScore_testInput() {
        long result = unit.misplacedPriority(testInput);
        assertThat(result).isEqualTo(157);
    }

    @Test
    public void testCalculateScore_input() {
        long result = unit.misplacedPriority(input);
        assertThat(result).isEqualTo(8085);
    }


}