package com.mycompany.doggrooming.logic;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Pets implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int num_customer;
    private String name;
    private String breed;
    private String color;
    private String allergic;
    private String atenttion_special;
    private String observations;
    @OneToOne
    private Owner aOwner;

    public Pets() {
    }

    public Pets(int num_customer, String name, String breed, String color, String allergic, String atenttion_special, String observations, Owner aOwner) {
        this.num_customer = num_customer;
        this.name = name;
        this.breed = breed;
        this.color = color;
        this.allergic = allergic;
        this.atenttion_special = atenttion_special;
        this.observations = observations;
        this.aOwner = aOwner;
    }

    public int getNum_customer() {
        return num_customer;
    }

    public void setNum_customer(int num_customer) {
        this.num_customer = num_customer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAllergic() {
        return allergic;
    }

    public void setAllergic(String allergic) {
        this.allergic = allergic;
    }

    public String getAtenttion_special() {
        return atenttion_special;
    }

    public void setAtenttion_special(String atenttion_special) {
        this.atenttion_special = atenttion_special;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public Owner getaOwner() {
        return aOwner;
    }

    public void setaOwner(Owner aOwner) {
        this.aOwner = aOwner;
    }
    
    
            
}
