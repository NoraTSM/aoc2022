package aoc.six;

import org.eclipse.collections.impl.list.Interval;
import org.eclipse.collections.impl.string.immutable.CharAdapter;
import org.eclipse.collections.impl.utility.StringIterate;

public class TuningTrouble {



    public int packetIndex(String input) {
        return processedCharsBeforeUniqueGroupsOfN(input, 4);
    }

    public int messageIndex(String input) {
        return processedCharsBeforeUniqueGroupsOfN(input, 14);
    }


    private int processedCharsBeforeUniqueGroupsOfN(String input, int n) {
        if (input.length() < n) {
            return input.length();
        }
        CharAdapter charAdapter = StringIterate.asCharAdapter(input);
        return n + Interval.fromTo(0,input.length() - n)
                           .detectOptional(i -> StringIterate.asCharAdapter(charAdapter.subSequence(i, i + n))
                                                     .toSet().size() == n)
                           .orElse(input.length() - n);
    }

}
