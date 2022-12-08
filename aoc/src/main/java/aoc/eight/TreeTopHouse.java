package aoc.eight;

import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.utility.ArrayIterate;

public class TreeTopHouse {

    public int possibleLocations(String input) {
        ListIterable<MutableList<Integer>> correct = matrix(input);

        ListIterable<ListIterable<Boolean>> north = transposeBool(transposeInt(correct).collect(this::visible));

        ListIterable<ListIterable<Boolean>> south = transposeBool(transposeInt(correct).collect(MutableList::toReversed)
                                                                                       .collect(this::visible)
                                                                                       .collect(ListIterable::toReversed));

        ListIterable<ListIterable<Boolean>> west = correct.collect(this::visible);

        ListIterable<ListIterable<Boolean>> east = correct.collect(MutableList::toReversed)
                                                          .collect(this::visible)
                                                          .collect(ListIterable::toReversed);


        ListIterable<ListIterable<Boolean>> latitude = union(north, south);
        ListIterable<ListIterable<Boolean>> longitude = union(west, east);


        ListIterable<Boolean> booleans = union(latitude, longitude).flatCollect(each -> each.select(Boolean::booleanValue));
        return booleans.size();
    }

    public MutableList<MutableList<Integer>> matrix(String input) {
        return ArrayIterate.collect(input.split("\n"), each -> each.split("")).
                collect(each -> ArrayIterate.collect(each, Integer::parseInt));
    }

    private ListIterable<ListIterable<Boolean>> union(ListIterable<ListIterable<Boolean>> first, ListIterable<ListIterable<Boolean>> opposite) {
        return first.collectWithIndex((list, i) -> list.collectWithIndex((element, j) -> element || opposite.get(i).get(j)));
    }

    public ListIterable<MutableList<Integer>> transposeInt(ListIterable<MutableList<Integer>> matrix) {
        return matrix.collectWithIndex((list, i) -> list.collectWithIndex((element, j) -> matrix.get(j).get(i)));
    }
    public ListIterable<ListIterable<Boolean>> transposeBool(ListIterable<ListIterable<Boolean>> matrix) {
        return matrix.collectWithIndex((list, i) -> list.collectWithIndex((element, j) -> matrix.get(j).get(i)));
    }

    public ListIterable<Boolean> visible(MutableList<Integer> ints) {
        return ints.collectWithIndex((value, j) -> j == 0 || value > ints.subList(0, j).max());
    }
}
