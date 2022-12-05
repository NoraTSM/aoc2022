package aoc.four;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.set.ImmutableSet;
import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.utility.ArrayIterate;

public class CampCleaning {

    public int subset(String input) {
        RichIterable<RichIterable<ImmutableSet<Integer>>> collect = getRanges(input);

        return collect.count(each -> each.anySatisfy(one -> {
            ImmutableSet<Integer> other = each.detect(some -> !some.equals(one));
            return other == null || one.isSubsetOf(other) || other.isSubsetOf(one);
        }));
    }

    public int overlap(String input) {
        RichIterable<RichIterable<ImmutableSet<Integer>>> collect = getRanges(input);

        return collect.count(each -> each.anySatisfy(one -> {
            ImmutableSet<Integer> other = each.detect(some -> !some.equals(one));
            return other == null || one.containsAnyIterable(other) || other.containsAnyIterable(one);
        }));
    }

    private RichIterable<RichIterable<ImmutableSet<Integer>>> getRanges(String input) {
        String[] split = input.split("\n");
        RichIterable<RichIterable<ImmutableSet<Integer>>> collect = ArrayIterate.collect(split,
                                                                                      each -> ArrayIterate.collect(
                                                                                              each.split(","), pair -> {
                                                                                                  String[] range = pair.split("-");
                                                                                                  return Interval.fromTo(Integer.parseInt(range[0]),
                                                                                                                         Integer.parseInt(range[1])).toImmutableSet();
                                                                                              }));
        return collect;
    }


}
