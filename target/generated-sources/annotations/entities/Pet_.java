package entities;

import entities.Category;
import entities.Customer;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-09-05T01:04:29")
@StaticMetamodel(Pet.class)
public class Pet_ { 

    public static volatile SingularAttribute<Pet, String> photoFileName;
    public static volatile SingularAttribute<Pet, String> gender;
    public static volatile SingularAttribute<Pet, String> color;
    public static volatile SingularAttribute<Pet, String> name;
    public static volatile SingularAttribute<Pet, String> about;
    public static volatile SingularAttribute<Pet, String> id;
    public static volatile SingularAttribute<Pet, Category> category;
    public static volatile SingularAttribute<Pet, Customer> ownerId;
    public static volatile SingularAttribute<Pet, BigInteger> age;

}