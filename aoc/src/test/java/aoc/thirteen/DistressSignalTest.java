package aoc.thirteen;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DistressSignalTest {

    private static String input;

    String testInput = """
            [1,1,3,1,1]
            [1,1,5,1,1]
                        
            [[1],[2,3,4]]
            [[1],4]
                        
            [9]
            [[8,7,6]]
                        
            [[4,4],4,4]
            [[4,4],4,4,4]
                        
            [7,7,7,7]
            [7,7,7]
                        
            []
            [3]
                        
            [[[]]]
            [[]]
                        
            [1,[2,[3,[4,[5,6,7]]]],8,9]
            [1,[2,[3,[4,[5,6,0]]]],8,9]
            """;



    DistressSignal unit = new DistressSignal();

    @BeforeTest
    static void setup() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("src/test/resources/10.txt");
        input = new String(fileInputStream.readAllBytes());
    }

    @Test
    public void testSumOfCycles_testInput() {
        assertThat(unit.processLists(testInput)).isEqualTo(13140);
    }


}