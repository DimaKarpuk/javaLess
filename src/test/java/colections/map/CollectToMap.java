package colections.map;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class CollectToMap {
    List<String> products = Arrays.asList("TV:1000", "Phone:800", "Headphones:400");

    @Test
    public void collectToMapTest(){

        Map<String,Integer> teh = new HashMap<>();

        teh = products.stream().map(x->{
            String [] n = x.split(":");
            return new AbstractMap.SimpleEntry<>(n[0],Integer.parseInt(n[1]));
        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

       // teh.entrySet().stream().forEach(System.out::println);

       // teh.entrySet().stream().filter(x->x.getValue()>500).forEach(System.out::println);
        teh.entrySet().stream().map(x->x.getKey()).forEach(System.out::println);
        teh.entrySet().stream().map(x->x.getKey()).forEach(System.out::println);
    }
}
