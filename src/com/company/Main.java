package com.company;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Main {

    public static void out(ZeroArgument zeroArgument) {
        zeroArgument.myFunction();
    }

    public static void out(OneArgument oneArgument, String str) {
        oneArgument.myFunction(str);
    }

    static int valutaBeregner(List<Integer> list, TwoArgument multiply, int exchangeRate) {
        int sum = 0;
        for (int t: list) {
            sum += multiply.myFunction(t,exchangeRate);
        }
        return sum;
    }

    static int sumString(List<String> list, StringFunction function) {
        int sum = 0;
        for (String str : list) {
            sum += function.calculate(str);
        }
        return sum;
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

        //Beregning af valuta
        //Tal og Kurs heltal
        TwoArgument twoArgument = (x,y) -> x*y;

        List<Integer> dollars = Arrays.asList(1,2,2,5,10);
        int sum = valutaBeregner(dollars, twoArgument, 6);
        System.out.println(sum);

        //Opgave lav valuta regner med både int og double




        // Tag collection af strenge og omdan til int
        List<String> stringlist = Arrays.asList("Hejsa", "du", "er", "sød");

        StringFunction stringFunction = s -> s.length();
        int listeSum = sumString(stringlist, stringFunction);
        System.out.println(listeSum);

        //Leg med stream - sjov og ballade

    }
}