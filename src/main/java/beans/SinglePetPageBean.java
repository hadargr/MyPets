/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejb.CategoryFacadeLocal;
import entities.Category;
import entities.Customer;
import entities.Pet;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import org.primefaces.event.FileUploadEvent;
import services.FileUploader;
import services.Utils;
import static services.Utils.getRandomUUID;

/**
 *
 * @author hadargr
 */
@ManagedBean(name = "singlePetPageBean")
@ViewScoped
public class SinglePetPageBean implements Serializable {

    @EJB
    CategoryFacadeLocal categoriesFacade;
    @ManagedProperty(value = "#{petManagedBean}")
    private PetManagedBean petManagedBean;
    @ManagedProperty(value = "#{currentUserBean}")
    private CurrentUserBean currentUserBean;
    private boolean isNew = false;
    private Part file = null;
    private final Map<String, String> params;
    private List<Category> categories;
    private String id;
    private String name;
    private String gender;
    private BigInteger age;
    private String color;
    private String about;
    private String photoFileName;
    private String categoryName;
    private Customer ownerId;
    private String statusText;
    private String outputTextClass = "success";
    private boolean showOutput = false;
    private boolean enableEditing = false;

    public SinglePetPageBean() {
        params = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap();
    }

    @PostConstruct
    public void init() {
        Pet selectedPet;
        try {
            String petId = params.get("pet");
            if ("new".equals(petId)) {
                if (currentUserBean.getCurrentCustomer() == null) {
                    Utils.navigateToPage("index");
                }
                isNew = true;
                selectedPet = new Pet(getRandomUUID());
                selectedPet.setOwnerId(currentUserBean.getCurrentCustomer());
                enableEditing = true;
            } else {
                selectedPet = petManagedBean.getPetById(petId);
                categoryName = selectedPet.getCategory().getName();
                enableEditing = currentUserBean.getCurrentCustomer() != null && currentUserBean.getCurrentCustomer().getId() == selectedPet.getOwnerId().getId();
            }
            id = selectedPet.getId();
            name = selectedPet.getName();
            gender = selectedPet.getGender();
            age = selectedPet.getAge();
            color = selectedPet.getColor();
            about = selectedPet.getAbout();
            photoFileName = selectedPet.getPhotoFileName() != null ? selectedPet.getPhotoFileName() : "empty.jpeg";
            ownerId = selectedPet.getOwnerId();
            categories = categoriesFacade.findAll();
        } catch (Exception e) {
            System.out.println("error in PostConstruct SinglePetPageBean");
            System.out.println(e.getCause());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public BigInteger getAge() {
        return age;
    }

    public void setAge(BigInteger age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isShowOutput() {
        return showOutput;
    }

    public void setShowOutput(boolean showOutput) {
        this.showOutput = showOutput;
    }

    public String getOutputTextClass() {
        return outputTextClass;
    }

    public void setOutputTextClass(String outputTextClass) {
        this.outputTextClass = outputTextClass;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public String getPhotoFileName() {
        return photoFileName;
    }

    public void setPhotoFileName(String photoFileName) {
        this.photoFileName = photoFileName;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public String getSelectedPetId() {
        return params.get("pet");
    }

    public void setPetManagedBean(PetManagedBean bean) {
        this.petManagedBean = bean;
    }

    public void setCurrentUserBean(CurrentUserBean bean) {
        this.currentUserBean = bean;
    }

    public boolean isEnableEditing() {
        return enableEditing;
    }

    public void setEnableEditing(boolean enableEditing) {
        this.enableEditing = enableEditing;
    }

    public Customer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Customer ownerId) {
        this.ownerId = ownerId;
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
        FileUploader fileUploader = new FileUploader();
        try {
            System.out.println("In processUpload");
            photoFileName = fileUploader.upload(this.file);

        } catch (Exception e) {
            System.out.println("error in saveData SinglePetPageBean");
            System.out.println(fileUploader.getError());
            System.out.println(e.getCause());
        }
    }

    public void saveData() {
        Pet selectedPet = new Pet();
        try {
            if (!"empty.jpeg".equals(photoFileName)) {
                selectedPet.setPhotoFileName(photoFileName);
            }
            selectedPet.setAbout(about);
            selectedPet.setAge(age);
            selectedPet.setColor(color);
            selectedPet.setGender(gender);
            selectedPet.setId(id);
            selectedPet.setName(name);
            selectedPet.setOwnerId(ownerId);
            selectedPet.setCategory(new Category(categoryName));
            if (isNew) {
                petManagedBean.petsFacade.create(selectedPet);
                statusText = "Pet successfully saved!";

            } else {
                petManagedBean.petsFacade.edit(selectedPet);
                statusText = "Details successfully saved!";
            }
            outputTextClass = "success";
            isNew = false;
        } catch (Exception e) {
            System.out.println("error in saveData SinglePetPageBean");
            System.out.println(e.getCause());
            statusText = "An error occurred :(";
            outputTextClass = "error";
        }
        showOutput = true;
    }

    public void updateStatusText() {
        statusText = "";
    }

    public boolean shouldRenderAboutOwner() {
        return ownerId != null && ownerId.getAbout() != null && ownerId.getAbout().length() > 0;
    }
}
