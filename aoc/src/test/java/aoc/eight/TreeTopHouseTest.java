package aoc.eight;

import org.eclipse.collections.api.list.ListIterable;
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

    String testOnes = """
            111
            111
            111
            """;

    String testZeros= """
            000
            000
            000
            """;

    String testMinigrid = """
            210
            210
            210
            """;

    String testReverse = """
            012
            012
            012
            """;

    String testProduct = """
            212
            212
            212
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
        assertThat(result).isEqualTo(1669);
    }

    @Test
    public void testTranspose() {
        ListIterable<ListIterable<Integer>> matrix = unit.matrix(testInput);
        ListIterable<ListIterable<Integer>> mutableLists = unit.transposeInt(matrix);
        assertThat(unit.transposeInt(mutableLists)).isEqualTo(matrix);

    }

    @Test
    public void testScenicLocations_testInput() {
        int result = unit.scenicLocations(testInput);
        assertThat(result).isEqualTo(8);
    }

    @Test
    public void testScenicLocationstest_input() {
        int result = unit.scenicLocations(input);
        assertThat(result).isEqualTo(200); //Too low 2058 earlier
    }

}