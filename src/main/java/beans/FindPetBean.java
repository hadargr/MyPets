/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import ejb.CategoryFacadeLocal;
import ejb.PetFacadeLocal;
import entities.Category;
import entities.Pet;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author hadargr
 */
@ManagedBean(name = "findPetBean")
@ViewScoped
public class FindPetBean implements Serializable {

    @EJB
    CategoryFacadeLocal categoriesFacade;
    @EJB
    PetFacadeLocal petsFacade;
    @ManagedProperty(value = "#{currentUserBean}")
    private CurrentUserBean currentUserBean;
    private List<Category> categories;
    private List<Pet> currentPets;
    private List<String> petsColors;
    private Category selectedCategory = null;
    private String selectedColor = "ALL";
    private String menuLabel = "ALL";

    public FindPetBean() {
    }

    @PostConstruct
    public void init() {
        try {
            categories = categoriesFacade.findAll();
            currentPets = petsFacade.findAll();
            petsColors = Arrays.asList("ORANGE", "YELLOW", "WHITE", "BLACK", "GRAY", "GREY", "GREEN", "BLUE", "BROW", "ALL");
            Collections.sort(petsColors);
        } catch (Exception e) {
            System.out.println("error in PostConstruct FindPetBean");
            System.out.println(e.getCause());
        }
    }

    public CurrentUserBean getCurrentUserBean() {
        return currentUserBean;
    }

    public void setCurrentUserBean(CurrentUserBean currentUserBean) {
        this.currentUserBean = currentUserBean;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Category getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(Category selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public List<Pet> getCurrentPets() {
        return currentPets;
    }

    public void setCurrentPets(List<Pet> currentPets) {
        this.currentPets = currentPets;
    }

    public String getMenuLabel() {
        return menuLabel;
    }

    public void setMenuLabel(String menuLabel) {
        this.menuLabel = menuLabel;
    }

    public String getSelectedColor() {
        return selectedColor;
    }

    public void setSelectedColor(String selectedColor) {
        this.selectedColor = selectedColor;
        updatePetsList();
    }

    public List<String> getPetsColors() {
        return petsColors;
    }

    public void setPetsColors(List<String> petsColors) {
        this.petsColors = petsColors;
    }

    public void selectCategory(Category newCategory) {
        this.selectedCategory = newCategory;
        menuLabel = selectedCategory.getName();
        updatePetsList();
    }

    public void selectAll() {
        this.selectedCategory = null;
        menuLabel = "ALL";
        updatePetsList();
    }

    private void updatePetsList() {
        if (selectedCategory == null) {
            if ("ALL".equals(selectedColor)) {
                currentPets = petsFacade.findAll();
            } else {
                currentPets = petsFacade.getPetsByColor(selectedColor);
            }
        } else if ("ALL".equals(selectedColor)) {
            currentPets = petsFacade.getPetsByCategory(selectedCategory.getName());
        } else {
            currentPets = petsFacade.getPetsByCategoryAndColor(selectedCategory.getName(), selectedColor);
        }
    }

    public String getIcon(Category category) {
        String icon = "";
        if (category == null) {
            return "fa fa-fw fa-asterisk";
        }
        switch (category.getName()) {
            case "DOG":
                icon = "fa fa-fw fa-dog";
                break;
            case "CAT":
                icon = "fa fa-fw fa-cat";
                break;
            case "RABBIT":
                icon = "fa fa-fw fa-carrot";
                break;
            case "BIRD":
                icon = "fa fa-fw fa-kiwi-bird";
                break;
            case "FISH":
                icon = "fa fa-fw fa-fish";
                break;
            case "TURTLE":
                icon = "fa fa-fw fa-pizza-slice";
                break;

        }
        return icon;
    }

    public String selectPet(Pet p) {
        return "singlepet?faces-redirect=true&pet=" + p.getId();
    }
}
