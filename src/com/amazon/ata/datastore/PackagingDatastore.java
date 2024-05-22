package com.amazon.ata.datastore;

import com.amazon.ata.types.Box;
import com.amazon.ata.types.FcPackagingOption;
import com.amazon.ata.types.FulfillmentCenter;
import com.amazon.ata.types.Material;
import com.amazon.ata.types.Packaging;
import com.amazon.ata.types.PolyBag;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Stores all configured packaging pairs for all fulfillment centers.
 */
public class PackagingDatastore {

    /**
     * The stored pairs of fulfillment centers to the packaging options they support.
     */
    private final List<FcPackagingOption> fcPackagingOptions = Arrays.asList(
            createBoxFcPackagingOption("IND1", Material.CORRUGATE, "10", "10", "10"),
            createBoxFcPackagingOption("IND1", Material.CORRUGATE, "10", "10", "10"),
            createBoxFcPackagingOption("ABE2", Material.CORRUGATE, "20", "20", "20"),
            createBoxFcPackagingOption("ABE2", Material.CORRUGATE, "40", "40", "40"),
            createBoxFcPackagingOption("YOW4", Material.CORRUGATE, "10", "10", "10"),
            createBoxFcPackagingOption("YOW4", Material.CORRUGATE, "20", "20", "20"),
            createBoxFcPackagingOption("YOW4", Material.CORRUGATE, "60", "60", "60"),
            createBoxFcPackagingOption("IAD2", Material.CORRUGATE, "20", "20", "20"),
            createBoxFcPackagingOption("IAD2", Material.CORRUGATE, "20", "20", "20"),
            createBoxFcPackagingOption("PDX1", Material.CORRUGATE, "40", "40", "40"),
            createBoxFcPackagingOption("PDX1", Material.CORRUGATE, "60", "60", "60"),
            createPolyBagFcPackagingOption("IAD2", Material.POLYBAG, "2000"),
            createPolyBagFcPackagingOption("IAD2", Material.POLYBAG, "10000"),
            createPolyBagFcPackagingOption("IAD2", Material.POLYBAG, "5000"),
            createPolyBagFcPackagingOption("YOW4", Material.POLYBAG, "2000"),
            createPolyBagFcPackagingOption("YOW4", Material.POLYBAG, "5000"),
            createPolyBagFcPackagingOption("YOW4", Material.POLYBAG, "10000"),
            createPolyBagFcPackagingOption("IND1", Material.POLYBAG, "2000"),
            createPolyBagFcPackagingOption("IND1", Material.POLYBAG, "5000"),
            createPolyBagFcPackagingOption("ABE2", Material.POLYBAG, "2000"),
            createPolyBagFcPackagingOption("ABE2", Material.POLYBAG, "6000"),
            createPolyBagFcPackagingOption("PDX1", Material.POLYBAG, "5000"),
            createPolyBagFcPackagingOption("PDX1", Material.POLYBAG, "10000"),
            createPolyBagFcPackagingOption("YOW4", Material.POLYBAG, "5000")
    );

    /**
     * Create fulfillment center packaging option from provided parameters.
     */
    private FcPackagingOption createFcPackagingOption(String fcCode, Material material,
                                                      String length, String width, String height) {
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter(fcCode);
        Packaging packaging = new Box(material, new BigDecimal(length), new BigDecimal(width),
                new BigDecimal(height));

        return new FcPackagingOption(fulfillmentCenter, packaging);
    }
    private FcPackagingOption createPolyBagFcPackagingOption(String fcCode, Material material,
                                                             String volume) {
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter(fcCode);
        Packaging packaging = new PolyBag(material, new BigDecimal(volume));

        return new FcPackagingOption(fulfillmentCenter, packaging);
    }
    private FcPackagingOption createBoxFcPackagingOption(String fcCode, Material material,
                                                         String length, String width, String height) {
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter(fcCode);
        Packaging packaging = new Box(material, new BigDecimal(length), new BigDecimal(width),
                new BigDecimal(height));

        return new FcPackagingOption(fulfillmentCenter, packaging);
    }

    public List<FcPackagingOption> getFcPackagingOptions() {
        return fcPackagingOptions;
    }
}
