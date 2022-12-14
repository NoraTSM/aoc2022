package aoc.one;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.utility.ArrayIterate;

import java.util.Comparator;

public  class Snacks {
    public long getMostCalories(String input) {
        return sumCaloriesPerElf(getElves(input)).max();
    }

    public long getSumOfTopNCalories(String input, int n) {
        return sumCaloriesPerElf(getElves(input)).sortThis(Comparator.reverseOrder())
                                                 .take(n)
                                                 .sumOfLong(Long::longValue);
    }

    private MutableList<Long> sumCaloriesPerElf(String[] elves) {
        return ArrayIterate.collect(elves, Snacks::parseSnackPack)
                           .collect(each -> each.sumOfInt(Integer::intValue));
    }

    public String[] getElves(String input) {
        return input.split("\n\n");
    }

    private static MutableList<Integer> parseSnackPack(String each) {
        return ArrayIterate.collect(each.split("\n"),
                                    Integer::parseInt);
    }
}