package aoc.three;


import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.bag.mutable.primitive.CharHashBag;

import static org.eclipse.collections.impl.utility.ArrayIterate.collect;
import static org.eclipse.collections.impl.utility.StringIterate.chunk;

public class Rucksack {

    public int misplacedPriority(String input) {

        String[] split = input.split("\n");
        ListIterable<MutableList<CharHashBag>> collect = collect(split,
                                                                 each -> chunk(each, each.length() / 2)
                                                                         .collect(room -> CharHashBag.newBagWith(room.toCharArray())));
        ListIterable<MutableSet<Integer>> collect1 = collect.collect(each -> each.get(0).select(element -> each.get(1).contains(element)).toSet().collect(this::scoreAlfa));

        return collect1.flatCollect(each -> each).reduce(Integer::sum).orElse(0);
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
