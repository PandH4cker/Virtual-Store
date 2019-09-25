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
        return  this.name
                +"\n" +this.surname
                +"\n" +this.address
                +"\n" +this.postalCode;
    }
}
