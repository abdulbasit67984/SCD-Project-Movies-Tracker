package Movie_Tracker.ui;


import Movie_Tracker.core.MediaTracker;
import Movie_Tracker.enums.MovieStatus;
import Movie_Tracker.enums.TVShowStatus;
import Movie_Tracker.models.MediaItem;
import Movie_Tracker.models.Movie;
import Movie_Tracker.models.TVShow;
import Movie_Tracker.utils.InputHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Handles the command-line user interface for the Simple Media Tracker application.
 * Manages menus, user input, and displaying output.
 */
public class ConsoleUI {
    private MediaTracker tracker;
    private InputHandler inputHandler;
    private Scanner scanner;

    public ConsoleUI(MediaTracker tracker) {
        this.tracker = tracker;
        this.scanner = new Scanner(System.in);
        this.inputHandler = new InputHandler(scanner);
    }

    /**
     * Starts the main application loop and displays the menu.
     */
    public void start() {
        int choice;
        do {
            displayMenu();
            choice = inputHandler.getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    addMovie();
                    break;
                case 2:
                    addTVShow();
                    break;
                case 3:
                    viewAllMedia();
                    break;
                case 4:
                    updateMediaStatus();
                    break;
                case 5:
                    rateMedia();
                    break;
                case 6:
                    searchAndFilterMedia();
                    break;
                case 0:
                    System.out.println("Exiting application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        scanner.close(); // Close the scanner when done
    }

    /**
     * Displays the main menu options to the user.
     */
    private void displayMenu() {
        System.out.println("\n--- Simple Media Tracker Menu ---");
        System.out.println("1. Add a Movie");
        System.out.println("2. Add a TV Show");
        System.out.println("3. View All Media");
        System.out.println("4. Update Media Status");
        System.out.println("5. Rate Media");
        System.out.println("6. Search/Filter Media");
        System.out.println("0. Exit");
        System.out.println("---------------------------------");
    }

    /**
     * Handles the process of adding a new movie based on user input.
     */
    private void addMovie() {
        System.out.println("\n--- Add New Movie ---");
        String title = inputHandler.getStringInput("Title: ");
        String genre = inputHandler.getStringInput("Genre (e.g., Sci-Fi, Drama): ");
        int releaseYear = inputHandler.getIntInput("Release Year: ");
        MovieStatus status = inputHandler.getEnumInput("Status", MovieStatus.class);
        String platform = inputHandler.getStringInput("Platform (e.g., Netflix, Blu-ray): ");

        Movie movie = new Movie(title, genre, releaseYear, status, platform);
        tracker.addMediaItem(movie);
        System.out.println("Movie added successfully!");
    }

    /**
     * Handles the process of adding a new TV show based on user input.
     */
    private void addTVShow() {
        System.out.println("\n--- Add New TV Show ---");
        String title = inputHandler.getStringInput("Title: ");
        String genre = inputHandler.getStringInput("Genre (e.g., Drama, Comedy): ");
        int firstReleaseYear = inputHandler.getIntInput("First Release Year: ");
        TVShowStatus status = inputHandler.getEnumInput("Status", TVShowStatus.class);
        String platform = inputHandler.getStringInput("Platform (e.g., Hulu, HBO Max): ");
        int numberOfSeasons = inputHandler.getIntInput("Number of Seasons: ");
        int episodesWatched = inputHandler.getIntInput("Episodes Watched: ");

        TVShow tvShow = new TVShow(title, genre, firstReleaseYear, status, platform, numberOfSeasons, episodesWatched);
        tracker.addMediaItem(tvShow);
        System.out.println("TV Show added successfully!");
    }

    /**
     * Displays all media items currently tracked.
     */
    private void viewAllMedia() {
        System.out.println("\n--- All Tracked Media ---");
        List<MediaItem> allItems = tracker.getAllMediaItems();
        if (allItems.isEmpty()) {
            System.out.println("No media items tracked yet.");
            return;
        }
        for (int i = 0; i < allItems.size(); i++) {
            System.out.println((i + 1) + ". " + allItems.get(i));
        }
    }

    /**
     * Handles updating the status of a media item.
     */
    private void updateMediaStatus() {
        System.out.println("\n--- Update Media Status ---");
        String title = inputHandler.getStringInput("Enter the title of the media to update: ");

        MediaItem item = tracker.findMediaItemByTitle(title);
        if (item == null) {
            System.out.println("Media item with title '" + title + "' not found.");
            return;
        }

        if (item instanceof Movie) {
            MovieStatus newStatus = inputHandler.getEnumInput("Enter new status", MovieStatus.class);
            if (tracker.updateMovieStatus(title, newStatus)) {
                System.out.println("Movie status updated successfully!");
            } else {
                System.out.println("Failed to update movie status.");
            }
        } else if (item instanceof TVShow) {
            TVShowStatus newStatus = inputHandler.getEnumInput("Enter new overall status", TVShowStatus.class);
            int episodesWatched = inputHandler.getIntInput("Enter new episodes watched: ");
            if (tracker.updateTVShowStatus(title, newStatus, episodesWatched)) {
                System.out.println("TV Show status and episodes updated successfully!");
            } else {
                System.out.println("Failed to update TV Show status.");
            }
        }
    }

    /**
     * Handles rating a media item.
     */
    private void rateMedia() {
        System.out.println("\n--- Rate Media ---");
        String title = inputHandler.getStringInput("Enter the title of the media to rate: ");

        MediaItem item = tracker.findMediaItemByTitle(title);
        if (item == null) {
            System.out.println("Media item with title '" + title + "' not found.");
            return;
        }

        int rating = inputHandler.getIntInput("Enter your rating (1-5): ");
        if (rating < 1 || rating > 5) {
            System.out.println("Invalid rating. Rating must be between 1 and 5.");
            return;
        }

        if (tracker.rateMediaItem(title, rating)) {
            System.out.println("Media item rated successfully!");
        } else {
            System.out.println("Failed to rate media item. Ensure it's watched/completed.");
        }
    }

    /**
     * Handles search and filter operations.
     */
    private void searchAndFilterMedia() {
        System.out.println("\n--- Search/Filter Media ---");
        System.out.println("1. Search by Keyword (Title/Genre)");
        System.out.println("2. Filter by Status");
        System.out.println("3. Filter by Genre");
        System.out.println("4. Filter by Platform");
        int choice = inputHandler.getIntInput("Choose an option: ");

        List<MediaItem> results = null;
        Map<String, String> criteria = new HashMap<>();

        switch (choice) {
            case 1:
                String keyword = inputHandler.getStringInput("Enter keyword: ");
                results = tracker.searchMediaItems(keyword); // Direct search method
                break;
            case 2:
                System.out.println("Filter by which status?");
                System.out.println("  (Movie: WATCHED, TO_WATCH / TV Show: WATCHING, COMPLETED, TO_WATCH)");
                String statusString = inputHandler.getStringInput("Enter status: ");
                criteria.put("status", statusString);
                results = tracker.filterMediaItems(criteria);
                break;
            case 3:
                String genre = inputHandler.getStringInput("Enter genre: ");
                criteria.put("genre", genre);
                results = tracker.filterMediaItems(criteria);
                break;
            case 4:
                String platform = inputHandler.getStringInput("Enter platform: ");
                criteria.put("platform", platform);
                results = tracker.filterMediaItems(criteria);
                break;
            default:
                System.out.println("Invalid filter option.");
                return;
        }

        System.out.println("\n--- Search/Filter Results ---");
        if (results == null || results.isEmpty()) {
            System.out.println("No media found matching your criteria.");
        } else {
            for (MediaItem item : results) {
                System.out.println(item);
            }
        }
    }
}