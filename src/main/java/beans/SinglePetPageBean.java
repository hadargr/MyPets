/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Pet;
import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import services.FileUploader;

/**
 *
 * @author hadargr
 */
@ManagedBean(name = "singlePetPageBean")
public class SinglePetPageBean implements Serializable {

    @ManagedProperty(value = "#{petManagedBean}")
    private PetManagedBean petManagedBean;
    @ManagedProperty(value = "#{currentUserBean}")
    private CurrentUserBean currentUserBean;
    private Pet selectedPet = null;
    private boolean isNew = false;
    private String photo = "";
    private Part file = null;
    private Map<String, String> params;

    public SinglePetPageBean() {
        params = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap();
    }

    @PostConstruct
    public void init() {
        String petId = params.get("pet");
        if (petId == "new") {
            isNew = true;
        } else {
            selectedPet = petManagedBean.getPetById(petId);

        }
    }

    public Pet getSelectedPet() {
        return selectedPet;
    }

    public String getSelectedPetId() {
        return params.get("pet");
    }

    public void setSelectedPet(Pet selectedPet) {
        this.selectedPet = selectedPet;
    }

    public void setPetManagedBean(PetManagedBean bean) {
        this.petManagedBean = bean;
    }

    public void setCurrentUserBean(CurrentUserBean bean) {
        this.currentUserBean = bean;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Part getFile() {
        return file;
    }

    public String imageTooltip(Pet p) {
        if (p.getPhotoFileName() == null) {
            return "No image for " + p.getName();
        }
        return "The cute " + p.getName();
    }

    public void setFile(Part file) {
        this.file = file;
        System.out.println("In setFile");
        System.out.println(file.getName());
    }

    public void processUpload() {
        System.out.println("In processUpload");
        FileUploader fileUploader = new FileUploader();
        photo = fileUploader.upload(this.file);
        System.out.println(photo);
        System.out.println(fileUploader.getError());
    }

    public String editPet(Pet p) {
        setSelectedPet(p);
        return "mypets?pet=" + p.getId();
    }

    public void removePet(Pet p) {
        setSelectedPet(p);
    }

    public void clear() {
        selectedPet = null;
        photo = "";
        file = null;
    }
}
