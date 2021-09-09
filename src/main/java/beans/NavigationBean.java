package beans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import services.Utils;

/**
 *
 * @author hadargr
 */
@ManagedBean(name = "navigationBean")
@SessionScoped
public class NavigationBean implements Serializable {

    public NavigationBean() {
    }

    public void navigateToMyPets() {
        Utils.navigateToPage("mypets");
    }

    public void navigateToFindPet() {
        Utils.navigateToPage("findpet");
    }

    public void navigateToFindDog() {
        Utils.navigateToPage("findpet", "category", "DOG");
    }

    public void navigateToFindCat() {
        Utils.navigateToPage("findpet", "category", "CAT");
    }

    public void navigateToSignup() {
        Utils.navigateToPage("signup");
    }
}
