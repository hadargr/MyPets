/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Customer;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import static services.Utils.getRandomUUID;

/**
 *
 * @author hadargr
 */
@ManagedBean(name = "signupPageBean")
public class SignupPageBean implements Serializable {
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
    
    public SignupPageBean() {
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
    

    public String doRegister() {
        try {
            Customer customer;
            try {
            customer = customerManagedBean.customersFacade.getByEmail(email);
            setError("That email’s already in our system.");
            return "signup";
            } catch (Exception e) {
                String id = getRandomUUID();
                customer = new Customer(id, firstName,email, password);
                if (lastName != null) {
                    customer.setFamilyName(lastName);
                }
                if (address != null) {
                    customer.setAddress(address);
                }
                if (gender != null) {
                    customer.setGender(gender);
                }
                if (about != null) {
                    customer.setAbout(about);
                }
                customerManagedBean.customersFacade.create(customer);
                currentUserBean.setCurrentCustomer(customer);
                return "index?faces-redirect=true";
            }
        } catch(Exception e) {
            System.out.println("doRegister error");
        }
        return "signup";
    }
}
