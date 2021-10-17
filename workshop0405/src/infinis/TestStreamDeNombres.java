package infinis;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestStreamDeNombres {

    private static double[] doubles;

    public static void main(String[] args) {
        doubles = new Random().doubles(10,0,100).toArray();

        System.out.println("1. Moyenne des racines carrée = "  + moyenneDesRacinesCarrees());
        System.out.println("\n2. Génération de streams infinis de doubles");
        listeDe10PuisTabDe20DoubleStream();
        listeDe10PuisTabDe20StreamDouble();
        System.out.println("\n3. 20 nombres entiers pairs entre 0 et 100");
        System.out.println(
//                TODO: générer le stream ici (faites d'abord les points 1. et 2.)
                IntStream.generate(()->(int) (Math.random()*50)*2).limit(20).boxed().collect(Collectors.toList())
        );

    }

    /**
     * cf. point 1
     */
    private static double moyenneDesRacinesCarrees() {
        //: utilisez DoubleStream.of(double... values)
        return DoubleStream.of(doubles).map(m->Math.sqrt(m)).average().getAsDouble();
    }

    /**
     * cf. point 2
     */
    private static DoubleStream randomDoubleStream(double maxValue) {
        //: utiliser la fonction generate() de la classe DoubleStream
        return DoubleStream.generate(new Random((long) maxValue)::nextDouble).map(x->x*maxValue);
    }

    /**
     * cf. point 2
     */
    private static Stream<Double> randomStreamDouble(double maxValue) {
        //: utiliser la fonction generate() de la classe Stream
        return Stream.generate(new Random((long) maxValue)::nextDouble).map(x->x*maxValue);
    }

    /**
     * cf. point 2
     */
    private static void listeDe10PuisTabDe20DoubleStream() {
        List<Double> numsList = randomDoubleStream(100).limit(10).boxed().collect(Collectors.toList());
        //: utiliser randomDoubleStream pour initialiser numsList
        //      note : il est normal que que le collect(Collectors.toList())
        //      fonctionne pas comme d'habitude ;)
        System.out.println(numsList);

        double[] numTab = randomDoubleStream(100).limit(20).toArray();
        //: utiliser randomDoubleStream pour initialiser numsTab
        System.out.print("{ ");
        for(double d : numTab) System.out.print(d + " ");
        System.out.println("}");
    }

    /**
     * cf. point 2
     */
    private static void listeDe10PuisTabDe20StreamDouble() {
        List<Double> numsList = randomStreamDouble(100).limit(10).collect(Collectors.toList());
        //: utiliser randomStreamDouble pour initialiser numsList
        System.out.println(numsList);

        double[] numTab = randomStreamDouble(100).limit(20).mapToDouble(x->x).toArray();
        //: utiliser randomStreamDouble pour initialiser numsTab
        //      note : il est normal que que le toArray ne fonctionne
        //      pas comme d'habitude ;)
        System.out.print("{ ");
        for(double d : numTab) System.out.print(d + " ");
        System.out.println("}");
    }

}
