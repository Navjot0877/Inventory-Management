
package services;

import dataaccess.CategoryDB;
import dataaccess.ItemDB;
import dataaccess.UserDB;
import java.util.List;
import models.Category;
import models.Item;
import models.User;

public class InventoryService {
    
    public Category getCategory(int categoryId) throws Exception {
        CategoryDB categoryDB = new CategoryDB();
        Category category = categoryDB.getCategory(categoryId);
        return category;
    }
    
    public List<Category> getAllCategories() throws Exception {
        CategoryDB categoriesDB = new CategoryDB();
        List<Category> categories = categoriesDB.getAll();
        return categories;
    }
    
    public void createCategory(String categoryName) throws Exception {
        CategoryDB categoriesDB = new CategoryDB();
        List<Category> categories = categoriesDB.getAll();
        int categoryId = categories.size() + 1;
        Category category = new Category(categoryId, categoryName);
        categoriesDB.createCategory(category);
    }
    
    public void editCategory(Category editCategory, String newCategoryName) throws Exception {
        CategoryDB categoryDB = new CategoryDB();
        editCategory.setCategoryName(newCategoryName);
        categoryDB.editCategory(editCategory);
    }
    
    public List<Item> getItemsOfOwner(String email) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.getUser(email);
        List<Item> items = user.getItemList();
        return items;
    }
    
    // method that returns a list contaiing all items
    public List<Item> getAllItems() throws Exception {
        ItemDB itemsDB = new ItemDB();
        List<Item> allItems = itemsDB.getAllItems();
        return allItems;
    }
    
    public Item getItem(int itemID) throws Exception{
        ItemDB itemsDB = new ItemDB();
        Item item = itemsDB.getItem(itemID);
        return item;      
    }
    // method to insert item
    public void insertItem(int itemID, String itemName, double price, String owner, int categoryID) throws Exception {
        Item item = new Item(itemID, itemName, price);
        
        UserDB userDB = new UserDB();
        User user = userDB.getUser(owner);
        item.setOwner(user);
        CategoryDB categoryDB = new CategoryDB();
        Category category = categoryDB.getCategory(categoryID);
        item.setCategory(category);
        
        ItemDB itemDB = new ItemDB();
        itemDB.addItem(item);     
    }
    
    public void editItem(int itemId, int categoryId, String itemName, double itemPrice) throws Exception{
        ItemDB itemDB = new ItemDB();
        Item item = itemDB.getItem(itemId);
        CategoryDB categoryDB = new CategoryDB();
        Category category = categoryDB.getCategory(categoryId);
        item.setCategory(category);
        item.setItemName(itemName);
        item.setPrice(itemPrice);
        itemDB.editItem(item);
    }
    
    // method to delete item
    public void deleteItem(int itemID) throws Exception{
        ItemDB itemsDB = new ItemDB();
        Item item = itemsDB.getItem(itemID);
        itemsDB.deleteItem(item);      
    }
    
}
