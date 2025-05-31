package Movie_Tracker.models;

import Movie_Tracker.enums.TVShowStatus;

/**
 * Represents a TV Show, extending the abstract MediaItem class.
 */
public class TVShow extends MediaItem {
    private int firstReleaseYear;
    private TVShowStatus overallStatus;
    private int numberOfSeasons;
    private int episodesWatched;

    /**
     * Constructor for TVShow.
     * @param title The title of the TV show.
     * @param genre The genre of the TV show.
     * @param firstReleaseYear The year the TV show first premiered.
     * @param overallStatus The overall status of the TV show (WATCHING, COMPLETED, TO_WATCH).
     * @param platform The platform where the TV show is available.
     * @param numberOfSeasons The total number of seasons.
     * @param episodesWatched The number of episodes watched by the user.
     */
    public TVShow(String title, String genre, int firstReleaseYear, TVShowStatus overallStatus,
                  String platform, int numberOfSeasons, int episodesWatched) {
        super(title, genre, platform); // Call parent constructor
        this.firstReleaseYear = firstReleaseYear;
        this.overallStatus = overallStatus;
        this.numberOfSeasons = numberOfSeasons;
        this.episodesWatched = episodesWatched;
    }

    // --- Getters ---
    public int getFirstReleaseYear() {
        return firstReleaseYear;
    }

    public TVShowStatus getOverallStatus() {
        return overallStatus;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public int getEpisodesWatched() {
        return episodesWatched;
    }

    // --- Setters ---
    /**
     * Sets the overall status of the TV show.
     * Overrides the abstract setStatus from MediaItem.
     * @param status An Object, expected to be a TVShowStatus enum value.
     */
    @Override
    public void setStatus(Object status) {
        if (status instanceof TVShowStatus) {
            this.overallStatus = (TVShowStatus) status;
        } else {
            System.err.println("Invalid status type for TVShow. Expected TVShowStatus.");
        }
    }

    /**
     * Sets the number of episodes watched.
     * @param episodesWatched The updated count of episodes watched.
     */
    public void setEpisodesWatched(int episodesWatched) {
        if (episodesWatched >= 0) {
            this.episodesWatched = episodesWatched;
        } else {
            System.err.println("Episodes watched cannot be negative.");
        }
    }

    /**
     * Provides a detailed string representation of the TV Show.
     * @return A string containing TV show details.
     */
    @Override
    public String toString() {
        return "TV Show - " + super.toString() +
               ", First Release Year: " + firstReleaseYear +
               ", Overall Status: " + overallStatus +
               ", Seasons: " + numberOfSeasons +
               ", Episodes Watched: " + episodesWatched;
    }

    @Override
    public Object getStatus() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStatus'");
    }

}