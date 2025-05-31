package Movie_Tracker.models;

/**
 * Abstract base class representing a generic media item (Movie or TV Show).
 * Contains common attributes and abstract methods for status management.
 */
public abstract class MediaItem {
    protected String title;
    protected String genre;
    protected String platform;
    protected int rating; // Default 0 if not rated, range 1-5

    /**
     * Constructor for MediaItem.
     * @param title The title of the media item.
     * @param genre The genre of the media item.
     * @param platform The platform where the media item is available.
     */
    public MediaItem(String title, String genre, String platform) {
        this.title = title;
        this.genre = genre;
        this.platform = platform;
        this.rating = 0; // Initialize rating to 0 (not rated)
    }

    // --- Getters ---
    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getPlatform() {
        return platform;
    }

    public int getRating() {
        return rating;
    }

    // --- Setters ---
    /**
     * Sets the rating for the media item.
     * @param rating The rating value (e.g., 1-5).
     */
    public void setRating(int rating) {
        if (rating >= 0 && rating <= 5) { // Allow 0 for unrated, 1-5 for actual ratings
            this.rating = rating;
        } else {
            System.err.println("Invalid rating. Rating must be between 0 and 5.");
        }
    }

    /**
     * Abstract method to set the specific status of the media item.
     * This forces concrete subclasses (Movie, TVShow) to implement their own status logic.
     * @param status The status object (e.g., MovieStatus or TVShowStatus enum).
     */
    public abstract void setStatus(Object status); // Using Object for polymorphism, cast in subclasses

    /**
     * Abstract method to get the specific status of the media item.
     * This forces concrete subclasses (Movie, TVShow) to implement their own status retrieval logic.
     * @return The status object (e.g., MovieStatus or TVShowStatus enum).
     */
    public abstract Object getStatus(); // Using Object for polymorphism, cast in subclasses

    /**
     * Provides a basic string representation of the media item.
     * Subclasses should override this for more specific details.
     * @return A string containing common media item details.
     */
    @Override
    public String toString() {
        return "Title: " + title +
               ", Genre: " + genre +
               ", Platform: " + platform +
               ", Rating: " + (rating == 0 ? "Not Rated" : rating);
    }
}