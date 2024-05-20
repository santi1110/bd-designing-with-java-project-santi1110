package com.amazon.ata.dao;

import com.amazon.ata.datastore.PackagingDatastore;
import com.amazon.ata.exceptions.NoPackagingFitsItemException;
import com.amazon.ata.exceptions.UnknownFulfillmentCenterException;
import com.amazon.ata.types.Box;
import com.amazon.ata.types.FcPackagingOption;
import com.amazon.ata.types.FulfillmentCenter;
import com.amazon.ata.types.Item;
import com.amazon.ata.types.Packaging;
import com.amazon.ata.types.ShipmentOption;

/**
 * Access data for which packaging is available at which fulfillment center.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PackagingDAO {
    private final Map<FulfillmentCenter, Set<Packaging>> fcPackagingOptionsMap;

    /**
     *
     * @param datastore using HashMap.
     */
    public PackagingDAO(PackagingDatastore datastore) {
        this.fcPackagingOptionsMap = new HashMap<>();
        for (FcPackagingOption option : datastore.getFcPackagingOptions()) {
            FulfillmentCenter fc = option.getFulfillmentCenter();
            Packaging packaging = option.getPackaging();
            // Check for duplicate entry
            if (!fcPackagingOptionsMap.containsKey(fc)) {
                fcPackagingOptionsMap.put(fc, new HashSet<>());
            }
            Set<Packaging> packagingSet = fcPackagingOptionsMap.get(fc);
            // Add only if it's a box
            if (packaging instanceof Box) {
                packagingSet.add(packaging);
            }
        }
    }

    /**
     *
     * @param item Type.
     * @param fulfillmentCenter Type.
     * @return the result.
     * @throws UnknownFulfillmentCenterException if there is no shipment.
     * @throws NoPackagingFitsItemException if there is no shipment.
     */
    public List<ShipmentOption> findShipmentOptions(Item item, FulfillmentCenter fulfillmentCenter)
            throws UnknownFulfillmentCenterException, NoPackagingFitsItemException {
        Set<Packaging> options = fcPackagingOptionsMap.get(fulfillmentCenter);
        if (options == null) {
            throw new UnknownFulfillmentCenterException(
                    String.format("Unknown FC: %s!", fulfillmentCenter.getFcCode()));
        }

        List<ShipmentOption> result = new ArrayList<>();

        for (Packaging packaging : options) {
            if (packaging.canFitItem(item)) {
                result.add(ShipmentOption.builder()
                        .withItem(item)
                        .withPackaging(packaging)
                        .withFulfillmentCenter(fulfillmentCenter)
                        .build());
                // We can return immediately upon finding a fitting option
            }
        }

        if (result.isEmpty()) {
            throw new NoPackagingFitsItemException(
                    String.format("No packaging at %s fits %s!", fulfillmentCenter.getFcCode(), item));
        }

        return result;
    }
}





