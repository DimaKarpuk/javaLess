package colections.map;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class TeatMap {

    Map<String,Integer> baseHuman = new HashMap<>();

    @Test
    public void setMapTest(){
        baseHuman.putIfAbsent("Dima",33);
        baseHuman.putIfAbsent("Olga", 31);
        baseHuman.putIfAbsent("Ksenia",11);
        baseHuman.putIfAbsent("Ignat",6);

       //int a = baseHuman.get("Dima"); // get value
      // System.out.println(a);

      // int size = baseHuman.size(); // get size map
      //  System.out.println(size);

        //Set<String> keys = baseHuman.keySet();
       // keys.stream().forEach(System.out::println);

       // baseHuman.entrySet().forEach(x->System.out.println(x.getKey() + x.getValue()));

       // baseHuman.entrySet().stream().filter(x->x.getValue()>20).map(x->x.getKey()).forEach(System.out::println);

      //  Map<String,Integer> oldHuman = baseHuman.entrySet().stream().filter(x->x.getValue()>18)
            //    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        //oldHuman.entrySet().forEach(System.out::println);

    }
}
