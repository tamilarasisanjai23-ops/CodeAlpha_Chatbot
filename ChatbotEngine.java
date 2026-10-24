
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class ChatbotEngine {

    private final Random random = new Random();

    public String getResponse(String input) {
        if (input == null || input.trim().isEmpty()) {
            return "Please enter a message.";
        }

        String message = input.toLowerCase().trim();

        if (message.matches(".*\\b(bye|goodbye|exit)\\b.*")) {
            return "Goodbye! Keep learning and have a great day!";
        }

        if (message.matches(".*\\b(hello|hi|hey)\\b.*")) {
            return randomReply(
                "Hello! How can I help you?",
                "Hi there! What would you like to learn?",
                "Hey! Ask me about Java, SQL, DSA, or placements."
            );
        }

        if (message.contains("your name")
                || message.equals("name")) {
            return "I am Java Chatbot, your virtual assistant.";
        }

        if (message.contains("how are you")) {
            return "I am working well! What would you like to learn today?";
        }

        if (message.contains("good morning")) {
            return "Good morning! Start your day with some coding practice.";
        }

        if (message.contains("good night")) {
            return "Good night! Rest well and keep learning tomorrow.";
        }

        if (message.contains("thank")) {
            return "You are welcome! Happy to help.";
        }

        if (message.contains("date")) {
            return "Today's date is "
                    + LocalDate.now().format(
                        DateTimeFormatter.ofPattern("dd-MM-yyyy")
                    ) + ".";
        }

        if (message.contains("time")) {
            return "Current time: "
                    + LocalTime.now().format(
                        DateTimeFormatter.ofPattern("hh:mm a")
                    ) + ".";
        }

        if (message.contains("oops")
                || message.contains("object oriented")
                || message.contains("class and object")) {
            return "OOP means Object-Oriented Programming. "
                 + "Its four main principles are Encapsulation, "
                 + "Inheritance, Polymorphism, and Abstraction.";
        }

        if (message.contains("inheritance")) {
            return "Inheritance allows one class to acquire properties "
                 + "and methods from another class using extends in Java.";
        }

        if (message.contains("loop")) {
            return "Java has for, while, and do-while loops. "
                 + "Loops repeat a block of code while a condition is met.";
        }

        if (message.contains("java")) {
            return "Java is an object-oriented programming language. "
                 + "Important topics include variables, loops, arrays, "
                 + "classes, objects, inheritance, and collections.";
        }

        if (message.contains("sql")) {
            return "SQL is used to manage relational databases. "
                 + "Start with SELECT, WHERE, ORDER BY, GROUP BY, "
                 + "JOIN, INSERT, UPDATE, and DELETE.";
        }

        if (message.contains("dbms") || message.contains("database")) {
            return "DBMS stands for Database Management System. "
                 + "It helps store, organize, retrieve, and manage data. "
                 + "Learn keys, normalization, relationships, and SQL.";
        }

        if (message.contains("dsa")
                || message.contains("data structure")
                || message.contains("algorithm")) {
            return "DSA means Data Structures and Algorithms. "
                 + "Start with arrays, strings, linked lists, stacks, "
                 + "queues, trees, searching, and sorting.";
        }

        if (message.contains("aptitude")) {
            return "Practice percentages, ratios, averages, time and work, "
                 + "speed and distance, number series, and logical reasoning.";
        }

        if (message.contains("placement")
                || message.contains("interview")
                || message.contains("job")) {
            return "For placements, practise Java, SQL, DSA, aptitude, "
                 + "communication, self-introduction, and project explanation.";
        }

        if (message.contains("project")) {
            return "Explain your project problem, objective, technologies, "
                 + "features, your contribution, and what you learned.";
        }

        if (message.contains("college")
                || message.contains("study")) {
            return "Make a daily study plan. Learn one concept, write code, "
                 + "test it, and revise what you learned.";
        }

        if (message.contains("help")
                || message.equals("commands")) {
            return "You can ask about Java, OOP, loops, SQL, DBMS, DSA, "
                 + "aptitude, placements, projects, date, or time.";
        }

        return randomReply(
            "I don't know that answer yet. Try asking about Java, SQL, or DSA.",
            "Could you ask that in a different way?",
            "I am a rule-based chatbot. Try a topic from the Help command."
        );
    }

    private String randomReply(String... replies) {
        return replies[random.nextInt(replies.length)];
    }
}
