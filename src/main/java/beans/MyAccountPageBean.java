/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Customer;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import services.Utils;

/**
 *
 * @author hadargr
 */
@ManagedBean(name = "myAccountPageBean")
@ViewScoped
public class MyAccountPageBean implements Serializable {

    @ManagedProperty(value = "#{customerManagedBean}")
    private CustomerManagedBean customerManagedBean;
    @ManagedProperty(value = "#{currentUserBean}")
    private CurrentUserBean currentUserBean;
    private String email = null;
    private String password = null;
    private String firstName = null;
    private String lastName = null;
    private String address = null;
    private String gender = null;
    private String about = null;
    private String error = null;

    public MyAccountPageBean() {
    }

    @PostConstruct
    public void init() {
        if (currentUserBean.getCurrentCustomer() == null) {
            Utils.navigateToPage("index");
        }
        email = currentUserBean.getCurrentCustomer().getEmail();
        firstName = currentUserBean.getCurrentCustomer().getFirstName();
        lastName = currentUserBean.getCurrentCustomer().getFamilyName();
        address = currentUserBean.getCurrentCustomer().getAddress();
        about = currentUserBean.getCurrentCustomer().getAbout();
        gender = currentUserBean.getCurrentCustomer().getGender();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setCustomerManagedBean(CustomerManagedBean bean) {
        this.customerManagedBean = bean;
    }

    public void setCurrentUserBean(CurrentUserBean bean) {
        this.currentUserBean = bean;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
        System.out.println(error);
    }

    public void doCancel() {
        init();
    }

    public void doUpdate() {
        try {
            Customer customer = new Customer(currentUserBean.getCurrentCustomer().getId());
            customer.setAbout(about);
            customer.setAddress(address);
            customer.setEmail(email);
            customer.setFamilyName(lastName);
            customer.setFirstName(firstName);
            customer.setGender(gender);
            customer.setPassword(currentUserBean.getCurrentCustomer().getPassword());
            customerManagedBean.customersFacade.edit(customer);
            currentUserBean.setCurrentCustomer(customer);
        } catch (Exception e) {
            System.out.println("doUpdate error");
        }
    }

    public void clear() {
        email = null;
        password = null;
        firstName = null;
        lastName = null;
        address = null;
        gender = null;
        about = null;
        error = null;
    }
}
