package com.mycompany.doggrooming.logic;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Owner implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id_owner;
    private String nombre;
    private String cellOwner;

    public Owner() {
    }

    public Owner(int id_owner, String nombre, String cellOwner) {
        this.id_owner = id_owner;
        this.nombre = nombre;
        this.cellOwner = cellOwner;
    }

    public int getId_owner() {
        return id_owner;
    }

    public void setId_owner(int id_owner) {
        this.id_owner = id_owner;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCellOwner() {
        return cellOwner;
    }

    public void setCellOwner(String cellOwner) {
        this.cellOwner = cellOwner;
    }
    
    
    
    
}
