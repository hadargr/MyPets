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
    
    public List<Pet> getPetsByOwnerId(String id) {
        TypedQuery<Pet> q = em.createQuery("SELECT p FROM Pet p JOIN Customer c ON p.ownerId=c WHERE c.id = :id", Pet.class);
        q.setParameter("id", id);
        return q.getResultList();
    }
    
}
