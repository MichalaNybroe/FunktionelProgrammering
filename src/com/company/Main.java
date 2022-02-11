package com.company;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Main {

    // Brug af generic : parameter overfører en type
    public static <T> void out(T content) {
        System.out.println(content);
    }

    public static <T> void forEach(List<T> list, Consumer<T> consumer) {
        //list.foreach(consumer);
        for (T t : list) {
            consumer.accept(t);
        }
    }

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

        //Opgave G7
        List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K", "Canada");

        // 3 måder at skrive arrayen ud hvor de er omdannet til caps
        //Rasmus' udgave
        System.out.println(G7.toString().toUpperCase());

        // Eriks udgave
        Stream<String> collToStream = G7.stream();
        collToStream = collToStream.map(x -> x.toUpperCase(Locale.ROOT));
        collToStream.forEach(x -> System.out.println(x));

        //Alternativ til Eriks linje 73 -> linje 78
        Stream<String> collToStream2 = G7.stream();
        collToStream2 = collToStream2.map(String::toUpperCase);
        collToStream2.forEach(x -> System.out.println(x));


        //Brug af Consumer
        Consumer<String> consumer = x -> System.out.println(x.toLowerCase(Locale.ROOT));
        consumer.accept("FÆRDIG!");

        Consumer<Integer> consumerInteger = x -> System.out.println("x=" + x);
        consumerInteger.accept(10);

        List<Integer> list = Arrays.asList(1,2,3,4,5,33);
        forEach(list, consumerInteger);

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



        //test
        double dd = Math.random();
        Long ii = Math.round(dd * 100);
        System.out.println(ii);
        //test slut


        //Leg med stream - sjov og ballade
        Supplier<Double> randomGenerator = () -> Math.random();
        Stream<Double> doubles = Stream.generate(randomGenerator);

        Function<Double, Long> doubleRound = d -> Math.round(d*100);
        Predicate<Long> keepOrNot = l -> l % 2 == 0;
        Consumer<Long> print = i -> System.out.println("Random=" + i);
        Stream<Long> generatedValues = Stream.generate(randomGenerator).map(doubleRound);

        Stream<Long> keptValues;
       // keptValues = generatedValues.filter(keepOrNot); //Bliver ved og ved med at køre
        keptValues = generatedValues.filter(keepOrNot).limit(12);
        keptValues.forEach(print);

        // Kan ikke køre nedenstående når linje 133 er aktiv - fordi den lukker streamen - idet print er consumer
        //double sumFunction = keptValues.mapToDouble(d -> d).sum();
        //System.out.println(sumFunction);

        // Supplier = æbletræ, consumer = spiser æblet, Predicate = true or not. Csonsumer lukker streamen

        //Opgave 2 Lav ovenstående opgave om random generede task til en linje kode
    }
}