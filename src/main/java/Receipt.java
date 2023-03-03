import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.text.NumberFormat;;

public class Receipt {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Cart cart = objectMapper.readValue(new File("./shopping-cart-features/cart.json"), Cart.class);
        Coupons coupons = objectMapper.readValue(new File("./shopping-cart-features/coupons.json"), Coupons.class);

        double total = 0;
        double taxableTotal = 0;
        double taxableSubTotal = 0;
        double discountTotal = 0;
        double subtotalAfterDiscounts = 0;
        double taxSubtotalAfterDiscounts = 0;
        double taxableTotalWithDiscounts = 0;
        Boolean isTaxable = true;
        for (CartItem item : cart.getItems()) {
            String itemSku = item.getSku();
            double itemPrice = item.getPrice();
            isTaxable = item.getIsTaxable();
            total += itemPrice;

            boolean couponApplied = false;
            for (CouponItem coupon : coupons.getCoupons()) {
                if (coupon.getAppliedSku().equals(itemSku)) {
                    double discountAmount = coupon.getDiscountPrice();
                    double itemDiscount = Math.min(itemPrice, discountAmount);
                    itemPrice -= itemDiscount;

                    discountTotal += itemDiscount;
                    couponApplied = true;
                }
            }
            if (itemPrice < 0) {
                itemPrice = 0;
            }

            if (isTaxable) {
                taxableSubTotal += itemPrice;
                taxableTotal += itemPrice * 0.0825;
            }
            if (isTaxable && couponApplied){
                taxSubtotalAfterDiscounts += itemPrice;
                taxableTotalWithDiscounts += itemPrice * 0.0825;
            }
            subtotalAfterDiscounts = total - discountTotal;

        }

        double tax = total * 0.0825;
        NumberFormat fmt = NumberFormat.getCurrencyInstance();
        double grandTotal = total + tax;
        double grandTotalWithTaxable = total + taxableTotal;
        double grandTotalWithDiscountsAndTax = subtotalAfterDiscounts + taxableTotalWithDiscounts;

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
        System.out.println("Grand Total (with Tax): " + fmt.format(grandTotalWithTaxable));
        System.out.println("Feature 4");
        System.out.println("Subtotal before discounts: " + fmt.format(total));
        System.out.println("Discount total: " + fmt.format(discountTotal));
        System.out.println("Subtotal After Discounts: " + fmt.format(subtotalAfterDiscounts));
        System.out.println("Taxable Subtotal After Discounts: " + fmt.format(taxableTotalWithDiscounts));
        System.out.println("Tax Total: " + fmt.format(taxableTotalWithDiscounts));
        System.out.println("Grand Total: " + fmt.format(grandTotalWithDiscountsAndTax));
    }
}
