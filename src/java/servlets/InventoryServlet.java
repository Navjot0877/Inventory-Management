
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Category;
import models.Item;
import models.User;
import services.AccountService;
import services.InventoryService;

@WebServlet(name = "InventoryServlet", urlPatterns = {"/inventory"})
public class InventoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        AccountService as = new AccountService();
        InventoryService is = new InventoryService();
        
        // get the username of the user logged in
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
            
        }
        
        try{
            List<Item> items = is.getItemsOfOwner(email);
            request.setAttribute("items", items);
            if(items.isEmpty()){
                request.setAttribute("noItems", "No items to display. Inventory is Empty");
                request.setAttribute("size", items.size());
            }
        } catch(Exception e){
            
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
        return; 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        boolean edit = false;
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("validUser");
        AccountService as = new AccountService();
        InventoryService is = new InventoryService();
        String action = request.getParameter("action");
        
        // if the value of the hidden attribute action is "Delete"
        if(action.equals("Delete")) {
            try {
                int itemID = Integer.parseInt(request.getParameter("itemID"));
                // delete item
                is.deleteItem(itemID);
                request.setAttribute("deleteSuccessful", "Item was successfully deleted.");
            } catch (Exception e) {
                request.setAttribute("deleteError", "An error occured while trying to delete item.");
            }
        }
        else if(action.equals("Edit")){
            edit = true;
            request.setAttribute("edit", edit);
            int itemID = Integer.parseInt(request.getParameter("itemID"));
            session.setAttribute("itemId", itemID);
            try {
                Item item = is.getItem(itemID);
                request.setAttribute("category", item.getCategory().getCategoryName());
                request.setAttribute("editItemName", item.getItemName());
                request.setAttribute("editPrice", item.getPrice());
            } catch (Exception e) {
                request.setAttribute("noItemFetched", "No item was fetched");
            }
        }      
        
        else if(action.equals("Add")) {
            try {
               
                List<Item> items = is.getAllItems();
                
                int itemID = items.size() + 1;
                int categoryID = Integer.parseInt(request.getParameter("category"));
                String itemName = request.getParameter("itemname");
                double price = Double.parseDouble(request.getParameter("price"));
                if(price < 0 || itemName == null || itemName.equals("")){
                    throw new Exception();
                }
                is.insertItem(itemID, itemName, price, email, categoryID);
                request.setAttribute("insertSuccessful", "Item was successfully added.");
            } catch (Exception e) {
                request.setAttribute("createError", "An error occured while trying to create item.");
            }
        }
        else if(action.equals("Edit Item")){
            int itemId = (int) session.getAttribute("itemId");
            int newCategoryId = Integer.parseInt(request.getParameter("newCategory"));
            String newItemName = request.getParameter("newItemName");
            double newPrice = Double.parseDouble(request.getParameter("newPrice"));
           
            try{
                if(newPrice < 0 || newItemName == null || newItemName.equals("")){
                    throw new Exception();
                }
                is.editItem(itemId, newCategoryId, newItemName, newPrice);
                edit = false;
                request.setAttribute("edit", edit);
                request.setAttribute("itemEditSuccessful", "Item was successfully edited");
            } catch(Exception e){
                request.setAttribute("itemEditError", "Item could not be edited");
            }
        }
        
        
        // get a list of all categories
        try {
            List<Category> categories = is.getAllCategories();
            String category = (String) session.getAttribute("category");
            request.setAttribute("categories", categories);
        } catch (Exception e) {
            request.setAttribute("categoriesError", "Something went wrong!");
        }
        
        // get a list of all items that belong to the user currently logged in
        try {
            List<Item> items = is.getItemsOfOwner(email);
            request.setAttribute("items", items);
        } catch (Exception e) {
            request.setAttribute("itemsError", "Something went wrong!");
        }
        
        
        getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
        return;
        
    }

}
