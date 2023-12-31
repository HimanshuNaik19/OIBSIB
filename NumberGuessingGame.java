import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int lowerBound = 1;
        int upperBound = 500;
        int numberToGuess = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
        int numberOfTries = 0;
        boolean hasGuessedCorrectly = false;

        System.out.println("......Welcome to the Number Guessing Game!......");
        System.out.println(" There is a selection of random number between " + lowerBound + " and " +
                upperBound + ". Try to guess it.");

        while (!hasGuessedCorrectly) {
            System.out.print("Try to guess the number: ");
            int userGuess = scanner.nextInt();
            numberOfTries++;

            if (userGuess < numberToGuess) {
                System.out.println(" Number is higher, Try to guess it again.");
            } else if (userGuess > numberToGuess) {
                System.out.println(" Number is lower,Try to guess it again.");
            } else {
                hasGuessedCorrectly = true;
                System.out.println("Congratulations! You've guessed the number Correctly and it is " +
                        numberToGuess + " in " + numberOfTries + " tries.");
            }
        }
        scanner.close();

    }
}
