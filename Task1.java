import java.util.Random;
import java.util.Scanner;

public class Task1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int score = 0;
        boolean playAgain = true;

        System.out.println(" Welcome to the Number Guessing Game");

        while (playAgain) {
            int number = rand.nextInt(100) + 1;
            int attemptsLeft = 5;
            boolean guessedCorrectly = false;

            System.out.println("\nI have selected a number between 1 and 100.");
            System.out.println("You have " + attemptsLeft + " attempts to guess it.");

            while (attemptsLeft > 0) {
                System.out.print("Enter your guess: ");
                int guess = sc.nextInt();
                attemptsLeft--;

                if (guess == number) {
                    System.out.println("Correct You guessed the number.");
                    guessedCorrectly = true;
                    score++;
                    break;
                } else if (guess > number) {
                    System.out.println("Too High");
                } else {
                    System.out.println("Too Low");
                }

                System.out.println("Attempts left: " + attemptsLeft);
            }

            if (!guessedCorrectly) {
                System.out.println("Out of attempts The number was: " + number);
            }

            System.out.println("Current Score: " + score);

            System.out.print("\nDo you want to play again? (yes/no): ");
            String choice = sc.next().toLowerCase();
            playAgain = choice.equals("yes");
        }

        System.out.println("\nThanks for playing Final Score: " + score);
        sc.close();
    }
}
