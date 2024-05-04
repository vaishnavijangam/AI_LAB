import java.util.Scanner;

public class ChatBot {
    public static void main(String[] args) {
        // Creating a Scanner object to read user input from the console
        Scanner scanner = new Scanner(System.in);
        
        // Greeting the user and asking for their name
        System.out.println("Hello! I'm your friendly chatbot. What's your name?");
        String name = scanner.nextLine();
        
        // Greeting the user by name and providing options for interaction
        System.out.println("Nice to meet you, " + name + "! How can I assist you today?");
        System.out.println("You can ask me anything, or type 'exit' to end the conversation.");

        String userInput;
        do {
            System.out.print("> "); // Prompting the user for input
            userInput = scanner.nextLine(); // Reading user input
            String response = getResponse(userInput); // Generating a response based on user input
            System.out.println(response); // Printing the response to the console
        } while (!userInput.equalsIgnoreCase("exit")); // Loop continues until the user types 'exit'
        
        // Saying goodbye to the user
        System.out.println("Goodbye, " + name + "! Have a great day!");
        scanner.close(); // Closing the Scanner object to release system resources
    }
    
    // Method to generate a response based on user input
    public static String getResponse(String userInput) {
        // Simple responses based on user input
        switch (userInput.toLowerCase()) {
            case "hello":
            case "hi":
                return "Hello there!";
            case "how are you":
                return "I'm just a bot, so I'm always doing well!";
            case "what's your name":
                return "I'm just a simple chatbot.";
            case "thank you":
            case "thanks":
                return "You're welcome!";
            case "exit":
                return "Exiting...";
            default:
                return "I'm sorry, I don't understand. Can you please rephrase?";
        }
    }
}
