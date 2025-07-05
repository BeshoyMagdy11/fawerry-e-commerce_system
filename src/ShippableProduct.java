class ShippableProduct extends Product {
    private double weightPerUnit;

    public ShippableProduct(String name, double price, int quantityInStock, double weightPerUnit) {
        super(name, price, quantityInStock);
        this.weightPerUnit = weightPerUnit;
    }

    public double getWeightPerUnit() {
        return weightPerUnit;
    }

    @Override
    public boolean isShippable() {
        return true;
    }
}