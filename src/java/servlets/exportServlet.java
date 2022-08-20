/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Item;
import models.User;
import services.AccountService;
import services.InventoryService;


/**
 *
 * @author Dell
 */
public class exportServlet extends HttpServlet {

  

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountService as = new AccountService();
        InventoryService is = new InventoryService();
        
        double price = 0;
        try{
        List<User> user = as.getAllUsers();
        for (User users: user){
            List<Item> items = is.getItemsOfOwner(users.getEmail());
            int size = items.size();
            for (Item itemss: items){
                 price = itemss.getPrice() + price;
            }
            price = 0;
        }
        }
        catch(Exception e){
            
        }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    
    
}
