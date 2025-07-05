public class App {
    public static void main(String[] args) throws Exception {
        // Create products
        ShippableProduct cheese = new ShippableProduct("Cheese", 100, 10, 200); 
        ShippableProduct biscuits = new ShippableProduct("Biscuits", 150, 5, 700); 
        NonShippableProduct scratchCard = new NonShippableProduct("Recharge card", 50, 20);
        Customer customer = new Customer(1000);
        Cart cart = new Cart();
        cart.add(cheese, 2);
        cart.add(biscuits, 1);
        cart.checkout(customer);    }
}
