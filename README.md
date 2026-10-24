
# CodeAlpha Java Chatbot Assistant

## Project Overview
The Java Chatbot Assistant is a console and graphical user interface (GUI) based chatbot developed using Java. It provides predefined responses to common user messages through rule-based logic.

## Features
- User-friendly Java Swing GUI
- Rule-based chatbot responses
- Greeting and general conversation support
- Java programming and placement-related responses
- Quick suggestion buttons
- Chat timestamps
- Save conversation history to a text file
- View saved chat history
- Clear visible chat messages
- Enter key support for sending messages
- Exit confirmation dialog

## Technologies Used
- Java
- Java Swing
- Object-Oriented Programming
- File Handling
- Java Date and Time API

## Project Files
- `Main.java` – Console-based chatbot entry point
- `ChatbotGUI.java` – Graphical user interface
- `ChatbotEngine.java` – Chatbot response logic
- `ChatHistory.java` – Saves conversation history
- `chat_history.txt` – Locally generated conversation history

## Requirements
- Java JDK 17 or later
- Visual Studio Code or any Java-compatible IDE

## How to Run

Open the project folder in the terminal and compile the files:

```bash
javac Main.java ChatbotEngine.java ChatHistory.java ChatbotGUI.java
```

Run the graphical chatbot:

```bash
java ChatbotGUI
```

To run the console version, if required:

```bash
java Main
```

## How to Use
1. Launch the application.
2. Enter a message in the input field.
3. Click Send or press Enter.
4. Use the suggestion buttons for quick questions.
5. Select View History to review saved conversations.
6. Select Clear Chat to clear messages displayed in the current window.

## Project Type
Educational Java project developed for internship learning and practice.

## Future Enhancements
- Expand the chatbot knowledge base
- Add improved message searching and history management
- Integrate an NLP or AI model
- Add more conversational capabilities

## Author
CodeAlpha Internship Project
