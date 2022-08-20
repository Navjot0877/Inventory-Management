
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Category;
import models.Item;
import models.User;

public class ItemDB {
    
    // method to return an item basedd on the itemID
    public Item getItem(int itemID) throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            Item item = em.find(Item.class, itemID);
            return item;
        } finally { 
            em.close();
        }
    }
    
    // method to return the list of all items
    public List<Item> getAllItems() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try{
            List<Item> items = em.createNamedQuery("Item.findAll", Item.class).getResultList();
            return items;
        } finally {
            em.close();
        }
    }
    
    // method to add an item to the database
    public void addItem(Item item) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try{
            User user = item.getOwner();
            Category category = item.getCategory();
            user.getItemList().add(item);
            category.getItemList().add(item);
            trans.begin();
            em.persist(item);
            em.merge(user);   
            em.merge(category);
            trans.commit();
        } catch (Exception e){
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public void editItem(Item item) throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try{
            User user = item.getOwner();
            Category category = item.getCategory();
            trans.begin();
            em.merge(category);
             em.merge(item);
             em.merge(user);
           
            trans.commit();
        } catch (Exception e){
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    // method to delete an item from the database
    public void deleteItem(Item item) throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try{
            User user = item.getOwner();
            Category category = item.getCategory();
            user.getItemList().remove(item);
            category.getItemList().remove(item);
            trans.begin();
            em.remove(em.merge(item));
            em.merge(user);
            em.merge(category);
            trans.commit();
        } catch (Exception e){
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
}
