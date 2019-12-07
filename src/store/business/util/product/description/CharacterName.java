package store.business.util.product.description;

/**
 * <h1>The character name class</h1>
 * <p>
 *     Used to the actors mainly.
 *     It implements the {@code public interface Comparable<E>} interface
 *     It means that the class can be compared to another object of the same class
 * </p>
 * <img src="../../../../../uml/CharacterName.png" />
 * @author Raphael Dray
 * @version 1.0.0
 * @since 1.0.0
 * @see Comparable<CharacterName>
 */
public class CharacterName implements Comparable<CharacterName> {
    private String name;
    private String surname;
    private int startComp;

    /**
     * Initialize the name and surname of the actor and handle a comparator
     * @param name The name of the actor
     * @param surname The surname of the actor
     */
    public CharacterName(final String name, final String surname) {
        this.name = name;
        this.surname = surname;
        this.startComp = 0;

        while((this.startComp < this.name.length())
                && (this.name.charAt(startComp)
                    == Character.toLowerCase(this.name.charAt(startComp)))) this.startComp++;
    }

    /**
     * Getter of the name
     * @return String The name of the actor
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter of the surname
     * @return String The surname of the actor
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
     * @return String The name and the surname
     * @see Override
     */
    @Override
    public String toString() {
        return this.name + " " + this.surname;
    }
}