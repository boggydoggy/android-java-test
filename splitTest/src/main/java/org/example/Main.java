package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        String a = "  1:     lo     \n2:      dummy helloWorld  ";
        String[] parts1 = a.split(" +");
        String[] parts2 = a.split(" ");
        String[] parts3 = a.trim().split(" ");

        for (String string: parts1) {
            System.out.println(string);
        }
        System.out.println("**done**");
        for (String string: parts2) {
            System.out.println(string);
        }
        System.out.println("**done**");
        for (String string: parts3) {
            System.out.println(string);
        }
        System.out.println("**done**");
    }
}