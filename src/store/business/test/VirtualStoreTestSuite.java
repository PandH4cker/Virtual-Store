package store.business.test;


import org.junit.jupiter.api.Test;
import store.business.util.product.Product;
import store.business.util.product.VideoGame;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VirtualStoreTestSuite {
    private final Product jeux = new VideoGame("Borderlands", "60",
                                                "14203148", "100",
                                                "files/images/CallOfDutyMW.jpg", "Multi-joueurs",
                                                "XBOX ONE X");
    @Test
    void createVideoGameAndPrintIt() {
        assertEquals("titre: Borderlands, genre: Multi-joueurs, platforme: XBOX ONE X", jeux.toString());
    }
}
