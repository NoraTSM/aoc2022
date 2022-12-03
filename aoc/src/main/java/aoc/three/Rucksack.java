package aoc.three;


import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.set.mutable.primitive.CharHashSet;
import org.eclipse.collections.impl.utility.ArrayIterate;

import static org.eclipse.collections.impl.utility.ArrayIterate.collect;
import static org.eclipse.collections.impl.utility.StringIterate.chunk;

public class Rucksack {

    public int misplacedPrioritySumTotal(String input) {

        return sum(getBagsWithPriorties(input));
    }

    public int sumOfBadgesGroupsOfN(String input, int n) {
        RichIterable<RichIterable<CharHashSet>> groups = ArrayIterate.chunk(bags(input), n).collect(each -> each.collect(this::toCharHashSet));
        return sum(collectIntersectionsAndMapToPriority(groups));

    }

    private ListIterable<MutableSet<Integer>> getBagsWithPriorties(String input) {
        RichIterable<RichIterable<CharHashSet>> collect = collect(bags(input),
                                                                 each -> chunk(each, each.length() / 2)
                                                                         .collect(this::toCharHashSet));

        return collectIntersectionsAndMapToPriority(collect.toImmutableList());
    }

    private ListIterable<MutableSet<Integer>> collectIntersectionsAndMapToPriority(RichIterable<RichIterable<CharHashSet>> groups) {
        return groups.collect(each -> each.reduce(this::intersect)
                                          .orElse(new CharHashSet().newEmpty())
                                          .collect(this::scoreAlfa)).toImmutableList();
    }

    private Integer sum(ListIterable<MutableSet<Integer>> list) {
        return list.flatCollect(each -> each).reduce(Integer::sum).orElse(0);
    }

    private CharHashSet toCharHashSet(String s) {
        return CharHashSet.newSetWith(s.toCharArray());
    }

    private CharHashSet intersect(CharHashSet current, CharHashSet previous) {
        return CharHashSet.newSet(current.intersect(previous));
    }

    private String[] bags(String input) {
        return input.split("\n");
    }

    int scoreAlfa(char a) {
        if(a >= 97 ) {
            return (a - 97) + 1;
        } else if (a <= 90 ) {
            return (a - 65) + 27;
        }
        return 0;
    }
}