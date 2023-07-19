package com.mycompany.doggrooming.persistence;

import com.mycompany.doggrooming.logic.Pets;
import com.mycompany.doggrooming.persistence.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


public class PetsJpaController implements Serializable {

    public PetsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;
    
    public PetsJpaController() {
        emf = Persistence.createEntityManagerFactory("GrooDogPU");
    }

    

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pets pets) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(pets);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pets pets) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            pets = em.merge(pets);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = pets.getNum_customer();
                if (findPets(id) == null) {
                    throw new NonexistentEntityException("The pets with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pets pets;
            try {
                pets = em.getReference(Pets.class, id);
                pets.getNum_customer();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pets with id " + id + " no longer exists.", enfe);
            }
            em.remove(pets);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pets> findPetsEntities() {
        return findPetsEntities(true, -1, -1);
    }

    public List<Pets> findPetsEntities(int maxResults, int firstResult) {
        return findPetsEntities(false, maxResults, firstResult);
    }

    private List<Pets> findPetsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pets.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
            
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            em.close();
        }
        return new ArrayList<>();
    }

    public Pets findPets(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pets.class, id);
        } finally {
            em.close();
        }
    }

    public int getPetsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pets> rt = cq.from(Pets.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    List<Pets> findPetEntities() {
        EntityManager em = getEntityManager();
        CriteriaQuery<Pets> cq = em.getCriteriaBuilder().createQuery(Pets.class);
        Root<Pets> rt = cq.from(Pets.class);
        cq.select(rt);
        
        List<Pets> result = em.createQuery(cq).getResultList();
        return result;
    }
    
}
