package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Category;
import models.User;
import services.AccountService;
import services.InventoryService;

public class ManageCategoriesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     HttpSession session = request.getSession();
        AccountService as = new AccountService();
        InventoryService is = new InventoryService();
        
        String email = (String) session.getAttribute("validUser");
        
        try {
            User user = as.getUser(email);
            session.setAttribute("firstName", user.getFirstName());
            session.setAttribute("lastName", user.getLastName());
        } catch (Exception e) {
            
        }

        try {
            List<Category> categories = is.getAllCategories();
            request.setAttribute("categories", categories);
        } catch (Exception e) {
            request.setAttribute("categoriesError", "Something went wrong while getting categories!");
        }

        // loads JSP
        getServletContext().getRequestDispatcher("/WEB-INF/manageCategories.jsp").forward(request, response);
        return;

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        AccountService as = new AccountService();
        InventoryService is = new InventoryService();
       boolean edit = false;
       request.setAttribute("edit", edit);

        String action = request.getParameter("action");
        

            Category editCategory = null;
            if (action.equals("create")) {
                String categoryName = request.getParameter("new_category_name");
                if (categoryName != null && categoryName != "") {
                    try {
                        is.createCategory(categoryName);
                        request.setAttribute("categoryCreateSuccessful", "Category was successfully created");
                    } catch (Exception e) {
                        request.setAttribute("categoryCreateError", "Category could not be created");
                    }
                } else {
                    request.setAttribute("categoryCreateError", "Category could not be created");
                }
            } else if (action.equals("manage")) {
                edit = true;
                request.setAttribute("edit", edit);
                int categoryId = Integer.parseInt(request.getParameter("edit_category_id"));
                try {
                    editCategory = is.getCategory(categoryId);
                    session.setAttribute("editCategory", editCategory);
                } catch (Exception ex) {
                    request.setAttribute("categoryFetchError", "Category could not be fetched");
                }
                request.setAttribute("newCategoryName", editCategory.getCategoryName());
            } else if (action.equals("edit")) {
                String newCategoryName = request.getParameter("edit_category_name");
                if (newCategoryName != null && newCategoryName != "") {
                    try {
                        
                        editCategory = (Category) session.getAttribute("editCategory");
                       
                        is.editCategory(editCategory, newCategoryName);
                        edit = false;
                        request.setAttribute("edit", edit);
                        request.setAttribute("categoryEditSuccessful", "Category was successfully edited");
                    } catch (Exception e) {
                        request.setAttribute("categoryEditError", "Category could not be edited");
                    }
                }
            }
        

        try {
            List<Category> categories = is.getAllCategories();
            request.setAttribute("categories", categories);
        } catch (Exception e) {
            request.setAttribute("categoriesError", "Something went wrong while getting categories!");
        }

        // loads JSP
        getServletContext().getRequestDispatcher("/WEB-INF/manageCategories.jsp").forward(request, response);
        return;
    }
}
