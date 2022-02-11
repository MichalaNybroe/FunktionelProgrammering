package com.company;

public class Main {

    public static void out(ZeroArgument zeroArgument) {
        zeroArgument.myFunction();
    }

    public static void out(OneArgument oneArgument, String str) {
        oneArgument.myFunction(str);
    }

    public static void main(String[] args) {
        ZeroArgument z =() -> System.out.println("Hello I am an Interface");
        out(z);

        // Ved et argument behøves paranteser i lambda ikke - to eksempler nedenfor
        OneArgument one = str -> System.out.println(str);
        OneArgument one2 = (str) -> System.out.println(str);
        out(one, "hej");
        out(one2, "hej2");

        TwoArgument multiply = (x,y) -> x*y;
        int total = multiply.myFunction(5,6);
        System.out.println(total);
    }
}