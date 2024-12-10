package group16;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import group16.classes.User.User;
import group16.classes.User.UserService;

public class App {
    private static UserService userService = new UserService();
    private static ProductService productService;

    public static void main(String[] args) throws SQLException {
        Connection connection = DatabaseConnection.getCon();
        ProductDAO productDAO = new ProductDAO(connection);
        productService = new ProductService(productDAO);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("___________________________________________________");
            System.out.println("Enter a number");
            System.out.println("");
            System.out.println("1. Log in");
            System.out.println("2. Create account");
            System.out.println("3. Product Management");
            System.out.println("4. Admin Management");
            System.out.println("5. Exit");
            System.out.println("");

            String userInput = scanner.nextLine();
            int userInputInt = 0;

            try {
                userInputInt = Integer.parseInt(userInput);
            } catch (Exception e) {
                System.err.println("Must input a number");
            }

            if (userInputInt == 1) {
                // WIP
            } else if (userInputInt == 2) {
                createUser(scanner);
            } else if (userInputInt == 3) {
                productManagement(scanner);
            } else if (userInputInt == 4) {
                adminManagement(scanner);
            } else if (userInputInt == 5) {
                System.exit(0);
            } else {
                System.out.println("Choose one of the available numbers");
            }
        }
    }

    private static void createUser(Scanner scanner) throws SQLException {
        User user = new User();

        System.out.println("___________________________________________________");
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

        userService.addUser(user);
        System.out.println("User created successfully.");
    }

    private static void productManagement(Scanner scanner) throws SQLException {
        while (true) {
            System.out.println("Product Management");
            System.out.println("1. Add Product");
            System.out.println("2. View Products by Seller");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. View All Products");
            System.out.println("6. Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addProduct(scanner);
                    break;
                case 2:
                    viewProductsBySeller(scanner);
                    break;
                case 3:
                    updateProduct(scanner);
                    break;
                case 4:
                    deleteProduct(scanner);
                    break;
                case 5:
                    viewAllProducts();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addProduct(Scanner scanner) throws SQLException {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter product quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Enter seller ID: ");
        int sellerId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        productService.addProduct(name, price, quantity, sellerId);
        System.out.println("Product added successfully.");
    }

    private static void viewProductsBySeller(Scanner scanner) throws SQLException {
        System.out.print("Enter seller ID: ");
        int sellerId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        List<Product> products = productService.getProductsBySeller(sellerId);
        for (Product product : products) {
            System.out.println(product);
        }
    }

    private static void updateProduct(Scanner scanner) throws SQLException {
        System.out.print("Enter product ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter new product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new product price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter new product quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        productService.updateProduct(id, name, price, quantity);
        System.out.println("Product updated successfully.");
    }

    private static void deleteProduct(Scanner scanner) throws SQLException {
        System.out.print("Enter product ID: ");
        int productId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        productService.deleteProduct(productId);
        System.out.println("Product deleted successfully.");
    }

    private static void viewAllProducts() throws SQLException {
        List<Product> products = productService.getAllProducts();
        for (Product product : products) {
            System.out.println(product);
        }
    }

    private static void adminManagement(Scanner scanner) throws SQLException {
        while (true) {
            System.out.println("Admin Management");
            System.out.println("1. View All Users");
            System.out.println("2. Delete User");
            System.out.println("3. View All Products with Seller Info");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewAllUsers();
                    break;
                case 2:
                    deleteUser(scanner);
                    break;
                case 3:
                    viewAllProductsWithSellerInfo();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void viewAllUsers() throws SQLException {
        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
    }

    private static void deleteUser(Scanner scanner) throws SQLException {
        System.out.print("Enter user ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        userService.deleteUser(userId);
        System.out.println("User deleted successfully.");
    }

    private static void viewAllProductsWithSellerInfo() throws SQLException {
        List<Product> products = productService.getAllProducts();
        for (Product product : products) {
            User seller = userService.getUserById(product.getSellerId());
            System.out.println("Product: " + product);
            System.out.println("Seller: " + seller);
        }
    }
}