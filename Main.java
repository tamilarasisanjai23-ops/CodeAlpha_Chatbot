
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("================================");
        System.out.println("     Welcome to Java Chatbot");
        System.out.println("================================");
        System.out.println("Bot: Hello! I am your chatbot.");
        System.out.println("Bot: Type 'bye' to exit.");

        while (true) {
            System.out.print("You: ");
            String message = sc.nextLine().toLowerCase();

            if (message.contains("bye")) {
                System.out.println("Bot: Goodbye! Have a nice day.");
                break;
            } else if (message.contains("hello") ||
                       message.contains("hi")) {
                System.out.println("Bot: Hello! How are you?");
            } else if (message.contains("name")) {
                System.out.println("Bot: I am Java Chatbot.");
                } else if (message.contains("thank")) {
    System.out.println("Bot: You are welcome!");
} else if (message.contains("good morning")) {
    System.out.println("Bot: Good morning! Have a great day!");
} else if (message.contains("college")) {
    System.out.println("Bot: Keep learning and achieve your goals!");
} else if (message.contains("how are you")) {
    System.out.println("Bot: I am fine. Thank you!");
            } else if (message.contains("help")) {
                System.out.println("Bot: I can answer simple questions.");
            } else if (message.contains("how are you")) {
                System.out.println("Bot: I am fine. Thank you!");
            } else {
                System.out.println("Bot: Sorry, I don't understand.");
            }
        }

        sc.close();
    }
}
