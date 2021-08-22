/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.Pet;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author hadargr
 */
@Local
public interface PetFacadeLocal {

    void create(Pet pet);

    void edit(Pet pet);

    void remove(Pet pet);

    Pet find(Object id);

    List<Pet> findAll();

    List<Pet> findRange(int[] range);

    int count();
    
    List<Pet> getPetsByOwnerId(String id);
    
}
