package aoc.three;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class RucksackTest {

    static String input;


    String testInput = """
            vJrwpWtwJgWrhcsFMMfFFhFp
            jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
            PmmdzqPrVvPwwTWBwg
            wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
            ttgJtRGJQctTZtZT
            CrZsJsPPZsGzwwsLwLmpwMDw
            """;

    Rucksack unit = new Rucksack();

    @BeforeTest
    static void setup() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("src/test/resources/3.txt");
        input = new String(fileInputStream.readAllBytes());
    }

    @Test
    public void testCalculateScore_testInput() {
        long result = unit.sumOfMisplacedItems(testInput);
        assertThat(result).isEqualTo(157);
    }

    @Test
    public void testCalculateScore_input() {
        long result = unit.sumOfMisplacedItems(input);
        assertThat(result).isEqualTo(8085);
    }


    @Test
    public void testSumOfBadgesGroupsOfN_testInput() {
        long result = unit.sumOfBadgesInGroupsOfN(testInput, 3);
        assertThat(result).isEqualTo(70);
    }

    @Test
    public void testSumOfBadgesGroupsOfN_input() {
        long result = unit.sumOfBadgesInGroupsOfN(input, 3);
        assertThat(result).isEqualTo(2515);
    }


}