package store.business.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import store.business.util.client.Client;
import store.business.util.client.exception.MalformedClientParameterException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    private Client client = null;
    private Client client2 = null;

    @DisplayName("Constructor of Client Tester")
    @ParameterizedTest
    @MethodSource("getDataForConstructor")
    void clientConstructorTest(final String name,
                               final String surname,
                               final String address,
                               final String postalCode,
                               final long uniqueID) {
        try { this.client = new Client(name, surname, address, postalCode, uniqueID); }
        catch (MalformedClientParameterException e) { e.printStackTrace(); }

        assertAll("Client non conforme", executeConformityTests(this.client,name, surname, address, postalCode, uniqueID));

        try { this.client2 = new Client(name, surname, address, postalCode); }
        catch (MalformedClientParameterException e) { e.printStackTrace(); }

        assertAll("Client non conforme", executeConformityTests(this.client2,name, surname, address, postalCode, uniqueID));

        populateTests();
    }

    private void populateTests() {
        assertAll("Client non egaux",
                () -> assertEquals(this.client, this.client),
                () -> assertEquals(this.client, this.client2)
        );

        assertEquals(this.client.getName()
                               + "\n" + this.client.getSurname()
                               + "\n" + this.client.getAddress()
                               + "\n" + this.client.getPostalCode(), this.client.toString());

        assertAll("Données clients non conformes",
                ClientTest::executeNameTests,
                ClientTest::executeSurnameTests,
                ClientTest::executeAddressTests,
                ClientTest::executePostalCodeTests
        );
    }

    private Executable[] executeConformityTests(Client client,String name, String surname, String address, String postalCode, long uniqueID) {
        return new Executable[]{
                () -> assertEquals(name, client.getName()),
                () -> assertEquals(surname, client.getSurname()),
                () -> assertEquals(address, client.getAddress()),
                () -> assertEquals(postalCode, client.getPostalCode()),
                () -> assertEquals(uniqueID, client.getUniqueID())
        };
    }

    static List<Object[]> getDataForConstructor() {
        return Arrays.asList(
                new Object[][] {
                        {"Dray", "Raphael", "19, Boulevard Edouard Branly, Sarcelles", "95200", 134811913}
                });
    }

    private static void executeNameTests() {
        assertAll("Nom non conforme",
                () -> assertThrows(NullPointerException.class, () -> new Client(null, "Raphael", "19, Boulevard Edouard Branly, Sarcelles", "95200")),
                () -> assertThrows(MalformedClientParameterException.class, () -> new Client("", "Raphael", "19, Boulevard Edouard Branly, Sarcelles", "95200")),
                () -> assertThrows(MalformedClientParameterException.class, () -> new Client("11Dray", "Raphael", "19, Boulevard Edouard Branly, Sarcelles", "95200")),
                () -> assertThrows(MalformedClientParameterException.class, () -> new Client("dray", "Raphael", "19, Boulevard Edouard Branly, Sarcelles", "95200")),
                () -> assertThrows(MalformedClientParameterException.class, () -> new Client("Dr11ay", "Raphael", "19, Boulevard Edouard Branly, Sarcelles", "95200")),
                () -> assertDoesNotThrow(() -> new Client("De Sevin", "Alexandre", "19, Boulevard Edouard Branly, Sarcelles", "95200"))
        );
    }

    private static void executeSurnameTests() {
        assertAll("Prénom non conforme",
                () -> assertThrows(NullPointerException.class, () -> new Client("Dray", null, "19, Boulevard Edouard Branly, Sarcelles", "95200")),
                () -> assertThrows(MalformedClientParameterException.class, () -> new Client("Dray", "", "19, Boulevard Edouard Branly, Sarcelles", "95200")),
                () -> assertThrows(MalformedClientParameterException.class, () -> new Client("Dray", "11Raphael", "19, Boulevard Edouard Branly, Sarcelles", "95200")),
                () -> assertThrows(MalformedClientParameterException.class, () -> new Client("Dray", "raphael", "19, Boulevard Edouard Branly, Sarcelles", "95200")),
                () -> assertThrows(MalformedClientParameterException.class, () -> new Client("Dray", "Ra11phael", "19, Boulevard Edouard Branly, Sarcelles", "95200"))
        );
    }

    private static void executeAddressTests() {
        assertAll("Adresse non conforme",
                () -> assertThrows(NullPointerException.class, () -> new Client("Dray", "Raphael", null, "95200")),
                () -> assertThrows(MalformedClientParameterException.class, () -> new Client("Dray", "Raphael", "", "95200"))
        );
    }

    private static void executePostalCodeTests() {
        assertAll("Code Postal non conforme",
                () -> assertThrows(MalformedClientParameterException.class, () -> new Client("Dray", "Raphael", "19, Boulevard Edouard Branly, Sarcelles", "0")),
                () -> assertDoesNotThrow(() -> new Client("Dray", "Raphael", "19, Boulevard Edouard Branly, Sarcelles", "01000"))
        );
    }
}