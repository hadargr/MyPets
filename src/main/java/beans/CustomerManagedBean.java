/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejb.CustomerFacadeLocal;
import entities.Customer;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author hadargr
 */
@ManagedBean(name = "customerManagedBean")
@SessionScoped
public class CustomerManagedBean implements Serializable {
    @EJB CustomerFacadeLocal customersFacade;
    private List<Customer> allCustomers;

    /**
     * Creates a new instance of PetManagedBean
     */
    public CustomerManagedBean() {
    }
    
    @PostConstruct
    public void init(){
    }
    
    public List<Customer> getAllCustomers()  {
        return allCustomers;
    }
}
