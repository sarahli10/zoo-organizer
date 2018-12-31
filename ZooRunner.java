// Class Name: ZooRunner
// Authors: Jacob, Ying, Sarah (Sarah and Ying coded IOExceptions)
// Date: June 5 - June 9, 2017
// Purpose: Runs all functions of the Zoo Class based on user input through a menu.

import java.util.*;
import java.io.*;

public class ZooRunner {
   public static void main(String[] args) {
   
      Scanner sc = new Scanner(System.in);
      Scanner st = new Scanner(System.in);
      
      boolean stop = false;
      int chooseSSA = 0;
      int choose = 0;
      int chooseAnimal = 0;
      String food = "";
      int amt = 0;
      String filename;
      int attendance = 0;
   
      boolean goodData = false;
      boolean successful = false;
   
   	// staff
      String nameStaff = "";
      int idStaff = 0;
      double pay = 0;
      String job = "";
   
   	// animal
      String species = "";
      String nameAnimal = "";
      String type = "";
      int idAnimal = 0;
      String habitat = "";
      char gender = 'M';
      int age = 0;
      double weight = 0;
      boolean health = true;
      String mealPlan = "";
      int foodQuantity = 0;
   
      Zoo torontoZoo = new Zoo("staff.txt", "storage.txt", "animals.txt");  
      
      while (!stop) {
         while (!goodData) { // FIRST YOU MUST TRY¡­¡­¡­¡­ THEN YOU MUST CATCH :P
            try {
               System.out.println("\nMain Menu");
               System.out.println("1: Storage");
               System.out.println("2: Staff");
               System.out.println("3: Animal");
               System.out.println("4: Other");
               System.out.println("Enter a selection or enter -1 to exit program");
               chooseSSA = sc.nextInt();
               goodData = true;
            } // end of try block
            catch (InputMismatchException e) {
               System.out.println("You entered bad data.");
               System.out.println("Please try again.\n");
               String flush = sc.next();
            } // end of catch block
         } // end of while
         
         goodData = false;
         if (chooseSSA == -1) {
            System.out.println("End program");
            stop = true;   
         } 
         else if (stop != true && chooseSSA == 1) {
            while (!goodData) {
               try {
                  System.out.println("\nStorage Menu");
                  System.out.println("1. Restock Food");
                  System.out.println("2. Deduct Food");
                  System.out.println("3. Restock Medication");
                  System.out.println("4. Deduct Medication");
                  System.out.println("-1. Go Back");
                  choose = sc.nextInt();
                  goodData = true;
               } // end of try
               catch (InputMismatchException e) {
                  System.out.println("You entered bad data.");
                  System.out.println("Please try again.\n");
                  String flush = sc.next();
               } // end of catch block
            } // end of while
            
            switch (choose) {
               case 1:
                  goodData = false;
                  while (!goodData) {
                     try {
                        do{
                           System.out.println("Enter \"Vegetables\", \"Meat\", or \"Critters\"");
                           food = st.nextLine();
                        }while(food.equals("Vegetables") == false && food.equals("Meat")  == false && food.equals("Critters") == false);
                        goodData = true;
                     } // end of try
                     catch (InputMismatchException e) {
                        System.out.println("You entered bad data.");
                        System.out.println("Please try again.\n");
                        String flush = sc.next();
                     } // end of catch block
                  } // end of while
                  
                  goodData = false;
                  while (!goodData) {
                     try {      
                        System.out.println("Enter amount");
                        amt = sc.nextInt();
                        goodData = true;
                     
                        successful = torontoZoo.restockFood(food, amt);
                        if (successful){
                           System.out.println("Food successfully restocked.");
                           System.out.println();
                           torontoZoo.outputTheStorage();
                           System.out.println();
                        }
                        else{
                           System.out.println("Food could not be restocked.");
                        }
                     } // end of try
                     catch (InputMismatchException e) {
                        System.out.println("You entered bad data.");
                        System.out.println("Please try again.\n");
                        String flush = sc.next();
                     } // end of catch block
                  } // end of while
                  break;
                  
               case 2:
                  goodData = false;
                  while (!goodData) {
                     try {
                        do{
                           System.out.println("Enter \"Vegetables\", \"Meat\", or \"Critters\"");
                           food = st.nextLine();
                        }while(food.equals("Vegetables") == false && food.equals("Meat")  == false && food.equals("Critters") == false);
                        goodData = true;
                     } // end of try
                     catch (InputMismatchException e) {
                        System.out.println("You entered bad data.");
                        System.out.println("Please try again.\n");
                        String flush = sc.next();
                     } // end of catch block
                  } // end of while
                  
                  goodData = false;
                  while (!goodData) {
                     try {            
                        System.out.println("Enter amount");
                        amt = sc.nextInt();
                        goodData = true;
                     
                        successful = torontoZoo.deductFood(food, amt);
                        if (successful){
                           System.out.println("Food successfully deducted.");
                           System.out.println();
                           torontoZoo.outputTheStorage();
                           System.out.println();
                        }
                        else{
                           System.out.println("Could not update storage.");
                        }
                     } // end of try
                     catch (InputMismatchException e) {
                        System.out.println("You entered bad data.");
                        System.out.println("Please try again.\n");
                        String flush = sc.next();
                     } // end of catch block
                  } // end of while
                  break;
                  
               case 3:
                  goodData = false;
                  while (!goodData) {
                     try {   
                        System.out.println("Enter amount of medication to restock");
                        amt = sc.nextInt();
                        goodData = true;
                     
                        successful = torontoZoo.restockMeds(amt);
                        if (successful){
                           System.out.println("Medication successfuly restocked.");
                           System.out.println();
                           torontoZoo.outputTheStorage();
                           System.out.println();
                        }
                        else{
                           System.out.println("Could not update medication.");
                        }
                     } // end of try
                     catch (InputMismatchException e) {
                        System.out.println("You entered bad data.");
                        System.out.println("Please try again.\n");
                        String flush = sc.next();
                     } // end of catch block
                  } // end of while
                  break;
                  
               case 4:
                  goodData = false;
                  while (!goodData) {
                     try {   
                        System.out.println("Enter amount of medication to deduct");
                        amt = sc.nextInt();
                        goodData = true;
                     
                                 
                        successful = torontoZoo.deductMeds(amt);
                        if (successful){
                           System.out.println("Medication successfuly deducted.");
                           System.out.println();
                           torontoZoo.outputTheStorage();
                           System.out.println();
                        }
                        else{
                           System.out.println("Could not update Medication.");
                        }
                     } // end of try
                     catch (InputMismatchException e) {
                        System.out.println("You entered bad data.");
                        System.out.println("Please try again.\n");
                        String flush = sc.next();
                     } // end of catch block
                  } // end of while
                  break;
            } // end of choose case structure
            choose = 0;
            goodData = false;
            
         } 
         else if (!stop && chooseSSA == 2){
            goodData = false;
            while (!goodData) {
               try {
                  System.out.println("\nStaff Menu");
                  System.out.println("1. List all staff");
                  System.out.println("2. Add staff");
                  System.out.println("3. Remove staff");
                  System.out.println("-1. Go Back");
                  choose = sc.nextInt();
                  goodData = true;
               } // end of try
               catch (InputMismatchException e) {
                  System.out.println("You entered bad data.");
                  System.out.println("Please try again.\n");
                  String flush = sc.next();
               } // end of catch block
            } // end of while
            
            goodData = false;         
            switch (choose) {
                  
               case 1:
                  torontoZoo.listAllStaff();
                  break;
                  
               case 2:
                  goodData = false;
                  while (!goodData) {
                     try {
                        System.out.print("Enter Staff name: ");
                        nameStaff = st.nextLine();
                        goodData = true;
                     } // end of try
                     catch (InputMismatchException e) {
                        System.out.println("You entered bad data.");
                        System.out.println("Please try again.\n");
                        String flush = sc.next();
                     } // end of catch block
                  } // end of while
                  
                  goodData = false;
                  while (!goodData) {
                     try {
                        successful = false;
                        do{
                           System.out.print("Enter Staff ID: ");
                           idStaff = sc.nextInt();
                           successful = torontoZoo.validStaffID(idStaff);
                           if(!successful){
                              System.out.println("ID taken. Try again.");
                           }
                        } while(!successful);
                        goodData = true;
                     } // end of try
                     catch (InputMismatchException e) {
                        System.out.println("You entered bad data.");
                        System.out.println("Please try again.\n");
                        String flush = sc.next();
                     } // end of catch block
                  } // end of while
                  
                  goodData = false;
                  while (!goodData) {
                     try {         
                        System.out.print("Enter the employee's pay: ");
                        pay = sc.nextDouble();
                        goodData = true;
                     } // end of try
                     catch (InputMismatchException e) {
                        System.out.println("You entered bad data.");
                        System.out.println("Please try again.\n");
                        String flush = sc.next();
                     } // end of catch block
                  } // end of while
                  
                  goodData = false;
                  while (!goodData) {
                     try {         
                        System.out.print("Enter the employee's job: ");
                        job = st.nextLine();
                        goodData = true;
                                    
                        successful = torontoZoo.addStaff(nameStaff, idStaff, pay, job);
                        if (successful)
                           System.out.println("Staff successfuly added.");
                        else
                           System.out.println("Could not add staff. Maximum staff may have been reached");
                     } // end of try
                     catch (InputMismatchException e) {
                        System.out.println("You entered bad data.");
                        System.out.println("Please try again.\n");
                        String flush = sc.next();
                     } // end of catch block
                  } // end of while
                  break;
                  
               case 3:
                  goodData = false;
                  while (!goodData) {
                     try {
                        System.out.print("Enter Id of staff: ");
                        idStaff = sc.nextInt();
                                    
                        successful = torontoZoo.removeStaff(idStaff);
                        if (successful)
                           System.out.println("Staff successfuly removed.");
                        else
                           System.out.println("Could not remove staff. ID of staff may not exist");
                        break;
                     } // end of try
                     catch (InputMismatchException e) {
                        System.out.println("You entered bad data.");
                        System.out.println("Please try again.\n");
                        String flush = sc.next();
                     } // end of catch block
                  } // end of while
            } // end of choose case structure
            choose = 0;
            goodData = false;
            
         } 
         else if (!stop && chooseSSA == 3){
            goodData = false;
            while (!goodData) {
               try {
                  System.out.println("\nAnimal Menu");
                  System.out.println("1. List animal");
                  System.out.println("2. Add animal");
                  System.out.println("3. Remove animal");
                  System.out.println("4. Sort animals by ID");
                  System.out.println("5. Display animal info");
                  System.out.println("6. Display all sick animals");
                  System.out.println("7. Update animal health");
                  System.out.println("8. Find number of animals by type");
                  System.out.println("9. Find number of animals by habitat");
                  System.out.println("-1. Go Back");
                  choose = sc.nextInt();
                  goodData = true;
               } // end of try
               catch (InputMismatchException e) {
                  System.out.println("You entered bad data.");
                  System.out.println("Please try again.\n");
                  String flush = sc.next();
               } // end of catch block
            } // end of while
                     
            switch (choose) {
                  
               case 1:
                  goodData = false;
                  while (!goodData) {
                     try {
                        System.out.println("1. List all animal");
                        System.out.println("2. List all amphibians");
                        System.out.println("3. List all birds");
                        System.out.println("4. List all mammals");
                        System.out.println("5. List all marine animals");
                        System.out.println("6. List all reptiles");
                        chooseAnimal = sc.nextInt();
                        goodData = true;
                     } // end of try
                     catch (InputMismatchException e) {
                        System.out.println("You entered bad data.");
                        System.out.println("Please try again.\n");
                        String flush = sc.next();
                     } // end of catch block
                  } // end of while
                     
                  if (chooseAnimal == 1){
                     torontoZoo.listAllAnimals();
                  } 
                  else if (chooseAnimal == 2){
                     torontoZoo.listAllAmphib();
                  } 
                  else if (chooseAnimal == 3){
                     torontoZoo.listAllBird();
                  } 
                  else if (chooseAnimal == 4){
                     torontoZoo.listAllMammal();
                  } 
                  else if (chooseAnimal == 5){
                     torontoZoo.listAllMarine();
                  } 
                  else if (chooseAnimal == 6){
                     torontoZoo.listAllReptile();
                  }//end of if
                  chooseAnimal = 0;
                  break;
                  
               case 2:
                  goodData = false;
                  while (!goodData) {
                     try {
                        System.out.println("1. Add an amphibian");
                        System.out.println("2. Add a bird");
                        System.out.println("3. Add a mammal");
                        System.out.println("4. Add a marine animal");
                        System.out.println("5. Add a reptile");
                        chooseAnimal = sc.nextInt();
                        goodData = true;
                     } // end of try
                     catch (InputMismatchException e) {
                        System.out.println("You entered bad data.");
                        System.out.println("Please try again.\n");
                        String flush = sc.next();
                     } // end of catch block
                  } // end of while loop
                        
                  goodData = false;
                  while (!goodData) {
                     try {
                        System.out.println("Enter the species");
                        species = st.nextLine();
                        goodData = true;
                     } // end of try
                     catch (InputMismatchException e) {
                        System.out.println("You entered bad data.");
                        System.out.println("Please try again.\n");
                        String flush = sc.next();
                     } // end of catch block
                  } // end of while loop
                  
                  goodData = false;
                  while (!goodData) {
                     try {
                        do{
                           System.out.println("Enter the type");
                           type = st.nextLine();
                        } while(torontoZoo.validAnimalType(type) == false);
                        goodData = true;
                     } // end of try
                     catch (InputMismatchException e) {
                        System.out.println("You entered bad data.");
                        System.out.println("Please try again.\n");
                        String flush = sc.next();
                     } // end of catch block
                  } // end of while loop
                  
                  goodData = false;
                  while (!goodData) {
                     try {
                        System.out.println("Enter the name of animal");
                        nameAnimal = st.nextLine();
                        goodData = true;
                     } // end of try
                     catch (InputMismatchException e) {
                        System.out.println("You entered bad data.");
                        System.out.println("Please try again.\n");
                        String flush = sc.next();
                     } // end of catch block
                  } // end of while loop
                  
                  goodData = false;
                  while (!goodData) {
                     try {
                        successful = false;
                        do{
                           System.out.println("Enter ID ");
                           idAnimal = sc.nextInt();
                           successful = torontoZoo.validAnimalID(idAnimal);
                           if(!successful){
                              System.out.println("ID taken. Try again.");
                           }
                        } while(!successful);
                        goodData = true;
                     } // end of try
                     catch (InputMismatchException e) {
                        System.out.println("You entered bad data.");
                        System.out.println("Please try again.\n");
                        String flush = sc.next();
                     } // end of catch block
                  } // end of while loop
                  
                  goodData = false;
                  while (!goodData) {
                     try {
                        System.out.println("Enter the habitat");
                        habitat = st.nextLine();
                        goodData = true;
                     } // end of try
                     catch (InputMismatchException e) {
                        System.out.println("You entered bad data.");
                        System.out.println("Please try again.\n");
                        String flush = sc.next();
                     } // end of catch block
                  } // end of while loop
                  
                  goodData = false;
                  while (!goodData) {
                     try {
                        System.out.println("Enter the gender");
                        gender = st.nextLine().charAt(0);
                        goodData = true;
                     } // end of try
                     catch (InputMismatchException e) {
                        System.out.println("You entered bad data.");
                        System.out.println("Please try again.\n");
                        String flush = sc.next();
                     } // end of catch block
                  } // end of while loop
                        
                  goodData = false;
                  while (!goodData) {
                     try {         
                        System.out.println("Enter the age");
                        age = sc.nextInt();
                        goodData = true;
                     } // end of try
                     catch (InputMismatchException e) {
                        System.out.println("You entered bad data.");
                        System.out.println("Please try again.\n");
                        String flush = sc.next();
                     } // end of catch block
                  } // end of while loop
                        
                  goodData = false;
                  while (!goodData) {
                     try { 
                        System.out.println("Enter the weight");
                        weight = sc.nextDouble();
                        goodData = true;
                     } // end of try
                     catch (InputMismatchException e) {
                        System.out.println("You entered bad data.");
                        System.out.println("Please try again.\n");
                        String flush = sc.next();
                     } // end of catch block
                  } // end of while loop
                                   
                  goodData = false;
                  while (!goodData) {
                     try {
                        System.out.println("Enter if the animal is healthy (true/false)");
                        health = sc.nextBoolean();
                        goodData = true;
                     } // end of try
                     catch (InputMismatchException e) {
                        System.out.println("You entered bad data.");
                        System.out.println("Please try again.\n");
                        String flush = sc.next();
                     } // end of catch block
                  } // end of while loop
                  
                  goodData = false;
                  while (!goodData) {
                     try {
                     do{
                        System.out.println("Enter the type of food the animal eats");
                        mealPlan = st.nextLine();
                     } while (mealPlan.equals("Vegetables") == false && mealPlan.equals("Meat")  == false && mealPlan.equals("Critters") == false);
                        goodData = true;
                     } // end of try
                     catch (InputMismatchException e) {
                        System.out.println("You entered bad data.");
                        System.out.println("Please try again.\n");
                        String flush = sc.next();
                     } // end of catch block
                  } // end of while loop
                        
                  goodData = false;
                  while (!goodData) {
                     try {
                        System.out.println("Enter the amount of food the animal eats");
                        foodQuantity = sc.nextInt();
                        goodData = true;
                     } // end of try
                     catch (InputMismatchException e) {
                        System.out.println("You entered bad data.");
                        System.out.println("Please try again.\n");
                        String flush = sc.next();
                     } // end of catch block
                  } // end of while loop
                        
                  if (chooseAnimal == 1){
                     successful = torontoZoo.addAmphib(species, type, nameAnimal, idAnimal, habitat, gender, age, weight, health, mealPlan, foodQuantity);
                     if (successful){
                        System.out.println("Animal successfuly added");
                     } 
                     else {
                        System.out.println("Could not add animal. There was an error.");
                     }
                  } 
                  else if (chooseAnimal == 2){
                     successful = torontoZoo.addBird(species, type, nameAnimal, idAnimal, habitat, gender, age, weight, health, mealPlan, foodQuantity);
                     System.out.println("addBird");
                     if (successful)
                        System.out.println("Animal successfuly added");
                     else
                        System.out.println("Could not add animal. Max number of animals may have been reached");
                  } 
                  else if (chooseAnimal == 3){
                     successful = torontoZoo.addMammal(species, type, nameAnimal, idAnimal, habitat, gender, age, weight, health, mealPlan, foodQuantity);
                     if (successful)
                        System.out.println("Animal successfuly added");
                     else
                        System.out.println("Could not add animal. Max number of animals may have been reached");
                  } 
                  else if (chooseAnimal == 4){
                     successful = torontoZoo.addMarine(species, type, nameAnimal, idAnimal, habitat, gender, age, weight, health, mealPlan, foodQuantity);
                     if (successful)
                        System.out.println("Animal successfuly added");
                     else
                        System.out.println("Could not add animal. Max number of animals may have been reached");
                  } 
                  else if (chooseAnimal == 5){
                     successful = torontoZoo.addReptile(species, type, nameAnimal, idAnimal, habitat, gender, age, weight, health, mealPlan, foodQuantity);
                     if (successful)
                        System.out.println("Animal successfuly added");
                     else
                        System.out.println("Could not add animal. Max number of animals may have been reached");
                  } // end of if
                  chooseAnimal = 0;
                  break;
                  
               case 3:
                  goodData = false;
                  while (!goodData) {
                     try {
                        System.out.print("Enter animal ID: ");
                        idAnimal = sc.nextInt();
                        goodData = true;
                                    
                        successful = torontoZoo.removeAnimal(idAnimal);
                        if (successful)
                           System.out.println("Animal successfuly removed.");
                        else
                           System.out.println("Could not remove animal. ID of animal may not exist.");
                        break;
                     } // end of try
                     catch (InputMismatchException e) {
                        System.out.println("You entered bad data.");
                        System.out.println("Please try again.\n");
                        String flush = sc.next();
                     } // end of catch block
                  } // end of while loop 
                  
               case 4:
                  torontoZoo.sortAnimalsByID();
                  System.out.println("Animals have been sorted");
                  break;
                  
               case 5:
                  goodData = false;
                  while (!goodData) {
                     try {
                        System.out.print("Enter animal ID: ");
                        idAnimal = sc.nextInt();
                        goodData = true;
                        torontoZoo.displayAnimalInfo(idAnimal);
                     } // end of try
                     catch (InputMismatchException e) {
                        System.out.println("You entered bad data.");
                        System.out.println("Please try again.\n");
                        String flush = sc.next();
                     } // end of catch block
                  } // end of while loop
                  break;
                  
               case 6:
                  System.out.println(torontoZoo.returnSickAnimals(0,torontoZoo.getnumAnimals()));
                  break;
                  
               case 7:
                  goodData = false;
                  while (!goodData) {
                     try {
                        System.out.print("Enter animal ID: ");
                        idAnimal = sc.nextInt();
                        goodData = true;
                     } // end of try
                     catch (InputMismatchException e) {
                        System.out.println("You entered bad data.");
                        System.out.println("Please try again.\n");
                        String flush = sc.next();
                     } // end of catch block
                  } // end of while loop
                        
                  goodData = false;
                  while (!goodData) {
                     try {           
                        System.out.print("Is the animal healthy?(true/false): ");
                        health = sc.nextBoolean();
                        goodData = true;
                        torontoZoo.updateAnimalHealth(idAnimal, health);
                     } // end of try
                     catch (InputMismatchException e) {
                        System.out.println("You entered bad data.");
                        System.out.println("Please try again.\n");
                        String flush = sc.next();
                     } // end of catch block
                  } // end of while loop
                  break;
                  
               case 8:
                  goodData = false;
                  while (!goodData) {
                     try {
                        int numType;
                        System.out.print("Enter animal type: ");
                        type = st.nextLine();
                        goodData = true;
                        numType = torontoZoo.findNumByType(type);
                        if (numType == 0)
                           System.out.println("There are no animals by this type");
                        else
                           System.out.println(numType + " animals are of " + type + " type.");
                     } // end of try
                     catch (InputMismatchException e) {
                        System.out.println("You entered bad data.");
                        System.out.println("Please try again.\n");
                        String flush = sc.next();
                     } // end of catch block
                  } // end of while loop
                  break;
                  
               case 9:
                  goodData = false;
                  while (!goodData) {
                     try {
                        int numHab;
                        System.out.print("Enter habitat: ");
                        habitat = st.nextLine();
                        goodData = true;
                        numHab = torontoZoo.findNumByHabitat(habitat);
                        if (numHab == 0)
                           System.out.println("There are no animals living in this habitat");
                        else
                           System.out.println(numHab + " animals are in " + habitat + " habitat.");
                     } // end of try
                     catch (InputMismatchException e) {
                        System.out.println("You entered bad data.");
                        System.out.println("Please try again.\n");
                        String flush = sc.next();
                     } // end of catch block
                  } // end of while loop
                  break;
            } // end of choose case structure
            choose = 0;
            goodData = false;
         
         } 
         else if(!stop && chooseSSA == 4){
            goodData = false;
            while (!goodData) {
               try {
                  System.out.println("\nOther Menu");
                  System.out.println("1. Profit for the day");
                  System.out.println("2. Print Map");
                  System.out.println("-1. Go Back");
                  choose = sc.nextInt();
                  goodData = true;
               } // end of try
               catch (InputMismatchException e) {
                  System.out.println("You entered bad data.");
                  System.out.println("Please try again.\n");
                  String flush = sc.next();
               } // end of catch block
            } // end of while loop
                  
            switch (choose) {
               case 1:
                  goodData = false;
                  while (!goodData) {
                     try {
                        System.out.print("Enter attendance number: ");
                        attendance = sc.nextInt();
                        goodData = true;
                        System.out.println("The total profit: " + torontoZoo.totalProfit(attendance));
                     } // end of try
                     catch (InputMismatchException e) {
                        System.out.println("You entered bad data.");
                        System.out.println("Please try again.\n");
                        String flush = sc.next();
                     } // end of catch block
                  } // end of while loop
                  break;
                  
               case 2:
                  torontoZoo.printMap();
                  break;
            } // end of choose
            choose = 0;
            goodData = false; 
                 
         } 
         else {
            System.out.println("Not a valid Entry.");
         }// end of if
      } // end of while loop
   
   } // end of main method
} // end of class