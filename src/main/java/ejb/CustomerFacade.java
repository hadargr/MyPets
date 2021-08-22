/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Customer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author hadargr
 */
@Stateless(mappedName="customerFacade")
public class CustomerFacade extends AbstractFacade<Customer> implements CustomerFacadeLocal {

    @PersistenceContext(unitName = "com.mycompany_MyPets_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomerFacade() {
        super(Customer.class);
    }
    
    public Customer customerLogin(String email, String password) {
        TypedQuery<Customer> q = em.createQuery("SELECT c FROM Customer c WHERE c.email=\"" + email +"\" AND c.password=\"" + password +"\"", Customer.class);
        return q.getSingleResult();
    }

    public Customer getByEmail(String email) {
        TypedQuery<Customer> q = em.createNamedQuery("Customer.findByEmail", Customer.class);
        return q.setParameter("email", email).getSingleResult();
    }
}
