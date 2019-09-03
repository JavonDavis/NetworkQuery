package com.javondavis;

public class Main {

    public static void main(String[] args) {
        try {
            Network n = new Network(6);
            n.connect(1,4);
            n.connect(2,5);
            n.connect(1,2);
            n.connect(2, 1);
            n.connect(5, 4);
            n.connect(5, 5);

            System.out.println(n.query(5,5));
            System.out.println(n.query(1,4));
            System.out.println(n.query(2,5));
            System.out.println(n.query(1,5));
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

    }
}
