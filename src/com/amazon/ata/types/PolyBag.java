package com.amazon.ata.types;

import java.math.BigDecimal;


public class PolyBag extends Packaging {
    private BigDecimal volume;

    /**
     *
     * @param material of the polybag.
     * @param volume of the polybag.
     */
    public PolyBag(Material material, BigDecimal volume) {
        super(material);
        this.volume = volume;
    }

    @Override
    public boolean canFitItem(Item item) {
        // Check if the item's volume is smaller than the polybag's volume
        BigDecimal itemVolume = item.getLength().multiply(item.getWidth()).multiply(item.getHeight());
        return itemVolume.compareTo(volume) < 0;
    }

    @Override
    public BigDecimal getMass() {
        // Calculate the mass of the polybag
        // We'll use an approximation since Math.sqrt() doesn't accept BigDecimal directly
        double volumeDouble = volume.doubleValue();
        double massDouble = Math.ceil(Math.sqrt(volumeDouble) * 0.6);
        return BigDecimal.valueOf(massDouble);
    }
}
