package com.amazon.ata.cost;

import com.amazon.ata.types.ShipmentCost;
import com.amazon.ata.types.ShipmentOption;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


public class WeightedCostStrategy implements CostStrategy {
    private Map<BigDecimal, CostStrategy> strategyWeights;

    /**
     *
     * @param strategyWeights to calculate cost.
     */
    public WeightedCostStrategy(Map<BigDecimal, CostStrategy> strategyWeights) {
        this.strategyWeights = strategyWeights;
    }


    @Override
    public ShipmentCost getCost(ShipmentOption shipmentOption) {
        BigDecimal totalCost = BigDecimal.ZERO;

        // Check if strategyWeights is not null
        if (strategyWeights != null) {
            for (Map.Entry<BigDecimal, CostStrategy> entry : strategyWeights.entrySet()) {
                BigDecimal weight = entry.getKey();
                CostStrategy strategy = entry.getValue();

                // Ensure strategy and shipmentOption are not null
                if (strategy != null && shipmentOption != null) {
                    ShipmentCost cost = strategy.getCost(shipmentOption);
                    totalCost = totalCost.add(cost.getCost().multiply(weight));
                } else {
                    throw new IllegalArgumentException("Strategy or shipmentOption is null");
                }
            }
        } else {
            throw new IllegalArgumentException("Strategy weights cannot be null");
        }

        // Return the ShipmentCost object with the calculated total cost
        return new ShipmentCost(shipmentOption, totalCost);
    }
    public static class Builder {
        /**
         * strategyWeights param.
         */
        private Map<BigDecimal, CostStrategy> strategyWeights;

        /**
         * No args constructor.
         */
        public Builder() {
            this.strategyWeights = new HashMap<>();
        }

        /**
         *
         * @param weight of constructor.
         * @param strategy of constructor.
         * @return strategyWeights.
         */
        public Builder addStrategyWithWeight(BigDecimal weight, CostStrategy strategy) {
            strategyWeights.put(weight, strategy);
            return this;
        }

        /**
         *
         * @return WeightedCostStrategy.
         */
        public WeightedCostStrategy build() {
            return new WeightedCostStrategy(strategyWeights);
        }
    }


}


