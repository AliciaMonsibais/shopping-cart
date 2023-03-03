import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItem {
    private String itemName;
    private double price;
    private int sku;
    private Boolean isTaxable;
    private String ownBrand;

//    public String getName() {
//        return itemName;
//    }
//
//    public void setName(String itemName) {
//        this.itemName = itemName;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }
}
