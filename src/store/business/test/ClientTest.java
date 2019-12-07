package store.business.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import store.business.util.client.Client;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {
    private Client client = null;
    private Client client2 = null;

    @DisplayName("Constructor of Client Tester")
    @ParameterizedTest
    @MethodSource("getDataForConstructor")
    void clientConstructorTest(final String name,
                               final String surname,
                               final String address,
                               final int postalCode,
                               final long uniqueID) {
        this.client = new Client(name, surname, address, postalCode, uniqueID);
        assertAll("Client non conforme",
                    () -> assertEquals(name, this.client.getName()),
                    () -> assertEquals(surname, this.client.getSurname()),
                    () -> assertEquals(address, this.client.getAddress()),
                    () -> assertEquals(postalCode, this.client.getPostalCode()),
                    () -> assertEquals(uniqueID, this.client.getUniqueID())
        );

        this.client2 = new Client(name, surname, address, postalCode);
        assertAll("Client non conforme",
                () -> assertEquals(name, this.client.getName()),
                () -> assertEquals(surname, this.client.getSurname()),
                () -> assertEquals(address, this.client.getAddress()),
                () -> assertEquals(postalCode, this.client.getPostalCode()),
                () -> assertEquals(uniqueID, this.client.getUniqueID())
        );

        assertAll("Client non egaux",
                () -> assertEquals(this.client, this.client),
                () -> assertEquals(this.client, this.client2)
        );

        assertEquals(this.client.getName()
                               + "\n" + this.client.getSurname()
                               + "\n" + this.client.getAddress()
                               + "\n" + this.client.getPostalCode(), this.client.toString());
    }

    static List<Object[]> getDataForConstructor() {
        return Arrays.asList(
                new Object[][] {
                        {"Dray", "Raphael", "19, Boulevard Edouard Branly, Sarcelles", 95200, 134811913}
                });
    }
}
