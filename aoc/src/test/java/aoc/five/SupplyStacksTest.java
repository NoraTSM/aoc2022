package aoc.five;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class SupplyStacksTest {

    static String input;

    String testInput = """
                [D]   \s
            [N] [C]   \s
            [Z] [M] [P]
             1   2   3\s
                        
            move 1 from 2 to 1
            move 3 from 1 to 3
            move 2 from 2 to 1
            move 1 from 1 to 2
            """;

    SupplyStacks unit = new SupplyStacks();

    @BeforeTest
    static void setup() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("src/test/resources/4.txt");
        input = new String(fileInputStream.readAllBytes());
    }

    @Test
    public void testFinalTopStack_testInput() {

        String result = unit.finalTopStack(testInput);
        assertThat(result).isEqualTo("CMZ");
    }

    @Test
    public void testFinalTopStack_input() {

        String result = unit.finalTopStack(input);
        assertThat(result).isEqualTo("CMZ");
    }




}