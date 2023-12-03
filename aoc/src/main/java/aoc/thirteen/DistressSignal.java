package aoc.thirteen;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.eclipse.collections.api.list.ListIterable;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.utility.ArrayIterate;

public class DistressSignal {


    public int processLists(String input) {
        Gson gson = new Gson();
        String[] split = input.split("\n\n");
        ListIterable<MutableList<JsonArray>> collect = ArrayIterate.collect(split,
                                                                             each -> ArrayIterate.collect(each.split("\n"),
                                                                                                          line -> gson.fromJson(line, JsonArray.class)));



        collect.collect(each -> each.reduce((one, two) -> one.size() > two.size() ||
               one.iterator().));
        return 1;

    }


    public boolean accepted(Object right, Object left) {
        return
    }

}
