package Movie_Tracker.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Utility class for handling user input from the console.
 * Centralizes input reading and validation.
 */
public class InputHandler {
    private Scanner scanner;

    public InputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Gets a string input from the user.
     * @param prompt The message to display to the user.
     * @return The string input.
     */
    public String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    /**
     * Gets an integer input from the user, ensuring valid integer input.
     * @param prompt The message to display to the user.
     * @return The integer input.
     */
    public int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int input = scanner.nextInt();
                scanner.nextLine(); // Consume newline left-over
                return input;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Consume the invalid input
            }
        }
    }

    /**
     * Gets an enum value from the user, based on provided enum class.
     * @param <T> The enum type.
     * @param prompt The message to display to the user.
     * @param enumClass The Class object of the enum (e.g., MovieStatus.class).
     * @return The selected enum value.
     */
    public <T extends Enum<T>> T getEnumInput(String prompt, Class<T> enumClass) {
        T value = null;
        while (value == null) {
            System.out.print(prompt + " (" + java.util.Arrays.toString(enumClass.getEnumConstants()) + "): ");
            String inputStr = scanner.nextLine().trim().toUpperCase();
            try {
                value = Enum.valueOf(enumClass, inputStr);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please choose from the given options.");
            }
        }
        return value;
    }
}