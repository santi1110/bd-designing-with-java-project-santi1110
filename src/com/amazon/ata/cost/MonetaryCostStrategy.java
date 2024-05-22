package com.amazon.ata.cost;

import com.amazon.ata.types.Material;
import com.amazon.ata.types.Packaging;
import com.amazon.ata.types.ShipmentCost;
import com.amazon.ata.types.ShipmentOption;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * A strategy to calculate the cost of a ShipmentOption based on its dollar cost.
 */

public class MonetaryCostStrategy implements CostStrategy {
    private static final BigDecimal LABOR_COST = BigDecimal.valueOf(0.43);
    private final Map<Material, BigDecimal> materialCostPerGram;

    /**
     * Creating HashMap.
     */
    public MonetaryCostStrategy() {
        materialCostPerGram = new HashMap<>();
        materialCostPerGram.put(Material.CORRUGATE, BigDecimal.valueOf(0.005));
        materialCostPerGram.put(Material.LAMINATED_PLASTIC, BigDecimal.valueOf(0.25));
        materialCostPerGram.put(Material.POLYBAG, BigDecimal.valueOf(0.25));
    }

    @Override
    public ShipmentCost getCost(ShipmentOption shipmentOption) {
        Packaging packaging = shipmentOption.getPackaging();

        // Check if packaging or its material is null
        if (packaging == null || packaging.getMaterial() == null) {
            throw new IllegalArgumentException("Packaging or material cannot be null");
        }

        // Check if materialCostPerGram map is properly initialized
        if (materialCostPerGram == null) {
            throw new IllegalStateException("Material cost map is not initialized");
        }

        // Retrieve material cost from the map
        BigDecimal materialCost = this.materialCostPerGram.get(packaging.getMaterial());

        // Check if material cost is found
        if (materialCost == null) {
            throw new IllegalArgumentException("Material cost not found for the packaging material");
        }

        // Calculate the total cost
        BigDecimal cost = packaging.getMass().multiply(materialCost).add(LABOR_COST);

        // Return the shipment cost
        return new ShipmentCost(shipmentOption, cost);
    }

}

