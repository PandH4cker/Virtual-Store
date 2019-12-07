package store.business.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.business.util.product.description.CharacterName;

import java.util.LinkedList;
import java.util.List;

public class CharacterNameTest {
    private static List<CharacterName> characterNames = new LinkedList<>();

    @BeforeAll
    static void setup() {
        characterNames.add(new CharacterName("Dray", "Raphael"));
        characterNames.add(new CharacterName("De Sevin", "Alexandre"));
        characterNames.add(new CharacterName("Nevoux", "Guillaume"));
        characterNames.add(new CharacterName("Dahan", "Eythan"));
    }

    @Test
    @DisplayName("Constructor of CharacterName Tester")
    void characterNameConstructorTest() {
        CharacterName first = characterNames.get(0);
        Assertions.assertEquals(0, first
                                                .compareTo(
                                                        new CharacterName("Dray", "Raphael")));
        Assertions.assertEquals(first.getName() + " " + first.getSurname(), first.toString());
    }
}
