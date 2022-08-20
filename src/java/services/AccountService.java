package services;

import dataaccess.RoleDB;
import dataaccess.UserDB;
import java.util.List;
import models.Role;
import models.User;

public class AccountService {

    public boolean login(String email, String password) {
        UserDB userDB = new UserDB();
        User user = userDB.getUser(email);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public User getUser(String email) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.getUser(email);
        return user;
    }

    public List<User> getAllUsers() throws Exception {
        UserDB userDB = new UserDB();
        List<User> users = userDB.getAll();
        return users;
    }

    public void register(String email, String password, String firstName, String lastName) throws Exception {
        UserDB userDB = new UserDB();
        RoleDB roleDB = new RoleDB();
        boolean active = true;
        Role role = roleDB.getRole(2);
        User user = new User(email, active, firstName, lastName, password);
        user.setRole(role);
        userDB.addUser(user);
    }

    public void addUser(String email, boolean active, String firstName, String lastName, String password, int intRole) throws Exception {
        UserDB userDB = new UserDB();
        String roleName = "";
        User user = new User(email, active, firstName, lastName, password);
        if (intRole == 1) {
            roleName = "system admin";
        } else if (intRole == 2) {
            roleName = "regular user";
        } else {
            roleName = "company admin";
        }
        System.out.println(roleName);
        Role role = new Role(intRole, roleName);
        user.setRole(role);
        userDB.addUser(user);
    }

    public void editUser(String email, String newPassword, String newFirstName, String newLastName) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.getUser(email);
        user.setPassword(newPassword);
        user.setFirstName(newFirstName);
        user.setLastName(newLastName);
        userDB.editUser(user);
    }

    public void editUsers(String email, boolean active, String newPassword, String newFirstName, String newLastName, int intRole) throws Exception {
        UserDB userDB = new UserDB();
        String roleName = "";
        User user = userDB.getUser(email);
        user.setPassword(newPassword);
        user.setFirstName(newFirstName);
        user.setLastName(newLastName);
        user.setActive(active);
        switch (intRole){
            case 1: roleName = "system admin";
            break;
            case 2: roleName = "regular user";
            break;
            case 3: roleName = "company admin";
        }
        Role role = new Role(intRole, roleName);
        user.setRole(role);
        userDB.editUser(user);
    }

    public void deleteUser(String email) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.getUser(email);
        userDB.deleteUser(user);
    }

    public void deactivateUser(String email) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.getUser(email);
        user.setActive(false);
        userDB.editUser(user);
    }

}
