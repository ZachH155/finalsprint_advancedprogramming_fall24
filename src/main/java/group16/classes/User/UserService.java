package group16.classes.User;

import java.sql.SQLException;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;


public class UserService {
    
    public UserDAO DAO = new UserDAO();

    //constructor
    public UserService() {
        DAO = new UserDAO();
    }

    //methods
    public void addUser(User user) throws SQLException {
        if (user.equals(null)) {
            System.out.println("User obj null");
        }

        String hashPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

        User newUser = new User(user.getUsername(), hashPassword, user.getEmail(), user.getRole());
        DAO.addUser(newUser);

        if (DAO.connectionStatus == true) {
            System.out.println("Account registered!");
        }
    }

    public User getUser(String username, String password) throws SQLException {
        if (username.equals(null) || password.equals(null)) {
            System.out.println("Enter a username and password");
        }

        return DAO.getUser(username, password);

    }

    public List<User> getAllUsers() throws SQLException {
        return DAO.getAllUsers();
    }
}
