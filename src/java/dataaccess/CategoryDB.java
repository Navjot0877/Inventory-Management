
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Category;

public class CategoryDB {
    
    public Category getCategory(int categoryID){
        EntityManager em = DBUtil.getEmFactory().createEntityManager(); 
        try{
            Category category = em.find(Category.class, categoryID);
            return category;
        } finally{
            em.close();
        }
    }
    
    // method to contain a list of all categories
    public List<Category> getAll() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try{
            List<Category> categories = em.createNamedQuery("Category.findAll", Category.class).getResultList();
            return categories;
        } finally {
            em.close();
        }
    }
    
    public void createCategory(Category category) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try{
            trans.begin();
            em.persist(category);
            trans.commit();
        } catch (Exception e){
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
    public void editCategory(Category category) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try{
            trans.begin();
            em.merge(category);
            trans.commit();
        } catch (Exception e){
            trans.rollback();
        } finally {
            em.close();
        }
    }
}
