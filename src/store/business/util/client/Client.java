package store.business.util.client;

import store.business.gui.model.Model;
import store.business.util.client.exception.MalformedClientParameterException;
import store.business.util.logger.level.Level;
import store.business.util.logger.Logger;
import store.business.util.logger.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>The client object</h1>
 * <p>
 *     A client is an object that has:
 *     <li>A name</li>
 *     <li>A surname</li>
 *     <li>An Address</li>
 *     <li>A postal code</li>
 *     <li>An UID</li>
 * </p>
 * <img src="../../../../uml/ClientDiagram.jpg" />
 *
 * @author Raphael Dray
 * @version 1.0.0
 * @since 1.0.0
 * @see Logger
 */
public class Client implements Model<MalformedClientParameterException> {
    private final Logger logger = LoggerFactory.getLogger("Client");
    private final String name;
    private final String surname;
    private final String address;
    private final String postalCode;
    private final long uniqueID;

    /**
     * This constructor initialize these attributes
     * @param name The name of the client
     * @param surname The surname of the client
     * @param address The address of the client
     * @param postalCode The postal code of the client
     * @param uniqueID The UID of the client
     */
    public Client(final String name,
                  final String surname,
                  final String address,
                  final String postalCode,
                  final long uniqueID) throws MalformedClientParameterException {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.postalCode = postalCode;
        this.uniqueID = uniqueID;
        validate();
        logger.log("New Client Created [" + this + "]", Level.INFO);
    }

    public Client(final String name,
                  final String surname,
                  final String address,
                  final String postalCode) throws MalformedClientParameterException {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.postalCode = postalCode;
        this.uniqueID = this.computeHashCode(name, surname, address, postalCode);
        validate();
        logger.log("New Client Created [" + this + "]", Level.INFO);
    }

    /**
     * {@inheritDoc}
     * @param other The other object to check the equality
     * @return boolean - The answer to the question of the objects are equal
     */
    @Override
    public boolean equals(final Object other) {
        if(this == other) return true;
        if(!(other instanceof Client)) return false;

        final Client client = (Client) other;
        return this.getUniqueID() == client.getUniqueID();
    }

    /**
     * Compute Hashcode to perform the UID
     * @param name The name of the client
     * @param surname The surname of the client
     * @param address The address of the client
     * @param postalCode The postalCode of the client
     * @return long - The hashcode, the UID
     */
    private long computeHashCode(final String name,
                                 final String surname,
                                 final String address,
                                 final String postalCode) {
        long result = Long.parseLong(postalCode);
        result = 31 * result + name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + address.hashCode();

        return result & 0xffffffffL / 10;
    }

    /**
     * Getter of the name
     * @return String - The name of the client
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter of the surname
     * @return String - The surname of the client
     */
    public String getSurname() {
        return this.surname;
    }

    /**
     * Getter of the address
     * @return String - The address of the client
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Getter of the postal code
     * @return String - The postal code of the client
     */
    public String getPostalCode() {
        return this.postalCode;
    }

    /**
     * Getter of the UID
     * @return long - The UID of the client
     */
    public long getUniqueID() {
        return this.uniqueID;
    }

    /**
     * Overriding toString method from the Object class
     * @return String - The description of a client
     * @see Override
     */
    @Override
    public String toString() {
        return  this.name
                +"\n" +this.surname
                +"\n" +this.address
                +"\n" +this.postalCode;
    }

    /**
     * {@inheritDoc}
     * @throws MalformedClientParameterException
     */
    @Override
    public void validate() throws MalformedClientParameterException {
        List<String> errors = new ArrayList<>();

        if(!hasContent(this.name)) errors.add("Name has no content.");
        if(!hasContent(this.surname)) errors.add("Surname has no content.");
        if(!hasContent(this.address)) errors.add("Address has no content.");
        if(!hasContent(this.postalCode)) errors.add("Postal Code has no content.");

        boolean passes = this.postalCode.matches("^(([0-8][0-9])|(9[0-5]))[0-9]{3}$");
        if(!passes) errors.add("Postal Code malformed");

        passes = this.name.matches("^[A-Z]([a-z]+\\s[A-Z][a-z]+|[a-z]+)");
        if(!passes) errors.add("Name need to start with a capital letter and does not include numbers");
        passes = this.surname.matches("^[A-Z]([a-z]+\\s[A-Z][a-z]+|[a-z]+)");
        if(!passes) errors.add("Surname need to start with a capital letter and does not include numbers");

        passes = ensureNotNull(this.uniqueID, "UID is null", errors);
        if (passes)
            if(!(this.uniqueID > 0)) errors.add("UID is negative");

        if (!errors.isEmpty()) {
            MalformedClientParameterException ex = new MalformedClientParameterException();
            for (String error : errors)
                ex.addSuppressed(new MalformedClientParameterException(error));
            throw ex;
        }
    }
}