package e4;

import java.util.ArrayList;
import java.util.List;

enum MovieRating {
    NOT_RATED(-1),
    AWFUL(0),
    BAD(2),
    MEDIOCRE(4),
    GOOD(6),
    EXCELLENT(8),
    MASTERPIECE(10);

    private int rating;

    MovieRating(int i) {
        this.rating = i;
    }

    int getNumericRating() {
        return this.rating;
    }

    boolean isBetterThan(MovieRating movieRating) {
        return this.rating > movieRating.rating;
    }
}


public class Movie {
    private String title;
    List<MovieRating> list = new ArrayList<>();
    public Movie(String title){
        this.title = title;

    }

    public String getTitle() {
        return this.title;
    }

    public static void main(String args[]) {
        MovieRating e = MovieRating.NOT_RATED;
        System.out.println(e.isBetterThan(MovieRating.AWFUL));
    }
}


