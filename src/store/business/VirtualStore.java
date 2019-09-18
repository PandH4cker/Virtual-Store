package store.business;

import store.business.gui.Table;
import store.business.util.product.ProductParser;

import java.util.Scanner;

public class VirtualStore {
    public VirtualStore() {
        /*Scanner scan = new Scanner(System.in);
        System.out.println("===VIRTUAL STORE===");
        System.out.println("1. Print Clients List");
        System.out.println("2. Print Products List");
        System.out.println("3. Create A New Client");
        System.out.println("4. Print Transactions List");
        System.out.println("5. Quit");
        System.out.println("Choose your option: ");
        switch (scan.next().charAt(0)) {
            case '1':
                //TODO Implement me
                break;
            case '2':
                new ProductParser();
                break;
            case '3':
                //TODO Implement me
                break;
            case '4':
                //TODO Implement me
                break;
            case '5':
                System.exit(0);
                break;
        }*/
        Table.get();
    }

    public static void main(String[] args) {
        new VirtualStore();
    }
}
