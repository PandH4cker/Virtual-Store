package store.business.util.client;

public class Client {
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
    }
}
