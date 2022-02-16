package com.company;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;
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
    static double valutaBeregner(List<Integer> list, TwoArgumentDouble multiply, double exchangerate) {
        double sum = 0;
        for(int t :list) {
            sum += multiply.myFunction(t, exchangerate);
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

        //Alternativ til Eriks linje 80 til linje 85
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
        TwoArgumentDouble twoArgumentDouble = (x,y) -> x*y;
        double dSum = valutaBeregner(dollars, twoArgumentDouble, 6.5);
        System.out.println(dSum);

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
        //Stream<Double> doubles = Stream.generate(randomGenerator);

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
        Stream
                .generate(Math::random)
                .map(d -> Math.round(d*100))
                .filter(l -> l % 2 == 0)
                .limit(12)
                .forEach(i -> System.out.println("Random=" + i));

        //Opgave 3: Udskriv alle tirsdage i marts måned 2022

        int lenghtOfMonth = YearMonth.of(2022, 3).lengthOfMonth();
        Collection<LocalDate> days = new ArrayList<>();

        for (int i = 1; i < lenghtOfMonth+1; i++) {
            days.add((YearMonth.of(2022, 3).atDay(i)));
        }

        Predicate<LocalDate> checkForTuesdays = x -> x.getDayOfWeek().equals(DayOfWeek.TUESDAY);
        days.stream().filter(checkForTuesdays).forEach(i -> System.out.println("Thuesday=" + i));

        //Opgave 3 clean
        IntStream
                .range(1, YearMonth.of(2022, 3).lengthOfMonth())
                .mapToObj(i -> YearMonth.of(2022, 3).atDay(i))
                .filter(x -> x.getDayOfWeek().equals(DayOfWeek.TUESDAY))
                .forEach(i -> System.out.println("Thuesday=" + i));

        Stream<LocalDate> thuesdaysInMonth = getThuesdaysInMonth(2022, 3);
        thuesdaysInMonth.forEach(i -> System.out.println("Thuesday = " + i));
    }

    public static Stream<LocalDate> getThuesdaysInMonth(int year, int month) {
        return IntStream
                .range(1, YearMonth.of(year, month).lengthOfMonth())
                .mapToObj(i -> YearMonth.of(year, month).atDay(i))
                .filter(x -> x.getDayOfWeek().equals(DayOfWeek.TUESDAY));
    }

    //lav det så man har en metode til at modtage alle dage i en måned, en til filtrering af dem til tirsdage og en til noget
    //med weekdays. se Eriks kode på github.


    //findes en parrallel stream som kan udføre flere funktioner på en stream samtidig.
}