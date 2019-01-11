//package shape;

public class Rectangle extends Shape{
    Rectangle(Double width, Double height) {
        super(2*width + 2*height, width*height, width, height);
    }
}