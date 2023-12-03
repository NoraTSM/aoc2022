package aoc;

import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.impl.utility.ArrayIterate;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MonkeyInTheMiddle {

    public void play(String input) {
        ArrayIterate.collect(input.split("\n\n"), this::parseMonkey);
    }

    public void changeWorryLevel(Monkey monkey, Item item) {
        //item.setWorryLevel(monkey.operation.apply(item.getWorryLevel()));
    }

    public int throwToMonkey(Monkey monkey, Item item) {
        return item.worryLevel % monkey.divisor == 0 ? monkey.monkeyTrue : monkey.monkeyFalse;
    }

    public Monkey parseMonkey(String input) {
        String[] split = input.split("\n");

        Pattern monkeyNumber = Pattern.compile("Monkey (\\d):");
        int monkey = Integer.parseInt(monkeyNumber.matcher(split[0]).group(1));

        Pattern itemPattern = Pattern.compile("  Starting items: (\\d+,? ?)+");
        String[] split1 = itemPattern.matcher(split[1]).group(1).split(", ");
        ArrayIterate.collect(split1, each -> new Item(Integer.parseInt(each)));


        Pattern op = Pattern.compile("  Operation: new = (\\w*)(\\d*) ([+*-]) (\\w*)(\\d*)");
        Matcher m = op.matcher(split[2]);

        String variable = m.group(1);
        String varInt = m.group(2);

        String operator = m.group(3);
        String variable2 = m.group(1);

        String varInt2 = m.group(2);
        /*Function operation = Functions.FunctionChain(variable == null ? variable : varInt,
                                                     operator,
                                                     variable2 == null ? variable2 : varInt2);*/



        Pattern pattern = Pattern.compile(new StringBuilder().append("Monkey (\\d):\n")
                                                             .append("  Starting items: ((\\d+),? ?)+\n")
                                                             .append("  Operation: ([a-z0-9]+ = [a-z0-9]+ [+*-] [a-z0-9]+)\n")
                                                             .append("  Test: divisible by (\\d+)\n")
                                                             .append("    If true: throw to monkey (\\d+)\n")
                                                             .append("    If false: throw to monkey (\\d+)\n").toString());

        return new Monkey();
    }


    public class Item {
        public static int counter = 0;
        public int number;
        public int worryLevel;

        Item(int worryLevel) {
            this.worryLevel = worryLevel;
            this.number = counter++;
        }

        public void setWorryLevel(int worryLevel) {
            this.worryLevel = worryLevel;
        }

        public int getWorryLevel() {
            return this.worryLevel;
        }

    }

    public class Monkey {

        public int number;

        public Function operation;

        public int divisor;

        public ListIterable<Item> items;

        int monkeyTrue;

        int monkeyFalse;

        public Monkey() {



        }
    }
}

