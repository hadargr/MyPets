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

/**
 *
 * @author hadargr
 */
@ManagedBean(name = "loginPageBean")
public class LoginPageBean implements Serializable {

    @ManagedProperty(value = "#{customerManagedBean}")
    private CustomerManagedBean customerManagedBean;
    @ManagedProperty(value = "#{currentUserBean}")
    private CurrentUserBean currentUserBean;

    private String email;
    private String password;
    private String error;

    public LoginPageBean() {
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

    public String doLogin() {
        try {
            Customer res;
            try {
                res = customerManagedBean.customersFacade.getByEmail(email);
            } catch (Exception e1) {
                setError("There is no user with that email. You should Sign Up or try a different email.");
                return "login";
            }
            try {
                res = customerManagedBean.customersFacade.customerLogin(email, password);
            } catch (Exception e2) {
                setError("Your password is not correct. Try again.");
                return "login";
            }
            System.out.println("doLogin success");
            System.out.println(res);
            currentUserBean.setCurrentCustomer(res);
            return "index?faces-redirect=true";
        } catch (Exception e) {
            System.out.println("doLogin error");
        }
        return "login";
    }
}
