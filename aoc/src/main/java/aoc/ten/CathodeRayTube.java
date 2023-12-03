package aoc.ten;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.tuple.Pair;
import org.eclipse.collections.impl.tuple.Tuples;
import org.eclipse.collections.impl.utility.ArrayIterate;

import java.util.Optional;

public class CathodeRayTube {

    public int sumOfCycles(String input, ListIterable<Integer> cycles) {

        ListIterable<Op> ops = ArrayIterate.collect(input.split("\n"), each -> op(each.split(" ")));
        MutableMap<Integer, Integer> integers = ops.collectWithIndex((op, index) -> {
            ListIterable<Op> ops1 = ops.subList(0, index);
            return Tuples.pair(ops1.collect(Op::getCycles).reduce(Integer::sum).orElse(0),
                               1 + ops1.collect(Op::getV).reduce(Integer::sum).orElse(0));
        }).toMap(Pair::getOne, Pair::getTwo);

        ListIterable<Integer> collect = cycles.collect(each -> each * integers.getOrDefault(each - 1, integers.getOrDefault(each-2, 1)));

        Optional<Integer> reduce = collect.reduce(Integer::sum);


        return reduce.orElse(0);

    }

    public int sumOfCycles(String input) {
        ImmutableList<Integer> cycles = Lists.immutable.of(20, 60, 100, 140, 180, 220);
        return sumOfCycles(input, cycles);
    }

    public Op op(String[] input) {
        if(input.length == 2) {
            return new Op(Integer.parseInt(input[1]));
        }
        return new Op();
    }





    public class Op {

        public final int v;
        public final int cycles;

        public Op(int v) {
            this.cycles = 2;
            this.v = v;
        }


        public Op() {
            this.cycles = 1;
            this.v = 0;
        }

        public int getCycles() {
            return this.cycles;
        }
        public int getV() {
            return this.v;
        }
    }
}

