/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package accesData;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author daniel
 */
public class AccesData {
    private EntityManager em;
    private EntityTransaction transac;
    private EntityManagerFactory emf;
   // private Class[] TClasse=new Class[]{Congregation,Proclamateur};
    
    public AccesData(String nomUniteDePersistance){
      System.out.println( "Création d'un EntityManager");
      emf =Persistence.createEntityManagerFactory(nomUniteDePersistance);
      this.em = emf.createEntityManager();
      this.transac = em.getTransaction();
    }
    public void fermer(){
        em.close();
        emf.close();
        
    }
    public List getListe(String requete){
        
        Query q=em.createNamedQuery(requete);
        return q.getResultList();
    }
    
    public List getListe(String requete,String nomParam,Object valeurParam){
        Query q=em.createNamedQuery(requete);
        q.setParameter(nomParam,valeurParam);
        return q.getResultList();
    }
    
    public Object getOccurenceObject(String requete,String nomParam,Object valeurParam){
        
        Query q=em.createNamedQuery(requete);
        q.setParameter(nomParam,valeurParam);
        return q.getSingleResult();
    }
    
   
     public void requeteNative(String requete){
         em.getTransaction().begin();
         Query q=em.createNativeQuery(requete);
       
        q.executeUpdate();
        em.getTransaction().commit();
        
    }
     public Object requeteNativeQuery(String requete){
         
         Query q=em.createNativeQuery(requete);
         return q.getSingleResult();
        
    }
     
     public Vector requeteNative_Vector(String requete){
         Vector v=new Vector();
         Query q=em.createNativeQuery(requete);
         List l=q.getResultList();
         Iterator i=l.iterator();
         while(i.hasNext())
             v.addElement(i.next());
         return v;
         
        
    }
    
      public List requeteNative_List(String requete){
         Vector v=new Vector();
         Query q=em.createNativeQuery(requete);
         return q.getResultList();

    }
    
     public Object getObject(String requete){
        Query q=em.createNamedQuery(requete);
        return q.getFirstResult();
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public EntityTransaction getTransac() {
        return transac;
    }

    public void setTransac(EntityTransaction transac) {
        this.transac = transac;
    }
    
    
    
    
    public List getListOccurences(String table){
        Query q=em.createNamedQuery(table+".findAll");
        return q.getResultList();
    }
    
    
    //rechercher un objet dans la BD a partir de sa cle
    public Object getOccurence(Class o, int i){
      //  return em.find(o.getClass().,i);
        return null;
    }
    
    public void ajout(Object o){
        transac.begin();
        em.persist(o);
        
        transac.commit();
    }
    public void miseAJour(){
       // c=getOccurence(c, i);
        transac.begin();
        em.flush();
        transac.commit();
        
    }
    public void supprime(Object o){
      // o=getOccurence(o, i);
       transac.begin();
       em.remove(o);
       transac.commit();
        
    }
    
}
