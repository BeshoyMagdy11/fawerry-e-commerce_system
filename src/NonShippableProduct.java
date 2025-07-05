class NonShippableProduct extends Product {
    public NonShippableProduct(String name, double price, int quantityInStock) {
        super(name, price, quantityInStock);
    }

    @Override
    public boolean isShippable() {
        return false;
    }
}