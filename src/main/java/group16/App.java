package group16;

import java.sql.SQLException;
import java.util.Scanner;

import group16.classes.User.User;
import group16.classes.User.UserService;


public class App 
{   
    public static User loggedUser = new User();
    private static UserService userService = new UserService();

    public static void main( String[] args ) throws SQLException
    {
        //Main menu
        System.out.println("___________________________________________________");
        System.out.println("Enter a number");
        System.out.println("");
        System.out.println("");
        System.out.println("1. Log in");
        System.out.println("2. Create account");
        System.out.println("3. Exit");
        System.out.println("");

        //detects user input
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        int userInputInt = 0;
        

        //checks for valid user input
        try {
            userInputInt = Integer.parseInt(userInput);
        } catch (Exception e) {
            System.err.println("Must input a number");
        }


        //runs code based on user input
        if (userInputInt == 1) {

            //user login
            System.out.println("___________________________________________________");
            System.out.println("User Login");
            System.out.println();

            System.out.println("Enter username");
            String username = scanner.nextLine();
            
            System.out.println("Enter password");
            String password = scanner.nextLine();


            if (userService.DAO.loginSuccess == true) {
                
                loggedUser = userService.getUser(username, password);

                System.out.println("Login successful");
            }
        


        } else if (userInputInt == 2) {

            //user creation
            User user = new User();

            System.out.println("___________________________________________________");
            System.out.println("User Regestration");
            System.out.println();

            System.out.println("Set role");
            System.out.println("Buyer, Seller, or Admin");
            user.setRole(scanner.nextLine());

            System.out.println("Enter username");
            user.setUsername(scanner.nextLine());

            System.out.println("Enter password");
            user.setPassword(scanner.nextLine());

            System.out.println("Enter email");
            user.setEmail(scanner.nextLine());

            //Database connection
            userService.addUser(user);




        } else if (userInputInt == 3) {
            System.exit(0);
        } else {
            System.out.println("Choose one of the avalible numbers");
        }
            
        scanner.close();
        
    }
            
}
