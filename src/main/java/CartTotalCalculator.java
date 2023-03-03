import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.text.NumberFormat;;

public class CartTotalCalculator {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Cart cart = objectMapper.readValue(new File("./shopping-cart-features/cart.json"), Cart.class);

        double total = 0;
        double taxableTotal = 0;
        double taxableSubTotal = 0;
        Boolean isTaxable = true;
        for (CartItem item : cart.getItems()) {
            isTaxable = item.getIsTaxable();
            total += item.getPrice();
            if (isTaxable){
                taxableSubTotal += item.getPrice();
                taxableTotal += item.getPrice() * 0.0825;
            }
        }

        double tax = total * 0.0825;
        NumberFormat fmt = NumberFormat.getCurrencyInstance();
        double grandTotal = total + tax;
        double grandTotalWithTaxable = total + taxableTotal;

        System.out.println("Feature 1");
        System.out.println("Grand Total: " + fmt.format(total));
        System.out.println("Feature 2");
        System.out.println("Subtotal: " + fmt.format(total));
        System.out.println("Tax Total: " + fmt.format(tax));
        System.out.println("Grand Total (with Tax): " + fmt.format(grandTotal));
        System.out.println("Feature 3");
        System.out.println("Subtotal: " + fmt.format(total));
        System.out.println("Taxable Subtotal: " + fmt.format(taxableSubTotal));
        System.out.println("Tax Total: " + fmt.format(taxableTotal));
        System.out.println("Grand Total (with Tax): $" + fmt.format(grandTotalWithTaxable));
        System.out.println("Feature 4");
        System.out.println("Subtotal before discounts: " + fmt.format(total));
        System.out.println("Discount total: " + fmt.format(total));
    }
}
