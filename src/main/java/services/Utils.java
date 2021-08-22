/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.UUID;

/**
 *
 * @author hadargr
 */
public class Utils {
    public static FileUploader fileUploader = new FileUploader();
    private Utils() { }
    
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    
    public static String getRandomUUID() {
        return UUID.randomUUID().toString();
    }
    
}
