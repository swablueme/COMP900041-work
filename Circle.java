//package shape;
import java.util.*;

public class Circle extends Shape {
    Circle(Double Radius) {
        super(Math.PI*Radius*2, Math.PI*Radius*Radius, Radius);
    }

}