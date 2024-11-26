package colections;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;


public class CarTest {

    List<CarObject> cars = new LinkedList();

    CarObject carObject = new CarObject("volvo", 200, "green");
    CarObject carObject2 = new CarObject("maz", 400, "blue");
    CarObject carObject3 = new CarObject("lada", 300, "black");
    CarObject carObject4 = new CarObject("niva", 600, "green");

    @Test
    public void cars() {
        cars.add(carObject);
        cars.add(carObject2);
        cars.add(carObject3);
        cars.add(carObject4);

        cars.stream().filter(x->x.getMass()>200 && x.getColor().equals("green")).map(x->x.getMarca()).forEach(System.out::println);
    }
}
