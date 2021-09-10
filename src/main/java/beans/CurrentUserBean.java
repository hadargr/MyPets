/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Customer;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author hadargr
 */
@ManagedBean(name = "currentUserBean")
@SessionScoped
public class CurrentUserBean implements Serializable {

    private Customer currentCustomer = null;
    private String status = "loggedout";

    public CurrentUserBean() {
    }

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public String getId() {
        return currentCustomer.getId();
    }

    public void setCurrentCustomer(Customer c) {
        this.currentCustomer = c;
    }

    public String logout() {
        setCurrentCustomer(null);
        return "index?faces-redirect=true";
    }

    public boolean getIsLoggedIn() {
        return currentCustomer != null;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String s) {
        status = currentCustomer == null ? "loggedout" : "loggedin";
    }

    public String getHelloMsg() {
        if (currentCustomer == null) {
            return "";
        }
        return "Hello " + currentCustomer.getFirstName();
    }
}
