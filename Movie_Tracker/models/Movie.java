package Movie_Tracker.models;

import Movie_Tracker.enums.MovieStatus;

/**
 * Represents a Movie, extending the abstract MediaItem class.
 */
public class Movie extends MediaItem {
    private int releaseYear;
    private MovieStatus status;

    /**
     * Constructor for Movie.
     * @param title The title of the movie.
     * @param genre The genre of the movie.
     * @param releaseYear The release year of the movie.
     * @param status The current status of the movie (WATCHED or TO_WATCH).
     * @param platform The platform where the movie is available.
     */
    public Movie(String title, String genre, int releaseYear, MovieStatus status, String platform) {
        super(title, genre, platform); // Call parent constructor
        this.releaseYear = releaseYear;
        this.status = status;
    }

    // --- Getters ---
    public int getReleaseYear() {
        return releaseYear;
    }

    public MovieStatus getStatus() {
        return status;
    }

    // --- Setters ---
    /**
     * Sets the status of the movie.
     * Overrides the abstract setStatus from MediaItem.
     * @param status An Object, expected to be a MovieStatus enum value.
     */
    @Override
    public void setStatus(Object status) {
        if (status instanceof MovieStatus) {
            this.status = (MovieStatus) status;
        } else {
            System.err.println("Invalid status type for Movie. Expected MovieStatus.");
        }
    }

    /**
     * Provides a detailed string representation of the Movie.
     * @return A string containing movie details.
     */
    @Override
    public String toString() {
        return "Movie - " + super.toString() +
               ", Release Year: " + releaseYear +
               ", Status: " + status;
    }
}