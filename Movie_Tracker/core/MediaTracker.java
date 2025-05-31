package Movie_Tracker.core;

import Movie_Tracker.enums.MovieStatus;
import Movie_Tracker.enums.TVShowStatus;
import Movie_Tracker.models.MediaItem;
import Movie_Tracker.models.Movie;
import Movie_Tracker.models.TVShow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Manages the collection of MediaItems (Movies and TV Shows) and provides
 * core functionalities for adding, viewing, updating, rating, and searching/filtering.
 */
public class MediaTracker {
    private List<MediaItem> mediaItems;

    /**
     * Constructor for MediaTracker. Initializes the list of media items.
     */
    public MediaTracker() {
        this.mediaItems = new ArrayList<>();
    }

    /**
     * Adds a MediaItem (Movie or TVShow) to the tracker.
     * @param item The MediaItem to add.
     */
    public void addMediaItem(MediaItem item) {
        this.mediaItems.add(item);
    }

    /**
     * Gets all MediaItems currently tracked.
     * @return A List of MediaItems.
     */
    public List<MediaItem> getAllMediaItems() {
        return new ArrayList<>(this.mediaItems); // Return a copy to prevent external modification
    }

    /**
     * Finds a MediaItem by its title.
     * @param title The title to search for.
     * @return The MediaItem if found, or null if not found.
     */
    public MediaItem findMediaItemByTitle(String title) {
        for (MediaItem item : mediaItems) {
            if (item.getTitle().equalsIgnoreCase(title)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Updates the status of a Movie.
     * @param title The title of the movie to update.
     * @param newStatus The new MovieStatus.
     * @return true if the movie was found and updated, false otherwise.
     */
    public boolean updateMovieStatus(String title, MovieStatus newStatus) {
        MediaItem item = findMediaItemByTitle(title);
        if (item instanceof Movie) {
            ((Movie) item).setStatus(newStatus);
            return true;
        }
        return false;
    }

    /**
     * Updates the status and episodes watched for a TVShow.
     * @param title The title of the TV show to update.
     * @param newStatus The new TVShowStatus.
     * @param episodesWatched The new number of episodes watched.
     * @return true if the TV show was found and updated, false otherwise.
     */
    public boolean updateTVShowStatus(String title, TVShowStatus newStatus, int episodesWatched) {
        MediaItem item = findMediaItemByTitle(title);
        if (item instanceof TVShow) {
            TVShow tvShow = (TVShow) item;
            tvShow.setStatus(newStatus);
            tvShow.setEpisodesWatched(episodesWatched);
            return true;
        }
        return false;
    }

    /**
     * Rates a MediaItem.  Only allows rating if the item is considered "watched".
     * @param title The title of the media to rate.
     * @param rating The rating value (1-5).
     * @return true if the media was rated, false otherwise (e.g., if not found or not watched).
     */
    public boolean rateMediaItem(String title, int rating) {
        MediaItem item = findMediaItemByTitle(title);
        if (item != null) {
            if (item instanceof Movie) {
                if (((Movie) item).getStatus() == MovieStatus.WATCHED) {
                    item.setRating(rating);
                    return true;
                }
            } else if (item instanceof TVShow) {
                if (((TVShow) item).getOverallStatus() == TVShowStatus.COMPLETED) {
                     item.setRating(rating);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Searches for MediaItems containing the given keyword in their title or genre.
     * @param keyword The keyword to search for.
     * @return A List of MediaItems that match the keyword.
     */
    public List<MediaItem> searchMediaItems(String keyword) {
        return mediaItems.stream()
                .filter(item -> item.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                                 item.getGenre().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

   /**
     * Filters MediaItems based on criteria provided in a Map.
     * Currently supports filtering by "status", "genre", and "platform".
     * @param criteria A Map where keys are filter names (e.g., "status", "genre")
     * and values are the filter values (e.g., "WATCHED", "Action").
     * @return A List of MediaItems that match the filter criteria.
     */
    public List<MediaItem> filterMediaItems(Map<String, String> criteria) {
        return mediaItems.stream()
                .filter(item -> {
                    for (Map.Entry<String, String> entry : criteria.entrySet()) {
                        String filterName = entry.getKey().toLowerCase();
                        String filterValue = entry.getValue().toLowerCase();

                        switch (filterName) {
                            case "status":
                                if (item instanceof Movie) {
                                    return ((Movie) item).getStatus().toString().toLowerCase().equals(filterValue);
                                } else if (item instanceof TVShow) {
                                    return ((TVShow) item).getOverallStatus().toString().toLowerCase().equals(filterValue);
                                }
                                break;
                            case "genre":
                                return item.getGenre().toLowerCase().contains(filterValue);
                            case "platform":
                                return item.getPlatform().toLowerCase().equals(filterValue);
                        }
                    }
                    return true; // If no criteria, return all
                })
                .collect(Collectors.toList());
    }
}