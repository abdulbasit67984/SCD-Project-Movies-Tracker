package Movie_Tracker;

import Movie_Tracker.core.MediaTracker;
import Movie_Tracker.ui.ConsoleUI; 

/**
 * Main class for the Movie/TV Show Tracker application.
 * This class serves as the application's entry point and orchestrates
 * the start of the user interface.
 */
public class Main {
    public static void main(String[] args) {
        MediaTracker tracker = new MediaTracker(); // Initialize the core tracker
        ConsoleUI ui = new ConsoleUI(tracker);    // Create the UI instance
        ui.start();                               // Start the user interface
    }
}
