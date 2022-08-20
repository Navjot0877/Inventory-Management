/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import static com.sun.org.apache.xerces.internal.util.FeatureState.is;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Item;
import models.User;
import services.AccountService;
import services.InventoryService;


public class AllItems extends HttpServlet {

   

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        AccountService as = new AccountService();
        String email = (String) session.getAttribute("validUser");
        
        try {
            User user = as.getUser(email);
            session.setAttribute("firstName", user.getFirstName());
            session.setAttribute("lastName", user.getLastName());
        } catch (Exception e) {
            
        }
    
    request.setAttribute("ss", false);
        getServletContext().getRequestDispatcher("/WEB-INF/items.jsp").forward(request, response);
        return;
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        InventoryService is = new InventoryService();
        String search = request.getParameter("search").toLowerCase();
        try{
            ArrayList<Item> item2 = new ArrayList<>();
        List<Item> items = is.getAllItems();
        for(Item itemss: items){
            
            if (itemss.getItemName().toLowerCase().startsWith(search))
                item2.add(itemss);
        }
        if(item2.size() > 0){
        request.setAttribute("ss", true);
        request.setAttribute("items", item2);
        }
        else{
            request.setAttribute("none", "No results matches your search");
        }
        }
        catch(Exception e){
                
            }
         getServletContext().getRequestDispatcher("/WEB-INF/items.jsp").forward(request, response);
        return;
    }

    
}
