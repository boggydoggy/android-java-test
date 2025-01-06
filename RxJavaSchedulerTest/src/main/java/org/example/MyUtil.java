package org.example;

public class MyUtil {
    static void printData(String msg) {
        System.out.println(Thread.currentThread().getName() + " | " + msg);
    }

    static void printData(String msg, Object obj) {
        System.out.println(Thread.currentThread().getName() + " | " + msg + " | " + obj.toString());
    }
}
