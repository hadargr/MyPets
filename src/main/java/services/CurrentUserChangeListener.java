/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import beans.CurrentUserBean;
import beans.MyPetsPageBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author hadargr
 */
public class CurrentUserChangeListener {

    public void currentUserChanged() {
        System.out.println("CurrentUserChangeListener in processValueChange !!!!! ");
        MyPetsPageBean myPetsPageBean = (MyPetsPageBean) FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap().get("myPetsPageBean");
        myPetsPageBean.clear();
    }
}
