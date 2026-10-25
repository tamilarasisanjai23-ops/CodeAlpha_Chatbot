
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class ChatbotEngine {

    private final Random random = new Random();

    public String getResponse(String input) {
        if (input == null || input.trim().isEmpty()) {
            return "Please enter a message.";
        }

        String message = input.toLowerCase(Locale.ROOT)
                .replaceAll("[^a-z0-9\\s]", " ")
                .replaceAll("\\s+", " ")
                .trim();

        if (containsAny(message, "bye", "goodbye", "exit")) {
            return "Goodbye! Keep learning and have a great day!";
        }

        if (containsAny(message, "hello", "hi", "hey")) {
            return randomReply(
                "Hello! How can I help you?",
                "Hi there! What would you like to learn?",
                "Hey! Ask me about Java, SQL, DSA, or placements."
            );
        }

        if (message.contains("your name") || message.equals("name")) {
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

        if (containsAny(message, "thank", "thanks", "thank you")) {
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

        if (containsAny(message, "oop", "oops", "object oriented",
                "class and object", "polymorphism",
                "encapsulation", "abstraction")) {
            return "OOP stands for Object-Oriented Programming. "
                 + "It organizes programs using classes and objects. "
                 + "Its four main principles are Encapsulation, "
                 + "Inheritance, Polymorphism, and Abstraction.";
        }

        if (message.contains("inheritance")) {
            return "Inheritance allows one class to acquire properties "
                 + "and methods from another class using extends in Java.";
        }

        if (containsAny(message, "loop", "for loop", "while loop")) {
            return "Java has for, while, and do-while loops. "
                 + "Loops repeat a block of code while a condition is met.";
        }

        if (containsAny(message, "jvm", "java virtual machine")) {
    return "JVM stands for Java Virtual Machine. "
         + "It executes Java bytecode and helps Java programs "
         + "run on different platforms.";
}
            if (containsAny(message, "java", "jdk", "jre", "jvm",
        "explain java", "tell me about java", "java meaning")) {
    return "Java is an object-oriented programming language. "
         + "It is platform-independent and runs on the JVM. "
         + "Important topics include variables, loops, arrays, "
         + "classes, objects, inheritance, and collections.";
}

        if (containsAny(message, "sql", "select query", "join query")) {
            return "SQL is used to manage relational databases. Start with "
                 + "SELECT, WHERE, ORDER BY, GROUP BY, JOIN, INSERT, "
                 + "UPDATE, and DELETE.";
        }

        if (containsAny(message, "dbms", "database", "normalization")) {
            return "DBMS stands for Database Management System. It helps "
                 + "store, organize, retrieve, and manage data. Learn keys, "
                 + "normalization, relationships, and SQL.";
        }

        if (containsAny(message, "dsa", "data structure",
                "algorithm", "tree", "array", "linked list")) {
            return "DSA means Data Structures and Algorithms. Start with "
                 + "arrays, strings, linked lists, stacks, queues, trees, "
                 + "searching, and sorting.";
        }

        if (containsAny(message, "aptitude", "percentage",
                "average", "time and work")) {
            return "Practice percentages, ratios, averages, time and work, "
                 + "speed and distance, number series, and logical reasoning.";
        }

        if (containsAny(message, "placement", "interview", "job",
                "resume", "self introduction")) {
            return "For placements, practise Java, SQL, DSA, aptitude, "
                 + "communication, self-introduction, and project explanation.";
        }

        if (containsAny(message, "project", "my project")) {
            return "Explain your project problem, objective, technologies, "
                 + "features, your contribution, and what you learned.";
        }

        if (containsAny(message, "college", "study", "study plan")) {
            return "Make a daily study plan. Learn one concept, write code, "
                 + "test it, and revise what you learned.";
        }

        if (containsAny(message, "help", "commands", "what can you do")) {
            return "You can ask about Java, OOP, loops, SQL, DBMS, DSA, "
                 + "aptitude, placements, projects, date, or time.";
        }

        return randomReply(
            "I don't know that answer yet. Try asking about Java, SQL, or DSA.",
            "Could you ask that in a different way?",
            "I am a rule-based chatbot. Try a topic from the Help command."
        );
    }

    private boolean containsAny(String message, String... keywords) {
        for (String keyword : keywords) {
            if (message.contains(keyword)) {
                return true;
            }
        }
        return false;
    }

    private String randomReply(String... replies) {
        return replies[random.nextInt(replies.length)];
    }
}
