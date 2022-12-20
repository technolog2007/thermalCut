package shpp.com.model;

public enum Material {
    CARBON("carbon"),
    STAINLESS("stainless"),
    ALUMINUM("aluminum");

    private final String material;

    Material(String material) {
        this.material = material;
    }

    public String getMaterial() {
        return material;
    }
}
