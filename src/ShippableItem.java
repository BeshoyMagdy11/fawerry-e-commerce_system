interface ShippableItem {
    String getName();
    double getWeight();
}

class ShippingItem implements ShippableItem {
    private String name;
    private double weight;

    public ShippingItem(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}