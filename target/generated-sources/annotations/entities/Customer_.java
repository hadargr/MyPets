package entities;

import entities.Pet;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-09-09T14:06:33")
@StaticMetamodel(Customer.class)
public class Customer_ { 

    public static volatile SingularAttribute<Customer, String> firstName;
    public static volatile SingularAttribute<Customer, String> password;
    public static volatile SingularAttribute<Customer, String> address;
    public static volatile SingularAttribute<Customer, String> gender;
    public static volatile SingularAttribute<Customer, String> familyName;
    public static volatile SingularAttribute<Customer, String> about;
    public static volatile SingularAttribute<Customer, String> id;
    public static volatile CollectionAttribute<Customer, Pet> petCollection;
    public static volatile SingularAttribute<Customer, String> email;

}