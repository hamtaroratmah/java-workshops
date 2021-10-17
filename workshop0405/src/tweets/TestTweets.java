package tweets;

import lambda.Lambda;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public class TestTweets {


    private static List<Tweet> tweets;

    public static void main(String[] args) {

        User jean = new User("@sniperz", "Jean Guy", "Bruxelles");
        User myriam = new User("@mimi14", "Myriam Lois", "Namur");
        User tina = new User("@ting#12", "Tina Nguyen", "Bruxelles");
        User jorge = new User("@pesquej", "Jorge Abasolo", "Bruges");

        tweets = Arrays.asList(
            new Tweet("En plein Atelier Java oklm", LocalDateTime.now(), jean, 21),
            new Tweet("Venez voir ma nouvelle collection de figurines sur https://necliquezpassurcelien.be", LocalDateTime.of(2021, 9, 15, 23, 14), myriam, 2341),
            new Tweet("Pourquoi on doit toujours commencer les cours à 8h30 ? :(", LocalDateTime.of(2021, 8, 23, 14, 52), tina, 24),
            new Tweet("El pescador esta en el bote", LocalDateTime.of(2021, 9, 20, 7, 12), jorge, 367),
            new Tweet("Heureusement que j'ai lu la théorie avant *winkn wink*", LocalDateTime.now().plusMinutes(35), jean, 34),
            new Tweet("Ce soir à 22h30, live peinture avec @ting#1247!!!", LocalDateTime.of(2021, 8, 29, 17, 23), myriam, 1546),
            new Tweet("Donde esta mi navo?", LocalDateTime.of(2021, 9, 20, 7, 22), jorge, 461)
        );

//        tweetsABruxelles();
//        tweetsEnSeptembre2021Chronologique();
//        retweet2000Plus();
//        tweetsUtilisateurBruxellesParRetweet();
//        textesOrdreAlphabetique();
//        tweetLePlusRetweete();
        tagsOrdreAlphabetique();
//        nombreTweetsPlusQue100Retweets();
    }


    /**
     * Print une liste de tous les tweets dont l'auteur se trouve à Bruxelles
     */
    private static void tweetsABruxelles() {
        // filtrer les tweet selon la localisation de l'auteur
        List<Tweet> tweetList = tweets.stream().filter(user->"Bruxelles".equals(user.getAuthor().getLocation())).collect(Collectors.toList());
        for (Tweet temp : tweetList) {
            System.out.println(temp.toString() + "\n\n");
       }
    }

    /**
     * print une liste de tous les tweets publiés en septembre 2021, triès par ordre chronologique
     */
    private static void tweetsEnSeptembre2021Chronologique() {
        // filtrer les tweets selon le mois de la date, puis les trier
        Predicate<Tweet> sept21 = tweet -> tweet.getPublicationDate().matches(".?\\/(0)?9\\/2021.?");
        List<Tweet> tweetList = tweets.stream().filter(sept21).sorted().collect(Collectors.toList());
        for (Tweet temp : tweetList) {
            System.out.println(temp.toString() + "\n\n");
        }
    }

    /**
     * Print true si au moins un tweet a été retweeté 2000 fois ou +, false sinon
     */
    private static void retweet2000Plus() {
        // utiliser l'api stream pour vérifier si au moins un élément vérifie la condition
        List<Tweet> tweetList = tweets.stream().filter(tweet -> tweet.getRetweets()>2000).collect(Collectors.toList());
        for (Tweet temp : tweetList) {
            System.out.println(temp.toString() + "\n\n");
        }
    }

    /**
     * Print une liste de tous les tweet dont le user est à Bruxelles, triés par nombre
     * croissant de retweets
     */
    private static void tweetsUtilisateurBruxellesParRetweet() {
        // filtrer les tweets selon la localisation de l'auteur, puis les trier
        List<Tweet> tweetList = tweets.stream().filter(user->"Bruxelles".equals(user.getAuthor()
                .getLocation())).sorted(Comparator.comparing(Tweet::getRetweets)).collect(Collectors.toList());
        for (Tweet temp : tweetList) {
            System.out.println(temp.toString() + "\n\n");
        }
    }

    /**
     * Print tous les textes des tweets (dans une List<String>) dans l'ordre alphabétique
     */
    private static void textesOrdreAlphabetique(){
        // mapper le stream de Tweet vers un stream de String, puis trier
        List<Tweet> tweetList = tweets.stream().sorted(Comparator.comparing(Tweet::getText)).collect(Collectors.toList());
        for (Tweet temp : tweetList) {
            System.out.println(temp.toString() + "\n\n");
        }
    }

    /**
     * Print le tweet le plus retweeté
     */
    private static void tweetLePlusRetweete() {
        // s'aider de p.13-14 : "Réduire"
        Tweet tweet = tweets.stream().max(Comparator.comparing(Tweet::getRetweets)).get();
        System.out.printf(tweet.toString());
    }

    /**
     * 1. Print une String contenant tous les tags (chaque tag une fois) par ordre alphabétique,
     *    tous séparés par une virgule.
     * 2. même chose en retirant les @ en début de tag
     */
    private static void tagsOrdreAlphabetique() {
        //TODO: Pour l'unicité des tag, voir p.10 : "Filtrer et réduire un stream"
        //      Pour transformer en une seule String, voir p.15-16 : "Réduire et résumer"
        String fullTag = tweets.stream().map(Tweet::getAuthor).map(User::getTag).sorted().distinct().collect(joining(",")).replace("@", "");
        System.out.println(fullTag);
    }

    /**
     * Print le nombre de tweets qui ont été retweetés plus de 100 fois
     */
    private static void nombreTweetsPlusQue100Retweets() {
        // filtrer par retweets puis collecter et compter en s'aidant de p.15 : "Réduire et résumer"
        long nbTweet = tweets.stream().filter((a)->a.getRetweets()>1000).count();
        System.out.println(nbTweet);
    }


}
