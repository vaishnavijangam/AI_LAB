import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Class to represent a customer
class Customer {
    private String name;
    private String phoneNumber;
    private String emailAddress;
    private String socialMediaHandle;

    // Constructor
    public Customer(String name, String phoneNumber, String emailAddress, String socialMediaHandle) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.socialMediaHandle = socialMediaHandle;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getSocialMediaHandle() {
        return socialMediaHandle;
    }
}

// Class to represent a CRM system
class CRMSystem {
    private List<Customer> customers;

    // Constructor
    public CRMSystem() {
        customers = new ArrayList<>();
    }

    // Method to add a new customer
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    // Method to display customer details
    public void displayCustomerDetails() {
        System.out.println("Customer Details:");
        for (Customer customer : customers) {
            System.out.println("Name: " + customer.getName());
            System.out.println("Phone Number: " + customer.getPhoneNumber());
            System.out.println("Email Address: " + customer.getEmailAddress());
            System.out.println("Social Media Handle: " + customer.getSocialMediaHandle());
            System.out.println("------------------------------------");
        }
    }
}

// Main class
public class CustomerRelationshipManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CRMSystem crmSystem = new CRMSystem();

        // Collect customer details from the user
        System.out.println("Enter customer details:");
        while (true) {
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Phone Number: ");
            String phoneNumber = scanner.nextLine();
            System.out.print("Email Address: ");
            String emailAddress = scanner.nextLine();
            System.out.print("Social Media Handle: ");
            String socialMediaHandle = scanner.nextLine();

            // Create a new customer object
            Customer customer = new Customer(name, phoneNumber, emailAddress, socialMediaHandle);

            // Add the customer to the CRM system
            crmSystem.addCustomer(customer);

            // Ask if the user wants to add another customer
            System.out.print("Do you want to add another customer? (yes/no): ");
            String choice = scanner.nextLine();
            if (!choice.equalsIgnoreCase("yes")) {
                break;
            }
        }

        // Display customer details
        crmSystem.displayCustomerDetails();

        // Close the scanner
        scanner.close();
    }
}
