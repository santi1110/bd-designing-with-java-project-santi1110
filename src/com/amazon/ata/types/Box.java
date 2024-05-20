package com.amazon.ata.types;
import java.math.BigDecimal;
import java.util.Objects;

public class Box extends Packaging {
    private BigDecimal length;
    private BigDecimal width;
    private BigDecimal height;


    public BigDecimal getLength() {
        return length;
    }
    public BigDecimal getWidth() {
        return width;
    }
    public BigDecimal getHeight() {
        return height;
    }
    /**
     *  @param height   The height of the box.
     * @param length   The length of the box.
     * @param width    The width of the box.

     * @param material The material of the box.

     */
    public Box(Material material, BigDecimal height, BigDecimal length, BigDecimal width) {
        super(material);
        this.height = height;
        this.length = length;
        this.width = width;
    }

    @Override
    public boolean canFitItem(Item item) {
        // Check if the item's dimensions are smaller than the box's dimensions
        return length.compareTo(item.getLength()) > 0 &&
                width.compareTo(item.getWidth()) > 0 &&
                height.compareTo(item.getHeight()) > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Box box = (Box) o;
        return Objects.equals(length, box.length) &&
                Objects.equals(width, box.width) && Objects.equals(height, box.height);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), length, width, height);
    }

    @Override
    public BigDecimal getMass() {
        // Calculate the mass of the box
        BigDecimal two = BigDecimal.valueOf(2);

        // For simplicity, we ignore overlapping flaps
        BigDecimal endsArea = length.multiply(width).multiply(two);
        BigDecimal shortSidesArea = length.multiply(height).multiply(two);
        BigDecimal longSidesArea = width.multiply(height).multiply(two);

        return endsArea.add(shortSidesArea).add(longSidesArea);
    }
}
