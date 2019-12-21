package store.business.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import store.business.util.product.description.CharacterName;
import store.business.util.product.description.exception.MalformedCharacterNameParameterException;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CharacterNameTest {
    private static List<CharacterName> characterNames = new LinkedList<>();

    @BeforeAll
    static void setup() {
        try {
            characterNames.add(new CharacterName("Dray", "Raphael"));
            characterNames.add(new CharacterName("De Sevin", "Alexandre"));
            characterNames.add(new CharacterName("Nevoux", "Guillaume"));
            characterNames.add(new CharacterName("Dahan", "Eythan"));
        } catch (MalformedCharacterNameParameterException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Constructor of CharacterName Tester")
    void characterNameConstructorTest() {
        CharacterName first = characterNames.get(0);

        populateTests(first);
    }

    private void populateTests(CharacterName first) {
        assertAll("Le comparable est non conforme", executeComparaisonTests(first));

        assertEquals(first.getName() + " " + first.getSurname(), first.toString());

        assertAll("Données nom personnage non conforme",
                CharacterNameTest::executeNameTests,
                CharacterNameTest::executeSurnameTests
        );
    }

    private Executable[] executeComparaisonTests(CharacterName first) {
        return new Executable[]{() -> assertEquals(0, first.compareTo(new CharacterName("Dray", "Raphael"))),
                () -> {
                    Collections.sort(characterNames);
                    assertNotEquals(first, characterNames.get(0));
                }};
    }

    private static void executeNameTests() {
        assertAll("Nom non conforme",
                () -> assertThrows(NullPointerException.class, () -> new CharacterName(null, "Raphael")),
                () -> assertThrows(MalformedCharacterNameParameterException.class, () -> new CharacterName("", "Raphael")),
                () -> assertThrows(MalformedCharacterNameParameterException.class, () -> new CharacterName("11Dray", "Raphael")),
                () -> assertThrows(MalformedCharacterNameParameterException.class, () -> new CharacterName("Dr11ay", "Raphael")),
                () -> assertThrows(MalformedCharacterNameParameterException.class, () -> new CharacterName("dray", "Raphael")),
                () -> assertDoesNotThrow(() -> new CharacterName("De Sevin", "Alexandre"))
        );
    }

    private static void executeSurnameTests() {
        assertAll("Prénom non conforme",
                () -> assertThrows(NullPointerException.class, () -> new CharacterName("Dray", null)),
                () -> assertThrows(MalformedCharacterNameParameterException.class, () -> new CharacterName("Dray", "")),
                () -> assertThrows(MalformedCharacterNameParameterException.class, () -> new CharacterName("Dray", "11Raphael")),
                () -> assertThrows(MalformedCharacterNameParameterException.class, () -> new CharacterName("Dray", "Ra11phael")),
                () -> assertThrows(MalformedCharacterNameParameterException.class, () -> new CharacterName("Dray", "raphael"))
        );
    }

}
