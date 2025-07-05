import java.util.List;

class ShippingService {
    public void ship(List<ShippableItem> items) {
        for (ShippableItem item : items) {
            System.out.println("Shipping: " + item.getName() + " " + item.getWeight() + "g");
        }
    }
}
