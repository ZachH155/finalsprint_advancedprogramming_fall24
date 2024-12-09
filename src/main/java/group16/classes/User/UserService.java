package group16.classes.User;

import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

public class UserService {
    
    UserDAO DAO = new UserDAO();

    //constructor
    public UserService() {
        DAO = new UserDAO();
    }

    //method
    public boolean addUser(User user) throws SQLException {
        if (user.equals(null)) {
            return false;
        }

        String hashPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

        User newUser = new User(user.getUsername(), hashPassword, user.getEmail(), user.getRole());
        DAO.addUser(newUser);

        if (DAO.connectionStatus == true) {
            System.out.println("Account registered!");
        }
        
        return true;
    }

}
