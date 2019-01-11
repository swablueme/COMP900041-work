//package shape;
import java.util.*;
import java.util.stream.*;
public abstract class Shape {
    private final Double perimeter;
    private final Double area;
    private final Double[] originalInputs;

    Shape(Double perimeter, Double area, Double ... originalInputs) {
        this.perimeter = perimeter;
        this.area=area;
        this.originalInputs=originalInputs;
    }
    
    public final double perimeter () {
        return this.perimeter;     
    }
    public final double area () {
        return this.area;     
    }
    @Override
    public String toString() {
        String result = Arrays.stream(this.originalInputs)
                .map(String::valueOf)
                .collect(Collectors.joining(","));
        return this.getClass().getName()+"("+result+")";
    }

}
