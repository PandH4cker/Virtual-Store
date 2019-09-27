package store.business.util.client;

import store.business.util.logger.level.Level;
import store.business.util.logger.Logger;
import store.business.util.logger.LoggerFactory;

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
 * <img src="../../../../uml/Client.png" />
 * @author Raphael Dray
 * @version 1.0.0
 * @since 1.0.0
 * @see Logger
 */
public class Client {
    private final Logger logger = LoggerFactory.getLogger("Client");
    private final String name;
    private final String surname;
    private final String address;
    private final int postalCode;
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
                  final int postalCode,
                  final long uniqueID) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.postalCode = postalCode;
        this.uniqueID = uniqueID;
        logger.log("New Client Created [" + this + "]", Level.INFO);
    }

    /**
     * Getter of the name
     * @return String The name of the client
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter of the surname
     * @return String The surname of the client
     */
    public String getSurname() {
        return this.surname;
    }

    /**
     * Getter of the address
     * @return String The address of the client
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Getter of the postal code
     * @return int The postal code of the client
     */
    public int getPostalCode() {
        return this.postalCode;
    }

    /**
     * Getter of the UID
     * @return long The UID of the client
     */
    public long getUniqueID() {
        return this.uniqueID;
    }

    /**
     * Overriding toString method from the Object class
     * @return String The description of a client
     * @see Override
     */
    @Override
    public String toString() {
        return  this.name
                +"\n" +this.surname
                +"\n" +this.address
                +"\n" +this.postalCode;
    }
}
