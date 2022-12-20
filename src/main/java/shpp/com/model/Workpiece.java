package shpp.com.model;

public class Workpiece {

    private float thickness;
    private float width;
    private float length;
    private Float cuttingLength;
    private Material material;

    public Workpiece(float thickness, float width, float length, Material material) {
        this.thickness = thickness;
        this.width = width;
        this.length = length;
        this.material = material;
    }

    public Workpiece() {
    }

    public Workpiece(Float cuttingLength, Material material) {
        this.cuttingLength = cuttingLength;
        this.material = material;
    }

    public float getThickness() {
        return thickness;
    }

    public Workpiece setThickness(float thickness) {
        this.thickness = thickness;
        return this;
    }

    public float getWidth() {
        return width;
    }

    public Workpiece setWidth(float width) {
        this.width = width;
        return this;
    }

    public float getLength() {
        return length;
    }

    public Workpiece setLength(float length) {
        this.length = length;
        return this;
    }

    public Float getCuttingLength() {
        return cuttingLength;
    }

    public Workpiece setCuttingLength(Float cuttingLength) {
        this.cuttingLength = cuttingLength;
        return this;
    }

    public Material getMaterial() {
        return material;
    }

    public Workpiece setMaterial(Material material) {
        this.material = material;
        return this;
    }

    @Override
    public String toString() {
        return "Workpiece{" +
                "thickness=" + thickness +
                ", width=" + width +
                ", length=" + length +
                ", cuttingLength=" + cuttingLength +
                ", material=" + material.getMaterial() +
                '}';
    }
}
