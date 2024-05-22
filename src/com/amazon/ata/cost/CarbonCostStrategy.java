package com.amazon.ata.cost;

import com.amazon.ata.types.Material;
import com.amazon.ata.types.Packaging;
import com.amazon.ata.types.ShipmentCost;
import com.amazon.ata.types.ShipmentOption;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


public class CarbonCostStrategy implements CostStrategy {
    private static final BigDecimal CARBON_UNITS_PER_GRAM_CORRUGATE = new BigDecimal("0.017");
    private static final BigDecimal CARBON_UNITS_PER_GRAM_LAMINATED_PLASTIC = new BigDecimal("0.012");
    private static final BigDecimal CARBON_UNITS_PER_GRAM_POLYBAG = new BigDecimal("0.012");
    private final Map<Material, BigDecimal> carbonCostPerGram;

    /**
     * CarbpnCsotStrategy Constructor.
     */
    public CarbonCostStrategy() {
        carbonCostPerGram = new HashMap<>();
        carbonCostPerGram.put(Material.CORRUGATE, CARBON_UNITS_PER_GRAM_CORRUGATE);
        carbonCostPerGram.put(Material.LAMINATED_PLASTIC, CARBON_UNITS_PER_GRAM_LAMINATED_PLASTIC);
        carbonCostPerGram.put(Material.POLYBAG, CARBON_UNITS_PER_GRAM_POLYBAG);
    }

    @Override
    public ShipmentCost getCost(ShipmentOption shipmentOption) {
        if (shipmentOption == null || shipmentOption.getPackaging() == null) {
            throw new IllegalArgumentException("ShipmentOption or Packaging must not be null");
        }

        Packaging packaging = shipmentOption.getPackaging();
        Material material = packaging.getMaterial();

        if (material == null) {
            throw new IllegalArgumentException("Material in Packaging must not be null");
        }

        BigDecimal carbonCost = carbonCostPerGram.get(material);

        if (carbonCost == null) {
            throw new IllegalArgumentException("No carbon cost defined for material: " + material);
        }

        BigDecimal mass = packaging.getMass();

        if (mass == null) {
            throw new IllegalArgumentException("Mass in Packaging must not be null");
        }

        BigDecimal cost = mass.multiply(carbonCost);

        return new ShipmentCost(shipmentOption, cost);
    }
}

