package servlets;

import dataaccess.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //gets the session
        HttpSession session = request.getSession();
        AccountService as = new AccountService();
        String email = (String) session.getAttribute("validUser");

        String logOut = request.getParameter("log_out");
        String deactivate = request.getParameter("deactivate");
        // if session variable is not null (user was logged in) log out user by ending sesison
        if (logOut != null) {
            session.invalidate();
            request.setAttribute("loggedOut", "You have successfully logged out!");
        }

        if (deactivate != null) {
            try {
                as.deactivateUser(email);
                session.invalidate();
                request.setAttribute("userDeactivateSuccessful", "User was deactivated");
            } catch (Exception ex) {
                request.setAttribute("userDeactivateError", "User was not deactivated");
            }
        }

        // load the JSP
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        AccountService as = new AccountService();

        String action = request.getParameter("action");

        if (action != null && action.equals("login")) {
            boolean login = false;

            String email = request.getParameter("email");
            String password = request.getParameter("password");

            request.setAttribute("email", email);
            request.setAttribute("password", password);

            login = as.login(email, password);

            if (login == true) {
                UserDB userDB = new UserDB();
                session.setAttribute("validUser", email);
                String userEmail = (String) session.getAttribute("validUser");
                User user = userDB.getUser(userEmail);
                if (user.getActive()) {
                    if(user.getRole().getRoleId() == 1){
                        response.sendRedirect("manage_users");
                        return;
                    }else{
                    response.sendRedirect("inventory");
                    return;
                    }
                    
                } else {
                    request.setAttribute("accountDeactivate", "Your account is deactivated");
                    getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                    return;
                }

            } else {
                request.setAttribute("invalidLogin", "Invalid Username or Password!");
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                return;
            }
        } else if (action != null && action.equals("register")) {

            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String firstName = request.getParameter("first_name");
            String lastName = request.getParameter("last_name");

            try {
                as.register(email, password, firstName, lastName);
                request.setAttribute("userRegisterSuccessful", "User successfully created!");
            } catch (Exception e) {
                request.setAttribute("userRegisterError", "User could not be created.");
            }

            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
    }
}
