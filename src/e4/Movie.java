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
    private final int rating;

    MovieRating(int i) {
        rating = i;
    }

    int getNumericRating() {
        return rating;
    }

    boolean isBetterThan(MovieRating movieRating) {
        return this.rating > movieRating.rating;
    }
}


public class Movie {
    // El nombre de la película no debería variar una vez creado su objeto asi que puede ser un final
    private final String title;

    List<MovieRating> list = new ArrayList<>();
    public Movie(String title){
        this.title = title;

    }


    public String getTitle() {
        return title;
    }


    public void insertRating(MovieRating movieRating) {
        this.list.add(movieRating);
    }

    private boolean isRated() {
        if(!list.isEmpty()){
            for (MovieRating rating : list) {
                if(rating != MovieRating.NOT_RATED) return true;
            }
        }
        return false; // Solo si está vacía o en la lista solo hay NOT_RATED
    }

    public MovieRating maximumRating() {
        if(isRated()){
            MovieRating maximum = MovieRating.NOT_RATED;
            for (MovieRating rating : list) {
                if(maximum.getNumericRating() <= rating.getNumericRating()) maximum = rating;
            }
            return maximum;
        }else return MovieRating.NOT_RATED;
    }

    public double averageRating() {
        if(!isRated()) throw new java.util.NoSuchElementException("No ratings in the list");
        else{
            int average = 0;
            int cont = 0;
            for (MovieRating rating : list) {
                if(rating != MovieRating.NOT_RATED){
                    average = average + rating.getNumericRating();
                    cont += 1;
                }
            }
            return average/cont;
        }
    }



    public static void main(String[] args) {
        Movie ee = new Movie("Peli");
        ee.insertRating(MovieRating.NOT_RATED);
        ee.insertRating(MovieRating.MASTERPIECE);
        ee.insertRating(MovieRating.AWFUL);
        System.out.println(ee.isRated());
        System.out.println(ee.getTitle());
        System.out.println(ee.maximumRating());
        MovieRating t = ee.maximumRating();
        System.out.println(t);
        t = MovieRating.MEDIOCRE;
        System.out.println(t);
        System.out.println(ee.maximumRating());
        System.out.println(ee.averageRating());
        System.out.println(MovieRating.MASTERPIECE.getNumericRating());

    }
}


