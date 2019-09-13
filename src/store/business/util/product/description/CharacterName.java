package store.business.util.product.description;

public class CharacterName implements Comparable<CharacterName> {
    private String name;
    private String surname;
    private int startComp;

    public CharacterName(final String name, final String surname) {
        this.name = name;
        this.surname = surname;
        this.startComp = 0;

        while((this.startComp < this.name.length())
                && (this.name.charAt(startComp)
                    == Character.toLowerCase(this.name.charAt(startComp)))) this.startComp++;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    @Override
    public int compareTo(CharacterName other) {
        if(other == null) return 1;
        int cmp = this.name.substring(this.startComp)
                           .compareTo(other.name.substring(other.startComp));

        if(cmp == 0) return this.surname.compareTo(other.surname);
        else return cmp;
    }

    @Override
    public String toString() {
        return this.name + " " + this.surname;
    }
}
