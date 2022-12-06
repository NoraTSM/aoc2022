package aoc.six;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class TuningTroubleTest {

    static String input;

    String testInput = "mjqjpqmgbljsphdztnvjfqwrcgsmlb";

    String testInput_none = "ttttt";

    TuningTrouble unit = new TuningTrouble();

    @BeforeTest
    static void setup() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("src/test/resources/6.txt");
        input = new String(fileInputStream.readAllBytes());
    }

    @Test
    public void testPacketIndex_testInput() {
        int result = unit.packetIndex(testInput);
        assertThat(result).isEqualTo(7);

    }

    @Test
    public void testPacketIndex_testNone() {
        int result = unit.packetIndex(testInput_none);
        assertThat(result).isEqualTo(testInput_none.length());

    }

    @Test
    public void testPacketIndex_input() {
        int result = unit.packetIndex(input);
        assertThat(result).isEqualTo(1034);

    }

    @Test
    public void testMessageIndex_testInput() {
        int result = unit.messageIndex(testInput);
        assertThat(result).isEqualTo(19);

    }

    @Test
    public void testMessageIndex_testNone() {
        int result = unit.messageIndex(testInput_none);
        assertThat(result).isEqualTo(testInput_none.length());

    }

    @Test
    public void testMessageIndex_input() {
        int result = unit.messageIndex(input);
        assertThat(result).isEqualTo(2472);

    }
}