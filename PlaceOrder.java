import java.util.*;
import java.util.Scanner;


class PlaceOrder {
    public static void main(String[] args) {
        System.out.print("Quantity: ");
        Scanner in=new Scanner(System.in);
        int quantity=Integer.parseInt(in.nextLine());
        System.out.print("Description: ");
        String description=in.nextLine();
        System.out.print("Unit price: ");
        double unitPrice=Double.parseDouble(in.nextLine());
        System.out.printf("Order for %d %s\n", quantity, description.toUpperCase());
        System.out.printf("Total price %14.2f\n", quantity*unitPrice);  
    }
}