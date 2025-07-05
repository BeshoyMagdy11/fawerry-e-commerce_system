
import java.util.*;

class Cart {
    private List<CartItem> items = new ArrayList<>();

    public void add(Product product, int quantity) {
        if (quantity > product.getQuantityInStock()) {
            throw new IllegalArgumentException("Quantity exceeds available stock");
        }
        for (CartItem item : items) {
            if (item.getProduct() == product) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        items.add(new CartItem(product, quantity));
    }

    public void checkout(Customer customer) {
        if (items.isEmpty()) {
            System.out.println("Error: Cart is empty");
            return;
        }

        // Calculate subtotal
        double subtotal = 0;
        for (CartItem item : items) {
            subtotal += item.getProduct().getPrice() * item.getQuantity();
        }

        double shippingFee = 0;
        List<ShippableItem> shippableItems = new ArrayList<>();
        double totalWeight = 0;

        for (CartItem item : items) {
            if (item.getProduct().isShippable()) {
                ShippableProduct product = (ShippableProduct) item.getProduct();
                double itemWeight = product.getWeightPerUnit() * item.getQuantity();
                totalWeight += itemWeight;
                shippableItems.add(new ShippingItem(product.getName(), itemWeight));
            }
        }
        if (!shippableItems.isEmpty()) {
            shippingFee = 30; 
        }

        double paidAmount = subtotal + shippingFee;
        if (customer.getBalance() < paidAmount) {
            System.out.println("Error: Insufficient balance");
            return;
        }

        customer.setBalance(customer.getBalance() - paidAmount);
        for (CartItem item : items) {
            Product product = item.getProduct();
            product.setQuantityInStock(product.getQuantityInStock() - item.getQuantity());
        }

        if (!shippableItems.isEmpty()) {
            ShippingService shippingService = new ShippingService();
            shippingService.ship(shippableItems);
        }

        System.out.println("---CONSOLE OUTPUT---");
        for (CartItem item : items) {
            if (item.getProduct().isShippable()) {
                ShippableProduct product = (ShippableProduct) item.getProduct();
                double totalWeightForItem = product.getWeightPerUnit() * item.getQuantity();
                System.out.println(item.getQuantity() + "x " + product.getName() + " " + totalWeightForItem + "g");
            }
        }
        if (totalWeight > 0) {
            System.out.println("Total package weight " + (totalWeight / 1000) + "kg");
        }
        for (CartItem item : items) {
            double totalPriceForItem = item.getProduct().getPrice() * item.getQuantity();
            System.out.println(item.getQuantity() + "x " + item.getProduct().getName() + " " + totalPriceForItem);
        }
        System.out.println("SUBTOTAL " + subtotal);
        System.out.println("Shipping " + shippingFee);
        System.out.println("Amount " + paidAmount);
        System.out.println("Customer current balance: " + customer.getBalance());
        System.out.println("END.");
    }
}
