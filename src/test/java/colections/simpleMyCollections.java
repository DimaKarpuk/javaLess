package colections;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class simpleMyCollections {
    @Test
    public void collectionsTest() {
        List<String> name = new ArrayList<>();
        name.add("Dima");
        name.add("Olga");
        name.add("Ksenua");
        name.add("Ignat");
        System.out.println(name);
        name.remove("Dima");
        System.out.println(name);

        name.remove(2);
        System.out.println(name);
    }

    @Test
    public void simpleTest2() {
        Random random = new Random();

        int[] ar = new int[8];
        for (int i = 0; i < ar.length; i++) {
            ar[i] = random.nextInt(87);
            System.out.println(ar[i]);
        }

        int a = 0;
        for (int i = 0; i < ar.length; i++) {
            if (ar[i] > a) {
                a = ar[i];
            }
            System.out.println(ar[i]);
        }
        System.out.println(a + " Это максимальное число");


    }

    @Test
    public void intTest() {
        String str = "1, 2, 3, 4, 4, 5, 10,11";

        List<String> lu = Arrays.asList(str.split(",\\s*"));
        lu.stream().map(Integer::parseInt).forEach(System.out::println);

        // String[] numberStrings = str.split(",\\s*"); // разделяем строки по запятым и пробелом


//        int[] numb = new int[numberStrings.length];
//        for (int i = 0; i <numberStrings.length; i++){
//            numb[i]= Integer.parseInt(numberStrings[i]);
//        }
//        Arrays.stream(numb).forEach(System.out::println);
    }
}
