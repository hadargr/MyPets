/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hadargr
 */
@Entity
@Table(name = "pet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pet.findAll", query = "SELECT p FROM Pet p"),
    @NamedQuery(name = "Pet.findById", query = "SELECT p FROM Pet p WHERE p.id = :id"),
    @NamedQuery(name = "Pet.findByName", query = "SELECT p FROM Pet p WHERE p.name = :name"),
    @NamedQuery(name = "Pet.findByGender", query = "SELECT p FROM Pet p WHERE p.gender = :gender"),
    @NamedQuery(name = "Pet.findByAge", query = "SELECT p FROM Pet p WHERE p.age = :age"),
    @NamedQuery(name = "Pet.findByColor", query = "SELECT p FROM Pet p WHERE p.color = :color"),
    @NamedQuery(name = "Pet.findByAbout", query = "SELECT p FROM Pet p WHERE p.about = :about"),
    @NamedQuery(name = "Pet.findByPhotoFileName", query = "SELECT p FROM Pet p WHERE p.photoFileName = :photoFileName")})
public class Pet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "id")
    private String id;
    @Size(max = 100)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "gender")
    private String gender;
    @Column(name = "age")
    private BigInteger age;
    @Size(max = 2147483647)
    @Column(name = "color")
    private String color;
    @Size(max = 300)
    @Column(name = "about")
    private String about;
    @Size(max = 2147483647)
    @Column(name = "photo_file_name")
    private String photoFileName;
    @JoinColumn(name = "category", referencedColumnName = "name")
    @ManyToOne(optional = false)
    private Category category;
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Customer ownerId;

    public Pet() {
    }

    public Pet(String id) {
        this.id = id;
    }

    public Pet(String id, String gender) {
        this.id = id;
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public BigInteger getAge() {
        return age;
    }

    public void setAge(BigInteger age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getPhotoFileName() {
        return photoFileName;
    }

    public void setPhotoFileName(String photoFileName) {
        this.photoFileName = photoFileName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Customer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Customer ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pet)) {
            return false;
        }
        Pet other = (Pet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Pet[ id=" + id + " ]";
    }
    
}
