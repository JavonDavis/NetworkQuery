package com.javondavis;

public class Main {

    public static void main(String[] args) {
        // Direct connections test
        try {
            Network n = new Network(6);
            n.connect(1,4);
            n.connect(2,5);
            n.connect(1,2);


            System.out.println(n.query(1, 4));
            System.out.println(n.query(1, 3));
            System.out.println(n.query(2, 5));
            System.out.println(n.query(2, 7));
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        // Indirect connections test
        try {
            Network n = new Network(6);
            n.connect(1,4);
            n.connect(2,5);
            n.connect(1,2);
            n.connect(4,5);
            n.connect(5,6);


            System.out.println(n.query(1, 5));
            System.out.println(n.query(1, 3));
            System.out.println(n.query(4, 3));
            System.out.println(n.query(6, 1));
            System.out.println(n.query(7, 3));
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        // Size bounds test
        try {
            Network n = new Network(6);
            n.connect(1,4);
            n.connect(2,5);
            n.connect(1,2);
            n.connect(4,5);
            n.connect(5,6);
            n.connect(5,7);

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        // Instantiation test
        try {
            Network n = new Network(0);

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        try {
            Network n = new Network(Integer.MAX_VALUE + 1); // catches overflow

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
