package com.mycompany.doggrooming.logic;

import com.mycompany.doggrooming.persistence.OwnerJpaController;
import com.mycompany.doggrooming.persistence.PetsJpaController;
import com.mycompany.doggrooming.persistence.exceptions.NonexistentEntityException;
import java.util.List;


public class Controller {
    
   //ControllerPersistence controlPersist = new ControllerPersistence();
    OwnerJpaController ownerJpaController = new OwnerJpaController();
    PetsJpaController petsJpaController = new PetsJpaController();
    
    public void save(String namePet, String breed, String color, 
            String Observations, String allergic, String atenttion, 
            String OwnersName, String OwnersCell) {
        
         //se creo el dueño y se asigno valores
        Owner owner = new Owner ();
        owner.setNombre(OwnersName);
        owner.setCellOwner(OwnersCell);
        ownerJpaController.create(owner);
        
         // se creo la masctota y se asigno valores
        Pets pet = new Pets ();
        pet.setName(namePet);
        pet.setBreed(breed);
        pet.setColor(color);
        pet.setAllergic(allergic);
        pet.setAtenttion_special(atenttion);
        pet.setObservations(Observations);
        pet.setaOwner(owner);
        petsJpaController.create(pet);
        
        
    }

    public List<Pets> bringpets() {
    
        return petsJpaController.findPetsEntities();
        
    }

    public void deletePet(int num_customer) throws NonexistentEntityException {
        petsJpaController.destroy(num_customer);
    }


  public Pets bringPet(int num_customer) {
     return   petsJpaController.findPets(num_customer);
  }
  public void modifyPet (Pets pet, String namePet, String breed, String color, 
          String observations, String allergic, String atenttionSpecial, 
          String OwnersName, String OwnersCell) throws Exception{
     
      pet.setName(namePet);
      pet.setBreed(breed);
      pet.setColor(color);
      pet.setObservations(observations);
      pet.setAllergic(allergic);
      
      petsJpaController.edit(pet);
      
      
      Owner owner = ownerJpaController.findOwner(pet.getaOwner().getId_owner());
      owner.setCellOwner(OwnersCell);
      owner.setNombre(OwnersName);
      ownerJpaController.edit(owner);
  }
  
  
}


















