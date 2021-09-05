/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Pet;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author hadargr
 */
@Stateless
public class PetFacade extends AbstractFacade<Pet> implements PetFacadeLocal {

    @PersistenceContext(unitName = "com.mycompany_MyPets_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PetFacade() {
        super(Pet.class);
    }

    @Override
    public List<Pet> getPetsByOwnerId(String id) {
        TypedQuery<Pet> q = em.createQuery("SELECT p FROM Pet p JOIN p.ownerId c WHERE c.id = :ownerId", Pet.class);
        q.setParameter("ownerId", id);
        return q.getResultList();
    }

    @Override
    public List<Pet> getPetsByCategory(String categoryName) {
        TypedQuery<Pet> q = em.createQuery("SELECT p FROM Pet p JOIN p.category c WHERE c.name = :categoryName", Pet.class);
        q.setParameter("categoryName", categoryName);
        return q.getResultList();
    }

    @Override
    public List<Pet> getPetsByColor(String color) {
        TypedQuery<Pet> q = em.createQuery("SELECT p FROM Pet p WHERE LOWER(p.color) LIKE '%"+color.toLowerCase()+"%'", Pet.class);
        return q.getResultList();
    }

    @Override
    public List<Pet> getPetsByCategoryAndColor(String categoryName, String color) {
        TypedQuery<Pet> q = em.createQuery("SELECT p FROM Pet p JOIN p.category c WHERE c.name = :categoryName AND LOWER(p.color) LIKE '%"+color.toLowerCase()+"%'", Pet.class);
        q.setParameter("categoryName", categoryName);
        return q.getResultList();
    }

}
