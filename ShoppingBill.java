
public class ShoppingBill {

    //Electronics + 18% GST
    public static double calculateElectronicsGST(double a, double b) {
        double amount = a + b;
        return amount + (amount * 18 / 100);
    }

    //Fashion + 13% GST
    public static double calculateFashionGST(double c, double d) {
        double amount = c + d;
        return amount + (amount * 13 / 100);
    }

    //Food + 7% GST
    public static double calculateFoodGST(double e) {
        return e + (e * 7 / 100);
    }

    //Total amount after GST
    public static double totalBill(double a, double b, double c,
                                   double d, double e) {
        double electronics = calculateElectronicsGST(a, b);
        double fashion = calculateFashionGST(c, d);
        double food = calculateFoodGST(e);

        return electronics + fashion + food;
    }

    // Calculate the 20% discount
    public static double calculateDiscount(double total) {
        return total * 20 / 100;
    }

    // Final payable amount
    public static double calculateDiscountedBill(double a, double b,
                                                  double c, double d,
                                                  double e) {
        double total = totalBill(a, b, c, d, e);
        double discount = calculateDiscount(total);

        return total - discount;
    }

    public static void main(String[] args) {

        // Sample values supplied directly as arguments
        double a = 100, b = 100, c = 100, d = 100, e = 100;

        double electronics = calculateElectronicsGST(a, b);
        double fashion = calculateFashionGST(c, d);
        double food = calculateFoodGST(e);

        double total = totalBill(a, b, c, d, e);
        double discount = calculateDiscount(total);
        double payable = calculateDiscountedBill(a, b, c, d, e);

        System.out.printf("Electronics after GST: Rs. %.2f%n", electronics);
        System.out.printf("Fashion after GST: Rs. %.2f%n", fashion);
        System.out.printf("Food after GST: Rs. %.2f%n", food);
        System.out.printf("Total after GST: Rs. %.2f%n", total);
        System.out.printf("Discount: Rs. %.2f%n", discount);
        System.out.printf("Final payable amount: Rs. %.2f%n", payable);
    }
}
