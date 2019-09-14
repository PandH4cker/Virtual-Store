package store.business.util.client;

import store.business.util.logger.Logger;
import store.business.util.logger.LoggerFactory;

public class Client {
    private final Logger logger = LoggerFactory.getLogger(Client.class.getName());
    private String name;
    private String surname;
    private String address;
    private int postalCode;

    public Client(final String name,
                  final String surname,
                  final String address,
                  final int postalCode) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.postalCode = postalCode;
        logger.log("New Client Created: " + this);
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



    @Override
    public String toString() {
        return String.format("Nom: %s%sPrénom: %s%sAdresse: %s%sCode Postal: %d",
                this.name, System.lineSeparator(),
                this.surname, System.lineSeparator(),
                this.address, System.lineSeparator(),
                this.postalCode);
    }
}
