public class Aims {

    public static void main(String[] args) {
        // Create a new cart
        Cart anOrder = new Cart();

        // Create new dvd objects and add them to the cart
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King",
                "Animation", "Roger Allers", 87, 19.95f);
        anOrder.addDigitalVideoDisc(dvd1);

        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars",
                "Science Fiction", "George Lucas", 87, 24.95f);
        anOrder.addDigitalVideoDisc(dvd2);

        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin",
                "Animation", 18.99f);
        anOrder.addDigitalVideoDisc(dvd3);

        // Print the cart items and the total cost
        anOrder.print();
        System.out.println("Total Cost is: ");
        System.out.printf("%.2f%n", anOrder.totalCost());

        // 13. Removing items from the cart
        System.out.println();
        System.out.println("=== Test removeDigitalVideoDisc ===");
        anOrder.removeDigitalVideoDisc(dvd2);
        anOrder.print();
        anOrder.removeDigitalVideoDisc(dvd2); // no longer in the cart

        // 14.1 Overloading by differing types of parameter (array of DVDs)
        System.out.println();
        System.out.println("=== Test addDigitalVideoDisc(DigitalVideoDisc[]) ===");
        DigitalVideoDisc[] dvdList = {
                new DigitalVideoDisc("Frozen", "Animation", "Chris Buck", 102, 21.50f),
                new DigitalVideoDisc("Inception", "Science Fiction", "Christopher Nolan", 25.00f)
        };
        anOrder.addDigitalVideoDisc(dvdList);
        anOrder.print();

        // 14.2 Overloading by differing the number of parameters
        System.out.println();
        System.out.println("=== Test addDigitalVideoDisc(DigitalVideoDisc, DigitalVideoDisc) ===");
        anOrder.addDigitalVideoDisc(new DigitalVideoDisc("Up"), dvd2);
        anOrder.print();

        // Cart capacity check: fill the cart up to 20 items, then try one more
        System.out.println();
        System.out.println("=== Test full cart ===");
        while (anOrder.getQtyOrdered() < Cart.MAX_NUMBERS_ORDERED) {
            anOrder.addDigitalVideoDisc(new DigitalVideoDisc("Filler DVD", "Other", 1.00f));
        }
        anOrder.addDigitalVideoDisc(new DigitalVideoDisc("One too many"));

        // 16. Classifier member vs. instance member
        System.out.println();
        System.out.println("=== Test id / nbDigitalVideoDiscs ===");
        System.out.println("dvd1 id = " + dvd1.getId());
        System.out.println("dvd2 id = " + dvd2.getId());
        System.out.println("dvd3 id = " + dvd3.getId());
        System.out.println("Number of DVDs created: " + DigitalVideoDisc.getNbDigitalVideoDiscs());
    }
}
