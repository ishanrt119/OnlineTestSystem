import java.util.ArrayList;
import java.util.Scanner;

public class QuizApp {
    private static final DatabaseManager dbManager = new DatabaseManager();
    private static int score = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Quiz Application!");
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        dbManager.addUser(username);

        ArrayList<Question> questions = dbManager.getQuestions();
        if (questions.isEmpty()) {
            System.out.println("No questions available in the database.");
            return;
        }

        for (Question question : questions) {
            askQuestion(question, scanner);
        }

        System.out.println("Your score: " + score + "/" + questions.size());
        dbManager.saveResult(1, score); // replace 1 with user ID logic if needed
        System.out.println("Thanks for playing!");
    }

    private static void askQuestion(Question question, Scanner scanner) {
        System.out.println(question.getQuestionText());
        System.out.println("1. " + question.getOption1());
        System.out.println("2. " + question.getOption2());
        System.out.println("3. " + question.getOption3());
        System.out.println("4. " + question.getOption4());
        System.out.print("Your answer (1-4): ");
        int answer = scanner.nextInt();

        if (answer == question.getCorrectOption()) {
            System.out.println("Correct!");
            score++;
        } else {
            System.out.println("Wrong! The correct answer was: " + question.getCorrectOption());
        }
    }
}
