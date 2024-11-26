package colections;


public class CarObject {

    String marca;
    String color;
    int mass;

    public CarObject(String marca, int mass, String color) {
        this.marca = marca;
        this.mass = mass;
        this.color = color;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getMass() {
        return mass;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }
}
