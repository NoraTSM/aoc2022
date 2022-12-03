package org.example.one;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


public class SnacksTest {

    static String input;

    Snacks unit = new Snacks();

    String testInput = """
            1000
            2000
            3000
                                      
            4000
                                      
            5000
            6000
                                      
            7000
            8000
            9000
                                      
            10000
            """;


    @BeforeTest
    static void setup() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("src/test/java/resources/1.txt");
        input = new String(fileInputStream.readAllBytes());
    }

    @Test
    public void testGetMostCalories_testInput() {
        long mostCalories = unit.getMostCalories(testInput);
        assertThat(mostCalories).isEqualTo(24000L);
    }

    @Test
    public void testGetMostCalories_input() {
        long mostCalories = unit.getMostCalories(input);
        assertThat(mostCalories).isEqualTo(68442L);
    }

    @Test
    public void testGetTopNCalories_testInput() {
        long mostCalories = unit.getSumOfTopNCalories(testInput, 3);
        assertThat(mostCalories).isEqualTo(45000L);
    }


    @Test
    public void testGetTopNCalories_test() {
        long mostCalories = unit.getSumOfTopNCalories(input, 3);
        assertThat(mostCalories).isEqualTo(204837L);
    }


}