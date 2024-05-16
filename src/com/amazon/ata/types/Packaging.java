package com.amazon.ata.types;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Represents a packaging option.
 *
 * This packaging supports standard boxes, having a length, width, and height.
 * Items can fit in the packaging so long as their dimensions are all smaller than
 * the packaging's dimensions.
 */

public abstract class Packaging {
    private Material material;

    /**
     *
     * @param material of the packaging.
     */
    public Packaging(Material material) {
        this.material = material;
    }

    public Material getMaterial() {
        return material;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Packaging packaging = (Packaging) o;
        return material == packaging.material;
    }

    @Override
    public int hashCode() {
        return Objects.hash(material);
    }

    /**
     *
     * @param item of the packaging.
     * @return boolean canfititem.
     */
    public abstract boolean canFitItem(Item item);

    /**
     *
     * @return the Mass of the packaging.
     */
    public abstract BigDecimal getMass();
}

