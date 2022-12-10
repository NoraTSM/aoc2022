package aoc.five;

import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.utility.ArrayIterate;
import org.eclipse.collections.impl.utility.StringIterate;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SupplyStacks {




    public String finalTopStack(String input) {
        String[] split = input.split("\n\n");
        ListIterable<String> collect = ArrayIterate.collect(split[0].split("\n"), each -> each);

        ListIterable<MutableList<String>> collect1 = collect.toReversed().drop(1).toReversed().collect(each -> StringIterate.chunk(each, 4));

        MutableSet<Integer> integers = Interval.fromTo(0, collect1.size() - 1).toSet();


        collect.groupBy(each -> each);


       // MutableStack<MutableListMultimap<Integer, Pair<String, Integer>>> mutableListMultimaps = collect1.groupBy(each-> each.groupBy(each1 -> each1.getTwo()));

        ListIterable<Instruction> instructions = ArrayIterate.collect(split[1].split("\n"), each -> {
                                                                     Matcher matcher = instructionPattern().matcher(input);
                                                                     if (matcher.matches()) {
                                                                         return new Instruction(matcher);
                                                                     }
                                                                     return null;
                                                                 }
                                                                ).select(Objects::nonNull);

        return "";
    }

    private Pattern instructionPattern() {
        Pattern pattern = Pattern.compile("move (\\d+) from (\\d+) to (\\d+)");
        return pattern;
    }

    private class Instruction {
        int moves; int src; int dest;
        public Instruction(Matcher matcher) {
            this.moves = Integer.parseInt(matcher.group(1));
            this.src = Integer.parseInt(matcher.group(2)) - 1;
            this.dest = Integer.parseInt(matcher.group(3)) -1;

        }
    }
}
