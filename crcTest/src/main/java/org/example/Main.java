package org.example;

public class Main {
    public static void main(String[] args) {
        long fwCrc = 2947040084L;
        long newCrc = 2947040084L;
        String hex = String.format("%x", fwCrc);

        System.out.println("long newCrc=" + newCrc + "int newCrc=" + (int) newCrc);
        System.out.println("long fwCrc=" + fwCrc + "hex fwCrc=" + hex);

        System.out.println(fwCrc == newCrc);
        System.out.println(fwCrc == (int) newCrc);
    }
}