package com.amazon.ata;

import com.amazon.ata.cost.CarbonCostStrategy;
import com.amazon.ata.cost.CostStrategy;
import com.amazon.ata.cost.MonetaryCostStrategy;
import com.amazon.ata.cost.WeightedCostStrategy;
import com.amazon.ata.dao.PackagingDAO;
import com.amazon.ata.datastore.PackagingDatastore;
import com.amazon.ata.service.ShipmentService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class App {
    private App() {}

    private static PackagingDatastore getPackagingDatastore() {
        return new PackagingDatastore();
    }

    private static PackagingDAO getPackagingDAO() {
        return new PackagingDAO(getPackagingDatastore());
    }

    private static CostStrategy getCostStrategy() {
        // Create instances of CarbonCostStrategy and MonetaryCostStrategy
        CarbonCostStrategy carbonCostStrategy = new CarbonCostStrategy();
        MonetaryCostStrategy monetaryCostStrategy = new MonetaryCostStrategy();

        // Create a map to hold strategy weights
        Map<BigDecimal, CostStrategy> strategyWeights = new HashMap<>();
        strategyWeights.put(BigDecimal.valueOf(0.8), monetaryCostStrategy);
        strategyWeights.put(BigDecimal.valueOf(0.2), carbonCostStrategy);

        // Create and return WeightedCostStrategy with the specified weights
        return new WeightedCostStrategy(strategyWeights);
    }


    public static ShipmentService getShipmentService() {
        return new ShipmentService(getPackagingDAO(), getCostStrategy());
    }
}
