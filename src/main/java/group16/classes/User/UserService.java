package group16.classes.User;

import java.sql.SQLException;

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

        User newUser = new User(user.getUsername(), user.getPassword(), user.getEmail(), user.getRole());
        DAO.addUser(newUser);
        System.out.println("Account registered!");
        return true;
    }

}
