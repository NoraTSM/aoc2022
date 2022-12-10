package aoc.eight;

import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.impl.utility.ArrayIterate;

import java.util.Objects;

public class TreeTopHouse {

    public int possibleLocations(String input) {
        ListIterable<ListIterable<Integer>> correct = matrix(input);

        ListIterable<ListIterable<Boolean>> north = transposeBool(transposeInt(correct).collect(this::visible));

        ListIterable<ListIterable<Boolean>> south = transposeBool(transposeInt(correct).collect(ListIterable::toReversed)
                                                                                       .collect(this::visible)
                                                                                       .collect(ListIterable::toReversed));

        ListIterable<ListIterable<Boolean>> west = correct.collect(this::visible);

        ListIterable<ListIterable<Boolean>> east = correct.collect(ListIterable::toReversed)
                                                          .collect(this::visible)
                                                          .collect(ListIterable::toReversed);


        ListIterable<ListIterable<Boolean>> latitude = union(north, south);
        ListIterable<ListIterable<Boolean>> longitude = union(west, east);


        ListIterable<Boolean> booleans = union(latitude, longitude).flatCollect(each -> each.select(Boolean::booleanValue));
        return booleans.size();
    }

    public int scenicLocations(String input) {
        ListIterable<ListIterable<Integer>> correct = matrix(input);

        ListIterable<ListIterable<Integer>> north = transposeInt(transposeInt(correct).collect(this::scenic));

        ListIterable<ListIterable<Integer>> south = transposeInt(transposeInt(correct).collect(ListIterable::toReversed)
                                                                           .collect(this::scenic)
                                                                           .collect(ListIterable::toReversed));

        ListIterable<ListIterable<Integer>> west = correct.collect(this::scenic);

        ListIterable<ListIterable<Integer>> east = correct.collect(ListIterable::toReversed)
                                                          .collect(this::scenic)
                                                          .collect(ListIterable::toReversed);


        ListIterable<ListIterable<Integer>> latitude = product(north, south);
        ListIterable<ListIterable<Integer>> longitude = product(west, east);


        ListIterable<ListIterable<Integer>> scores = product(latitude, longitude);
        return scores.flatCollect(each -> each).max();
    }

    public ListIterable<ListIterable<Integer>> matrix(String input) {
        return ArrayIterate.collect(input.split("\n"), each -> each.split("")).
                collect(each -> ArrayIterate.collect(each, Integer::parseInt));
    }

    private ListIterable<ListIterable<Boolean>> union(ListIterable<ListIterable<Boolean>> first, ListIterable<ListIterable<Boolean>> opposite) {
        return first.collectWithIndex((list, i) -> list.collectWithIndex((element, j) -> element || opposite.get(i).get(j)));
    }

    public ListIterable<ListIterable<Integer>> product(ListIterable<ListIterable<Integer>> first, ListIterable<ListIterable<Integer>> opposite) {
        return first.collectWithIndex((list, i) -> list.collectWithIndex((element, j) -> element * opposite.get(i).get(j)));
    }

    public ListIterable<ListIterable<Integer>> transposeInt(ListIterable<ListIterable<Integer>> matrix) {
        return matrix.collectWithIndex((list, i) -> list.collectWithIndex((element, j) -> matrix.get(j).get(i)));
    }
    public ListIterable<ListIterable<Boolean>> transposeBool(ListIterable<ListIterable<Boolean>> matrix) {
        return matrix.collectWithIndex((list, i) -> list.collectWithIndex((element, j) -> matrix.get(j).get(i)));
    }

    public ListIterable<Boolean> visible(ListIterable<Integer> ints) {
        return ints.collectWithIndex((value, j) -> j == 0 || value > ints.subList(0, j).max());
    }

    public int blocked(ListIterable<Integer> ints) {
        int vantagePoint = ints.getFirst();
        ListIterable<Integer> integers = ints.collectWithIndex((value, j) -> {
             if (j != 0 && value >= vantagePoint) {
                return j;
            } else if (j > 1  && value < ints.subList(1, j).max()) {
                return j - 1;
            }
            return null;
        });

        return integers.detectOptional(Objects::nonNull).orElse(ints.size() - 1);
    }

    public ListIterable<Integer> scenic(ListIterable<Integer> ints) {
        return ints.collectWithIndex((value, j) ->  blocked(ints.subList(j, ints.size())));
    }

}
