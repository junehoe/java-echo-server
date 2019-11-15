package echoserver;

import java.util.Scanner;

public class App {

    public String getGreeting() {
        return "Hello world.";
    }

    public void printHelloWorld() {
        System.out.println("Hello World!");
    }

    public String getInput() {
        Scanner scan = new Scanner(System.in);
        String s = scan.next();
        scan.close();
        return s;
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
    }
}