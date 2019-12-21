package store.business.util.product.description;

import store.business.gui.model.Model;
import store.business.util.product.description.exception.MalformedCharacterNameParameterException;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>The character name class</h1>
 * <p>
 *     Used to the actors mainly.
 *     It implements the {@code public interface Comparable<E>} interface
 *     It means that the class can be compared to another object of the same class
 * </p>
 * <img src="../../../../../uml/CharacterNameDiagram.jpg" />
 *
 * @author Raphael Dray
 * @version 1.0.0
 * @since 1.0.0
 * @see Comparable<CharacterName>
 */
public class CharacterName implements Comparable<CharacterName>, Model<MalformedCharacterNameParameterException> {
    private String name;
    private String surname;
    private int startComp;

    /**
     * Initialize the name and surname of the actor and handle a comparator
     * @param name The name of the actor
     * @param surname The surname of the actor
     */
    public CharacterName(final String name, final String surname) throws MalformedCharacterNameParameterException {
        this.name = name;
        this.surname = surname;
        this.startComp = 0;

        while((this.startComp < this.name.length())
                && (this.name.charAt(startComp)
                    == Character.toLowerCase(this.name.charAt(startComp)))) this.startComp++;

        validate();
    }

    /**
     * Getter of the name
     * @return String - The name of the actor
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter of the surname
     * @return String - The surname of the actor
     */
    public String getSurname() {
        return this.surname;
    }

    /**
     * {@inheritDoc}
     * @param other {@inheritDoc}
     * @return {@inheritDoc}
     * @see Override
     */
    @Override
    public int compareTo(CharacterName other) {
        if(other == null) return 1;
        int cmp = this.name.substring(this.startComp)
                           .compareTo(other.name.substring(other.startComp));

        if(cmp == 0) return this.surname.compareTo(other.surname);
        else return cmp;
    }

    /**
     * Overriding of the toString method from the Object class
     * @return String - The name and the surname
     * @see Override
     */
    @Override
    public String toString() {
        return this.name + " " + this.surname;
    }

    /**
     * {@inheritDoc}
     * @throws MalformedCharacterNameParameterException
     */
    @Override
    public void validate() throws MalformedCharacterNameParameterException {
        List<String> errors = new ArrayList<>();

        if(!hasContent(this.name)) errors.add("Name has no content.");
        if(!hasContent(this.surname)) errors.add("Surname has no content.");

        boolean passes = this.name.matches("^[A-Z]([a-z]+\\s[A-Z][a-z]+|[a-z]+|[A-Z])");
        if(!passes) errors.add("Name need to start with a capital letter and does not include numbers");
        passes = this.surname.matches("^[A-Z]([a-z]+\\s[A-Z][a-z]+|[a-z]+|[a-z][A-Z][a-z]+)");
        if(!passes) errors.add("Surname need to start with a capital letter and does not include numbers");

        if(!errors.isEmpty()) {
            MalformedCharacterNameParameterException ex = new MalformedCharacterNameParameterException();
            for(String error : errors)
                ex.addSuppressed(new MalformedCharacterNameParameterException(error));
            throw ex;
        }
    }
}