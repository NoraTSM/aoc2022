package aoc.three;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

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
        long result = unit.misplacedPrioritySumTotal(testInput);
        assertThat(result).isEqualTo(157);
    }

    @Test
    public void testCalculateScore_input() {
        long result = unit.misplacedPrioritySumTotal(input);
        assertThat(result).isEqualTo(8085);
    }


    @Test
    public void testSumOfBadgesGroupsOfN_testInput() {
        long result = unit.sumOfBadgesGroupsOfN(testInput, 3);
        assertThat(result).isEqualTo(70);
    }

    @Test
    public void testSumOfBadgesGroupsOfN_input() {
        long result = unit.sumOfBadgesGroupsOfN(input, 3);
        assertThat(result).isEqualTo(2515);
    }


}