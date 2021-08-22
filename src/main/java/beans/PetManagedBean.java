/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejb.PetFacadeLocal;
import entities.Pet;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author hadargr
 */
@ManagedBean(name = "petManagedBean")
@SessionScoped
public class PetManagedBean implements Serializable {
    @EJB PetFacadeLocal petsFacade;
    private List<Pet> allPets;
    private boolean status = false;
    private String data;

    /**
     * Creates a new instance of PetManagedBean
     */
    public PetManagedBean() {
    }
   
    public Pet getPetById(String id) {
        return petsFacade.find(id);
    }
    
    public List<Pet> getPetsByOwnerId(String ownerId) {
        return petsFacade.getPetsByOwnerId(ownerId);
    }
}
