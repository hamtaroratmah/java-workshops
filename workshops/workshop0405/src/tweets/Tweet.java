package tweets;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Tweet {

    private String text;
    private LocalDateTime publicationDate;
    private DateTimeFormatter dateTimeFormatter;
    private User author;
    private int retweets;

    public Tweet(String text, LocalDateTime publicationDate, User author, int retweets) {
        this.text = text;
        this.publicationDate = publicationDate;
        this.author = author;
        this.retweets = retweets;
        dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
    }

    public String getText() {
        return text;
    }

    public String getPublicationDate() {
        return dateTimeFormatter.format(publicationDate);
    }

    public User getAuthor() {
        return author;
    }

    public int getRetweets() {
        return retweets;
    }

    @Override
    public String toString() {
        return "Tweet" +
                "\ntext='" + text + '\'' +
                "\npublicationDate= " + dateTimeFormatter.format(publicationDate) +
                "\nuser= " + author +
                "\nretweets= " + retweets;
    }
}
