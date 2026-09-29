public class Cart {
    public static final int MAX_NUMBERS_ORDERED = 20;

    private DigitalVideoDisc itemsOrdered[] = new DigitalVideoDisc[MAX_NUMBERS_ORDERED];
    private int qtyOrdered = 0;

    public int getQtyOrdered() {
        return qtyOrdered;
    }

    // Add one DVD to the cart
    public void addDigitalVideoDisc(DigitalVideoDisc disc) {
        if (disc == null) {
            System.out.println("Invalid disc, nothing was added.");
            return;
        }
        if (qtyOrdered >= MAX_NUMBERS_ORDERED) {
            System.out.println("The cart is almost full. Cannot add \"" + disc.getTitle() + "\".");
            return;
        }
        itemsOrdered[qtyOrdered] = disc;
        qtyOrdered++;
        System.out.println("The disc \"" + disc.getTitle() + "\" has been added.");
        if (qtyOrdered == MAX_NUMBERS_ORDERED) {
            System.out.println("The cart is almost full.");
        }
    }

    // 14.1 Overloading by differing types of parameter: add a list of DVDs
    public void addDigitalVideoDisc(DigitalVideoDisc[] dvdList) {
        if (dvdList == null) {
            System.out.println("Invalid list, nothing was added.");
            return;
        }
        for (DigitalVideoDisc disc : dvdList) {
            addDigitalVideoDisc(disc);
        }
    }

    /*
     * 14.1 Arbitrary number of arguments (varargs). It cannot live together with
     * addDigitalVideoDisc(DigitalVideoDisc[] dvdList): for the compiler,
     * DigitalVideoDisc... and DigitalVideoDisc[] are the same parameter type, so the
     * class would not compile ("cannot declare both ... in Cart"). See answers.txt.
     *
     * public void addDigitalVideoDisc(DigitalVideoDisc... dvdList) {
     *     for (DigitalVideoDisc disc : dvdList) {
     *         addDigitalVideoDisc(disc);
     *     }
     * }
     */

    // 14.2 Overloading by differing the number of parameters
    public void addDigitalVideoDisc(DigitalVideoDisc dvd1, DigitalVideoDisc dvd2) {
        addDigitalVideoDisc(dvd1);
        addDigitalVideoDisc(dvd2);
    }

    // Remove the DVD passed by argument from the cart
    public void removeDigitalVideoDisc(DigitalVideoDisc disc) {
        for (int i = 0; i < qtyOrdered; i++) {
            if (itemsOrdered[i] == disc) {
                // shift the remaining items one position to the left
                for (int j = i; j < qtyOrdered - 1; j++) {
                    itemsOrdered[j] = itemsOrdered[j + 1];
                }
                itemsOrdered[qtyOrdered - 1] = null;
                qtyOrdered--;
                System.out.println("The disc \"" + disc.getTitle() + "\" has been removed.");
                return;
            }
        }
        System.out.println("The disc "
                + (disc == null ? "null" : "\"" + disc.getTitle() + "\"")
                + " is not in the cart.");
    }

    // Sum of the costs of all DVDs in the cart
    public float totalCost() {
        float total = 0;
        for (int i = 0; i < qtyOrdered; i++) {
            total += itemsOrdered[i].getCost();
        }
        return total;
    }

    // Display the cart items (sequence number, title, cost) and the total cost
    public void print() {
        System.out.println("***********************CART***********************");
        if (qtyOrdered == 0) {
            System.out.println("The cart is empty.");
        }
        for (int i = 0; i < qtyOrdered; i++) {
            System.out.printf("%-4d%-25s%10.2f%n", i + 1, itemsOrdered[i].getTitle(), itemsOrdered[i].getCost());
        }
        System.out.printf("    %-25s%10.2f%n", "Total Cost", totalCost());
        System.out.println("**************************************************");
    }
}
