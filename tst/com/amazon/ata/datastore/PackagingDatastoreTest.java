package com.amazon.ata.datastore;

import com.amazon.ata.types.*;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PackagingDatastoreTest {

    FulfillmentCenter ind1 = new FulfillmentCenter("IND1");
    FulfillmentCenter abe2 = new FulfillmentCenter("ABE2");
    FulfillmentCenter yow4 = new FulfillmentCenter("YOW4");
    FulfillmentCenter iad2 = new FulfillmentCenter("IAD2");
    FulfillmentCenter pdx1 = new FulfillmentCenter("PDX1");

    Packaging package10Cm = new Box(Material.CORRUGATE,
            BigDecimal.valueOf(10), BigDecimal.valueOf(10), BigDecimal.valueOf(10));

    Packaging package20Cm = new Box(Material.CORRUGATE,
            BigDecimal.valueOf(20), BigDecimal.valueOf(20), BigDecimal.valueOf(20));

    Packaging package40Cm = new Box(Material.CORRUGATE,
            BigDecimal.valueOf(40), BigDecimal.valueOf(40), BigDecimal.valueOf(40));

    Packaging package60Cm = new Box(Material.CORRUGATE,
            BigDecimal.valueOf(60), BigDecimal.valueOf(60), BigDecimal.valueOf(60));

    Packaging polybag2000 = new PolyBag(Material.POLYBAG, BigDecimal.valueOf(2000));
    Packaging polybag10000 = new PolyBag(Material.POLYBAG, BigDecimal.valueOf(10000));
    Packaging polybag5000 = new PolyBag(Material.POLYBAG, BigDecimal.valueOf(5000));
    Packaging polybag6000 = new PolyBag(Material.POLYBAG, BigDecimal.valueOf(6000));

    FcPackagingOption ind1_10Cm = new FcPackagingOption(ind1, package10Cm);
    FcPackagingOption abe2_20Cm = new FcPackagingOption(abe2, package20Cm);
    FcPackagingOption abe2_40Cm = new FcPackagingOption(abe2, package40Cm);
    FcPackagingOption yow4_10Cm = new FcPackagingOption(yow4, package10Cm);
    FcPackagingOption yow4_20Cm = new FcPackagingOption(yow4, package20Cm);
    FcPackagingOption yow4_60Cm = new FcPackagingOption(yow4, package60Cm);
    FcPackagingOption iad2_20Cm = new FcPackagingOption(iad2, package20Cm);;
    FcPackagingOption pdx1_40Cm = new FcPackagingOption(pdx1, package40Cm);
    FcPackagingOption pdx1_60Cm = new FcPackagingOption(pdx1, package60Cm);
    FcPackagingOption iad2_2000Polybag = new FcPackagingOption(iad2, polybag2000);
    FcPackagingOption iad2_10000Polybag = new FcPackagingOption(iad2, polybag10000);
    FcPackagingOption iad2_5000Polybag = new FcPackagingOption(iad2, polybag5000);
    FcPackagingOption yow4_2000Polybag = new FcPackagingOption(yow4, polybag2000);
    FcPackagingOption yow4_5000Polybag = new FcPackagingOption(yow4, polybag5000);
    FcPackagingOption yow4_10000Polybag = new FcPackagingOption(yow4, polybag10000);
    FcPackagingOption ind1_2000Polybag = new FcPackagingOption(ind1, polybag2000);
    FcPackagingOption ind1_5000Polybag = new FcPackagingOption(ind1, polybag5000);
    FcPackagingOption abe2_2000Polybag = new FcPackagingOption(abe2, polybag2000);
    FcPackagingOption abe2_6000Polybag = new FcPackagingOption(abe2, polybag6000);
    FcPackagingOption pdx1_5000Polybag = new FcPackagingOption(pdx1, polybag5000);
    FcPackagingOption pdx1_10000Polybag = new FcPackagingOption(pdx1, polybag10000);



    @Test
    public void getFcPackagingOptions_get_returnAllOptions() {
        // GIVEN
        PackagingDatastore packagingDatastore = new PackagingDatastore();
        List<FcPackagingOption> expectedPackagingOptions = Arrays.asList(
                ind1_10Cm, abe2_20Cm, abe2_40Cm, yow4_10Cm, yow4_20Cm, yow4_60Cm, iad2_20Cm, iad2_20Cm, pdx1_40Cm,
                pdx1_60Cm, pdx1_60Cm, iad2_2000Polybag, iad2_10000Polybag, iad2_5000Polybag, yow4_2000Polybag,
                yow4_5000Polybag, yow4_10000Polybag, ind1_2000Polybag, ind1_5000Polybag, abe2_2000Polybag,
                abe2_6000Polybag, pdx1_5000Polybag, pdx1_10000Polybag, yow4_5000Polybag);

        // WHEN
        List<FcPackagingOption> fcPackagingOptions = packagingDatastore.getFcPackagingOptions();

        // THEN
        assertEquals(expectedPackagingOptions.size(), fcPackagingOptions.size(),
                String.format("There should be %s FC/Packaging pairs.", expectedPackagingOptions.size()));
        for (FcPackagingOption expectedPackagingOption : expectedPackagingOptions) {
            assertTrue(fcPackagingOptions.contains(expectedPackagingOption), String.format("expected packaging " +
                            "options from PackagingDatastore to contain %s package in fc %s",
                    expectedPackagingOption.getPackaging(),
                    expectedPackagingOption.getFulfillmentCenter().getFcCode()));
        }
        assertTrue(true, "getFcPackagingOptions contained all of the expected options.");
    }
}
