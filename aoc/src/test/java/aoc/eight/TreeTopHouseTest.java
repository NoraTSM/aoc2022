package aoc.eight;

import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.api.list.MutableList;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class TreeTopHouseTest {

    static String input;

    String testInput = """
            30373
            25512
            65332
            33549
            35390
            """;

    TreeTopHouse unit = new TreeTopHouse();


    @BeforeTest
    static void setup() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("src/test/resources/8.txt");
        input = new String(fileInputStream.readAllBytes());
    }


    @Test
    public void testPossibleLocations_testInput() {
        int result = unit.possibleLocations(testInput);
        assertThat(result).isEqualTo(21);
    }


    @Test
    public void testPossibleLocations_input() {
        int result = unit.possibleLocations(input);
        assertThat(result).isEqualTo(21);
    }

    @Test
    public void testTranspose() {
        ListIterable<MutableList<Integer>> matrix = unit.matrix(testInput);
        ListIterable<MutableList<Integer>> mutableLists = unit.transposeInt(matrix);
        assertThat(unit.transposeInt(mutableLists)).isEqualTo(matrix);

    }
}