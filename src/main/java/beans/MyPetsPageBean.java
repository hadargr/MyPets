/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejb.PetFacadeLocal;
import entities.Pet;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import services.Utils;

/**
 *
 * @author hadargr
 */
@ManagedBean(name = "myPetsPageBean")
@ViewScoped
public class MyPetsPageBean implements Serializable {

    @EJB
    PetFacadeLocal petsFacade;
    @ManagedProperty(value = "#{currentUserBean}")
    private CurrentUserBean currentUserBean;
    private List<Pet> currentUserPets = new ArrayList<>();

    public MyPetsPageBean() {
    }

    @PostConstruct
    public void init() {
        if (currentUserBean.getCurrentCustomer() == null) {
            Utils.navigateToPage("index");
        }
        fetchCurrentUserPets();
    }

    public List<Pet> getCurrentUserPets() {
        return currentUserPets;
    }

    public void setCurrentUserBean(CurrentUserBean bean) {
        this.currentUserBean = bean;
    }

    public void fetchCurrentUserPets() {
        String id = this.currentUserBean.getId();
        this.currentUserPets = petsFacade.getPetsByOwnerId(id);
    }

    public String addPet() {
        return "singlepet?faces-redirect=true&pet=new";
    }

    public String editPet(Pet p) {
        return "singlepet?faces-redirect=true&pet=" + p.getId();
    }

    public void removePet(Pet p) {
        this.petsFacade.remove(p);
        fetchCurrentUserPets();
    }

    public void clear() {
        currentUserPets = new ArrayList<>();
    }
}
