import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApplication {
    private static int score = 0;
    private static int questionIndex = 0;

    private static String[] questions = {
        "What is the capital of France?",
        "Which planet is known as the Red Planet?",
        "What is the largest mammal?",
        "Who wrote 'Romeo and Juliet'?",
        "What is the chemical symbol for water?"
    };

    private static String[][] options = {
        {"Berlin", "Madrid", "Paris", "Rome"},
        {"Earth", "Mars", "Venus", "Jupiter"},
        {"Elephant", "Blue Whale", "Giraffe", "Lion"},
        {"Charles Dickens", "Jane Austen", "William Shakespeare", "Mark Twain"},
        {"H2O", "CO2", "O2", "N2"}
    };

    private static int[] correctAnswers = {2, 1, 1, 2, 0}; // Index of correct option for each question

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Quiz Application!");

        Timer timer = new Timer();

        for (int i = 0; i < questions.length; i++) {
            presentQuestion(i);

            // Set a timer for 15 seconds (15000 milliseconds)
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("\nTime's up! Moving to the next question.");
                    submitAnswer(-1); // Submitting a default answer when time is up
                }
            }, 15000);

            int userAnswer = scanner.nextInt();
            timer.cancel(); // Cancel the timer

            submitAnswer(userAnswer);
        }

        // Display results
        System.out.println("\nQuiz completed!");
        System.out.println("Your final score: " + score + "/" + questions.length);
        scanner.close();
    }

    private static void presentQuestion(int index) {
        System.out.println("\nQuestion " + (index + 1) + ": " + questions[index]);
        for (int i = 0; i < options[index].length; i++) {
            System.out.println((i + 1) + ". " + options[index][i]);
        }
        System.out.print("Your answer (1-" + options[index].length + "): ");
    }

    private static void submitAnswer(int userAnswer) {
        if (userAnswer >= 1 && userAnswer <= options[questionIndex].length) {
            if (userAnswer - 1 == correctAnswers[questionIndex]) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect! The correct answer is: " + options[questionIndex][correctAnswers[questionIndex]]);
            }
        } else {
            System.out.println("Invalid input. Skipping to the next question.");
        }

        questionIndex++;
    }
}
