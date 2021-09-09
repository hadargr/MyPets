/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.UUID;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.FacesContext;

/**
 *
 * @author hadargr
 */
public class Utils {

    public static FileUploader fileUploader = new FileUploader();

    private Utils() {
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static String getRandomUUID() {
        return UUID.randomUUID().toString();
    }

    public static void navigateToPage(String page, String... parameters) {
        ConfigurableNavigationHandler configurableNavigationHandler
                = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance()
                        .getApplication().getNavigationHandler();
        String params = "";
        if (parameters != null && parameters.length % 2 == 0) {
            for (int i = 0; i < parameters.length; i += 2) {
                params += "&" + parameters[i] + "=" + parameters[i + 1];
            }
        }
        configurableNavigationHandler.performNavigation(page + "?faces-redirect=true" + params);
    }

}
