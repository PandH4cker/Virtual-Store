package store.business.util.client;

import store.business.util.logger.level.Level;
import store.business.util.logger.Logger;
import store.business.util.logger.LoggerFactory;

public class Client {
    private final Logger logger = LoggerFactory.getLogger("Client");
    private final String name;
    private final String surname;
    private final String address;
    private final int postalCode;
    private final long uniqueID;

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

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getAddress() {
        return this.address;
    }

    public int getPostalCode() {
        return this.postalCode;
    }

    public long getUniqueID() {
        return this.uniqueID;
    }

    @Override
    public String toString() {
        return  this.name
                +"\n" +this.surname
                +"\n" +this.address
                +"\n" +this.postalCode;
    }
}
