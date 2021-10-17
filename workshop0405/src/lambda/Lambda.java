package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Lambda {

    /**
     * Retourne une liste contenant uniquement les Integer qui correspondent
     * au predicat match
     * @param list La liste d'Integer originale
     * @param match le predicat à respecter
     * @return une liste contenant les integer qui respectent match
     */
    public static <E> List<E> allMatches(List<E> list, Predicate<E> match) {
        List<E> returnList = new ArrayList<E>();
        for (E predicat : list) {
            if (match.test(predicat)) returnList.add(predicat);
        }
        return returnList;
    }

    /**
     * Retourne une liste contenant tous les éléments de la liste originale, transformés
     * par la fonction transform
     * @param list La liste d'Integer originale
     * @param transform la fonction à appliquer aux éléments
     * @return une liste contenant les integer transformés par transform
     */
    public static <E,S> List<S> transformAll(List<E> list, Function<E, S> transform) {
        List<S> returnList = new ArrayList<>();
        for (E num : list) {
            returnList.add(transform.apply(num));
        }
        return returnList;
    }

    public static <E> List<E> filter(List<E> list, Predicate<E> transform){
        return list.stream().filter(transform).collect(Collectors.toList());
    }

    public static <E,S> List<S> map(List<E> list, Function<E, S> transform) {
        return list.stream().map(transform).collect(Collectors.toList());
    }

}
