
import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ChatbotGUI extends JFrame {

    private JTextArea chatArea;
    private JTextField inputField;
    private ChatbotEngine chatbot;
    private ChatHistory chatHistory;

    public ChatbotGUI() {
        chatbot = new ChatbotEngine();
        chatHistory = new ChatHistory();

        setTitle("CodeAlpha - Java Chatbot Assistant");
        setSize(850, 650);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(240, 244, 248));

        JLabel titleLabel = new JLabel(
            "Java Chatbot Assistant", SwingConstants.CENTER
        );
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(30, 60, 100));
        titleLabel.setBorder(
            BorderFactory.createEmptyBorder(15, 10, 10, 10)
        );
        add(titleLabel, BorderLayout.NORTH);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setFont(new Font("Arial", Font.PLAIN, 15));
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatArea.setMargin(new Insets(12, 12, 12, 12));
        chatArea.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setBorder(
            BorderFactory.createEmptyBorder(5, 12, 5, 12)
        );
        add(scrollPane, BorderLayout.CENTER);

        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 15));

        JButton sendButton = new JButton("Send");
        JButton javaButton = new JButton("Java");
        JButton placementButton = new JButton("Placement");
        JButton helpButton = new JButton("Help");
        JButton historyButton = new JButton("View History");
        JButton searchButton = new JButton("Search History");
        JButton exportButton = new JButton("Export History");
        JButton clearButton = new JButton("Clear Chat");
        JButton newChatButton = new JButton("New Chat");

        sendButton.addActionListener(e -> sendMessage());
        inputField.addActionListener(e -> sendMessage());

        javaButton.addActionListener(e ->
            sendSuggestedQuestion("Tell me about Java")
        );

        placementButton.addActionListener(e ->
            sendSuggestedQuestion("Give me placement tips")
        );

        helpButton.addActionListener(e ->
            sendSuggestedQuestion("help")
        );

        historyButton.addActionListener(e -> viewHistory());
        searchButton.addActionListener(e -> searchHistory());
        exportButton.addActionListener(e -> exportHistory());

newChatButton.addActionListener(e -> {
    int choice = JOptionPane.showConfirmDialog(
        this,
        "Start a new chat? Previous saved history will remain.",
        "New Chat",
        JOptionPane.YES_NO_OPTION
    );

    if (choice == JOptionPane.YES_OPTION) {
        chatArea.setText("");
        addBotMessage("Welcome to a new chat!");
    }
});

        clearButton.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(
                this,
                "Clear the messages shown in this window?",
                "Clear Chat",
                JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                chatArea.setText("");
                addBotMessage("Chat cleared. How can I help you?");
            }
        });

        JPanel inputPanel = new JPanel(new BorderLayout(8, 8));
        inputPanel.setBorder(
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        );
        inputPanel.setBackground(new Color(240, 244, 248));
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 5));
        buttonPanel.setBackground(new Color(240, 244, 248));
        buttonPanel.add(javaButton);
        buttonPanel.add(placementButton);
        buttonPanel.add(helpButton);
        buttonPanel.add(historyButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(exportButton);
        buttonPanel.add(newChatButton);
        buttonPanel.add(clearButton);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(new Color(240, 244, 248));
        bottomPanel.add(inputPanel, BorderLayout.CENTER);
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(bottomPanel, BorderLayout.SOUTH);

        addBotMessage("Welcome to CodeAlpha Java Chatbot!");
        addBotMessage(
            "Ask about Java, OOP, SQL, DBMS, DSA, or placements."
        );

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(
                    ChatbotGUI.this,
                    "Are you sure you want to exit?",
                    "Exit Chatbot",
                    JOptionPane.YES_NO_OPTION
                );

                if (choice == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });
    }

    private void sendSuggestedQuestion(String question) {
        inputField.setText(question);
        sendMessage();
    }

    private void sendMessage() {
        String message = inputField.getText().trim();

        if (message.isEmpty()) {
            return;
        }

        String time = LocalTime.now().withNano(0).toString();

        chatArea.append("You [" + time + "]: " + message + "\n");
        chatHistory.saveMessage("You", message);

        String response = chatbot.getResponse(message);

        addBotMessage(response);
        chatHistory.saveMessage("Bot", response);

        inputField.setText("");
        chatArea.setCaretPosition(chatArea.getDocument().getLength());
    }

    private void addBotMessage(String message) {
        String time = LocalTime.now().withNano(0).toString();
        chatArea.append("Bot [" + time + "]: " + message + "\n\n");
        chatArea.setCaretPosition(chatArea.getDocument().getLength());
    }

    private void viewHistory() {
        try {
            Path file = Path.of("chat_history.txt");

            if (!Files.exists(file) || Files.size(file) == 0) {
                JOptionPane.showMessageDialog(
                    this, "No saved chat history found.",
                    "Chat History", JOptionPane.INFORMATION_MESSAGE
                );
                return;
            }

            JTextArea area = new JTextArea(20, 50);
            area.setEditable(false);
            area.setFont(new Font("Monospaced", Font.PLAIN, 12));
            area.setText(Files.readString(file));
            area.setCaretPosition(0);

            JOptionPane.showMessageDialog(
                this, new JScrollPane(area),
                "Saved Chat History", JOptionPane.INFORMATION_MESSAGE
            );

        } catch (IOException e) {
            showError("Unable to read history: " + e.getMessage());
        }
    }

    private void searchHistory() {
        String keyword = JOptionPane.showInputDialog(
            this, "Enter a word to search in chat history:"
        );

        if (keyword == null) {
            return;
        }

        String result = chatHistory.searchHistory(keyword);

        JTextArea area = new JTextArea(result, 15, 45);
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        JOptionPane.showMessageDialog(
            this, new JScrollPane(area),
            "Search Results", JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void exportHistory() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Choose where to export chat history");
        chooser.setSelectedFile(new java.io.File("chat_history_export.txt"));

        if (chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        String result = chatHistory.exportHistory(
            chooser.getSelectedFile().getAbsolutePath()
        );

        JOptionPane.showMessageDialog(
            this, result, "Export History",
            result.startsWith("Unable") || result.startsWith("No chat")
                ? JOptionPane.ERROR_MESSAGE
                : JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(
            this, message, "Error", JOptionPane.ERROR_MESSAGE
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChatbotGUI app = new ChatbotGUI();
            app.setVisible(true);
        });
    }
}
