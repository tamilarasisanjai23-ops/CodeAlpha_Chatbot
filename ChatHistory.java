

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ChatHistory {

    private static final String FILE_NAME = "chat_history.txt";

    public void saveMessage(String sender, String message) {
        try (BufferedWriter writer =
                 new BufferedWriter(new FileWriter(FILE_NAME, true))) {

            writer.write(
                LocalDateTime.now() + " | " + sender + ": " + message
            );
            writer.newLine();

        } catch (IOException e) {
            System.out.println(
                "Could not save chat history: " + e.getMessage()
            );
        }
    }

    public String searchHistory(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return "Please enter a keyword to search.";
        }

        try {
            Path path = Path.of(FILE_NAME);

            if (!Files.exists(path)) {
                return "No chat history found.";
            }

            List<String> matches = Files.readAllLines(path)
                .stream()
                .filter(line -> line.toLowerCase()
                    .contains(keyword.trim().toLowerCase()))
                .collect(Collectors.toList());

            if (matches.isEmpty()) {
                return "No matching messages found for: " + keyword;
            }

            return String.join("\n", matches);

        } catch (IOException e) {
            return "Unable to search history: " + e.getMessage();
        }
    }

    public String exportHistory(String destinationFile) {
        if (destinationFile == null
                || destinationFile.trim().isEmpty()) {
            return "Please provide an export file name.";
        }

        try {
            Path source = Path.of(FILE_NAME);
            Path destination = Path.of(destinationFile.trim());

            if (!Files.exists(source)) {
                return "No chat history available to export.";
            }

            if (source.toAbsolutePath().normalize()
                    .equals(destination.toAbsolutePath().normalize())) {
                return "Choose a different file name for export.";
            }

            Files.copy(
                source,
                destination,
                java.nio.file.StandardCopyOption.REPLACE_EXISTING
            );

            return "Chat history exported to: "
                    + destination.toAbsolutePath();

        } catch (IOException e) {
            return "Unable to export history: " + e.getMessage();
        }
    }
}
