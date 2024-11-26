package colections;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PersonColections {

    Person person = new Person("DimaKarpuk","male", 33);
    Person person1 = new Person("OlgaKarpuk", "wuman", 31);
    Person person2 = new Person("KsenuaKarpuk", "wuman", 11);
    Person person3 = new Person("IgnatKarpuk", "male", 6);

    List<Person> persons = new ArrayList<>();
    @Test
    public void String (){
        persons.add(person);
        persons.add(person1);
        persons.add(person2);
        persons.add(person3);
        persons.add(person3);

       // persons.stream().filter(p-> p.getAge()>10).forEach(System.out::println);
      // Person person4 = persons.stream().findFirst().get();
       // System.out.println(person4);
       // persons.stream().filter(p->p.getName().equals("Dima")).forEach(System.out::println);
        //persons.stream().map(p->p.getAge()+100).forEach(System.out::println);
       // persons.stream().filter(p->p.getAge()<12).forEach(System.out::println);
       // persons.stream().filter(p->p.getName().)
        //persons.stream().filter(x->x.getAge()>20 && x.getGender().equals("wuman")).map(x-> x.getName()).forEach(System.out::println);
    }
}
