/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Pet;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author hadargr
 */
@ManagedBean(name = "myPetsPageBean")
public class MyPetsPageBean implements Serializable {

    @ManagedProperty(value = "#{petManagedBean}")
    private PetManagedBean petManagedBean;
    @ManagedProperty(value = "#{currentUserBean}")
    private CurrentUserBean currentUserBean;
    private List<Pet> currentUserPets = new ArrayList<>();

    public MyPetsPageBean() {
    }

    @PostConstruct
    public void init() {
        if (currentUserBean != null) {
            fetchCurrentUserPets();
        }
    }

    public List<Pet> getCurrentUserPets() {
        return currentUserPets;
    }

    public void setPetManagedBean(PetManagedBean bean) {
        this.petManagedBean = bean;
    }

    public void setCurrentUserBean(CurrentUserBean bean) {
        this.currentUserBean = bean;
    }

    
    public String imageTooltip(Pet p) {
        if (p.getPhotoFileName() == null) {
            return "No image for " + p.getName();
        }
        return "The cute " + p.getName();
    }
    
    public void fetchCurrentUserPets() {
        String id = this.currentUserBean.getId();
        this.currentUserPets = this.petManagedBean.getPetsByOwnerId(id);
    }
    
    public String editPet(Pet p) {
        return "singlepet?faces-redirect=true&pet=" + p.getId();
    }

    public void removePet(Pet p) {
    }
    
    public void clear() {
        currentUserPets = new ArrayList<>();
    }
}
