// Class Name: Zoo
// Authors: Ying, Sarah, Krassy, Ziyad, Jacob
// Date: June 3, 2017
// Purpose: All actions related to organizing the Zoo, such as staff, animal, and the storage class.

import java.lang.String;
import java.io.*;
import java.util.*;

public class Zoo {
   private static final int maxAttendance = 1000;
   private static final double customerPrice = 39.99;
   private static final int numEncl = 30;
   private static final int maxAnimalInTotal = 105;
   private static final int maxStaff = 15;
   private static final int numMammal = 6;
   private static final int numRep = 4;
   private static final int numAmphib = 2;
   private static final int numBird = 5;
   private static final int numMarine = 4;
   private Storage strge;
   Animal[] aList = new Animal[maxAnimalInTotal];
   Staff[] staffList = new Staff[maxStaff];
   public int numAnimals;
   private int numStaff;

	// Author: Ying and Sarah
	// Description: staffFile - the name of the staff text file, sFile - the
	// name of the storage text file, animalFile - the name of the animal text
	// file; return type: Zoo
	// Purpose: This contsructor creates a Zoo object
   public Zoo(String staffFile, String sFile, String animalFile) {
      numAnimals = 0;
      numStaff = 0;
      loadStaffFromFile();
      loadStorageFromFile();
      loadAnimalFromFile();
      sortAnimalsByID();
   	
   }

	// Author: Sarah
	// Description: numAttendance - the number of people who visited the zoo on
	// a certain day; return value: double
	// Purpose: This method calculates and returns the total profit of the day
	// based on the ticket price and attendance
   public double totalProfit(int numAttendance) {
      return customerPrice * numAttendance;
   }

	// ----STORAGE FUNCTIONS----
	
   public void outputTheStorage(){
      strge.outputStorage();
   }
	// Author: Ziyad and Krassy
	// Description: file - file name of the storage text file;return value: void
	// Purpose: Loads the storage information from the storage file and creates
	// a storage object
   public void loadStorageFromFile() {
      try {
         BufferedReader in = new BufferedReader(new FileReader("storage.txt"));
         int totStorage = Integer.parseInt(in.readLine());
         int meds = Integer.parseInt(in.readLine());
         int critters = Integer.parseInt(in.readLine());
         int vegetables = Integer.parseInt(in.readLine());
         int meat = Integer.parseInt(in.readLine());
         strge = new Storage(meds, critters, vegetables, meat);
         in.close();
      } 
      catch (IOException e) {
         System.out.println("Problem reading file");
      }
   }

	// Author: Ziyad and Krassy
	// Description: n - "meat", "vegetables", or "critters";return value: void
	// Purpose: Calls the addFood method in the Storage class to add food to a
	// specific food type
   public boolean restockFood(String n, int amt) {
      strge.addFood(n, amt);
      saveStorage();
      return true;
   }

	// Author: Ziyad and Krassy
	// Description: n - "meat", "vegetables", or "critters";return value: void
	// Purpose: Calls the deductFood method in the Storage class to substract a
	// specific amount from a food type.
	// The difference between this method and the feedAnimal method is in this
	// case, the food may be rotten and is thrown out
   public boolean deductFood(String n, int amt) {
      boolean success = strge.deductFood(n, amt);
      if (success == true){
         saveStorage();
      }
      return success;
   }

	// Author: Ziyad and Krassy
	// Description: amt - amount of medication;return value: void
	// Purpose: Calls the addMeds method in the Storage class to add the amount
	// of medicide to add
   public boolean restockMeds(int amt) {
      strge.addMeds(amt);
      saveStorage();
      return true;
   }

	// Author: Ziyad and Krassy
	// Description: amt - amount of medication;return value: void
	// Purpose: Calls the deductMeds method in the Storage class to substract
	// the amount of medicide from the total
   public boolean deductMeds(int amt) {
      boolean success = strge.deductMeds(amt);
      if (success == true){
         saveStorage();
      }
      return success;
   }

	// -----------------------

	// ----STAFF FUNCTIONS----

	// Author: Krassy and Ziyad
	// Description: file - the name of the staff text file; return type: void
	// Purpose: This method loads the staff information from the staff file and
	// inserts it into the staff array
   public void loadStaffFromFile() {
      try {
         BufferedReader in = new BufferedReader(new FileReader("staff.txt"));
         int totStaff = Integer.parseInt(in.readLine());
         String name;
         int id;
         double pay;
         String job;
      
         for (int i = 0; i < totStaff; i++) {
            name = in.readLine();
            id = Integer.parseInt(in.readLine());
            pay = Double.parseDouble(in.readLine());
            job = in.readLine();
            addStaff(name, id, pay, job);
         }
         in.close();
      } 
      catch (IOException e) {
         System.out.println("Problem reading file");
      }
   }
   
   public boolean validStaffID(int identification){
      for(int i = 0; i < numStaff; i++){
         if(identification == staffList[i].getID()){
            return false;
         }
      }
      return true;
   }
	// Author: Sarah
	// Description: name - the name of the new staff; id - the id of the new
	// staff; return value: String
	// Purpose: Creates a new staff and inserts it into the array of staff
   public boolean addStaff(String name, int id, double pay, String job) {
      int index = -1;
      boolean empty = false;
      for (int i = 0; i < staffList.length && !empty; i++) {
         if (staffList[i] == null) {
            empty = true;
            index = i;
         }
      }
      if (index != -1) {
         staffList[index] = new Staff(name, id, pay, job);
         numStaff++;
         saveStaffList();
         return true;
      }
      return false;
   }

	// Author: Sarah
	// Description: id - the identification number of the employee; return type:
	// boolean
	// Purpose: This method removes the staff member based on their id.
   public boolean removeStaff(int id) {
      for (int i = 0; i < numStaff; i++) {
         if (id == staffList[i].getID()) {
            staffList[i] = null;
            numStaff--;
            saveStaffList();
            return true;
         }
      }
      return false;
   }

	// Author: Sarah, Krassy and Ziyad
	// Description: no parameters; return type: String
	// Purpose: This method lists all the staff members of the zoo.
   public void listAllStaff() {
      for (int i = 0; i < numStaff; i++){
         System.out.println("Name: " + staffList[i].getName());
         System.out.println("ID: " + staffList[i].getID());
         System.out.println("Pay: $" + staffList[i].getPay());
         System.out.println("Job: " + staffList[i].getJob());
         System.out.println();
      }
   }
	// ------------------------

	// ----ANIMAL FUNCTIONS----

	// Author: Sarah
	// Description: start - the index of the array that the program starts
	// looking through; end - the index of the array where the program stops
	// looking through;return value: String
	// Purpose: returns a String value of the list of animals that are sick
   public void displayAnimalInfo(int num) {
      int index = -1;
      for (int i = 0; i < numAnimals; i++) {
         if ((aList[i].getID()) == num){
            index = i;
         }
      }
   
      if (index != -1) {
         System.out.println("Name: " + aList[index].name);
         System.out.println("id: " + aList[index].id);
         System.out.println("habitat: " + aList[index].habitat);
         System.out.println("species: " + aList[index].species);
         System.out.println("type: " + aList[index].type);
         System.out.println("gender: " + aList[index].gender);
         System.out.println("age: " + aList[index].age);
         System.out.println("Weight: " + aList[index].weight + "kg");
         System.out.println("Healthy: " + aList[index].healthy);
         System.out.println("Meal Plan: " + aList[index].mealPlan);
         System.out.println("Food Quantity: " + aList[index].foodQuantity);
      } 
      else
         System.out.println("Could not find animal. Please check that the correct ID has been entered.");
   }

	// Author: Sarah
	// Description: start - the index of the array that the program starts
	// looking through;
	// end - the index of the array where the program stops looking
	// through;return value: String
	// Purpose: Returns a String value of the list of animals that are sick
   public String returnSickAnimals(int start, int end) {
      String str = "";
      if (start == end){
         return "";
      } 
      else if (aList[start].isSick()) {
         return str += String.valueOf("Animal ID[" + aList[start].getID()) + "] is sick\n" + returnSickAnimals(start + 1, end);
      } 
      else {
         return str += returnSickAnimals(start + 1, end);
      }
   }
   
   public boolean validAnimalID(int identification){
      for(int i = 0; i < numAnimals; i++){
         if(identification == aList[i].getID()){
            return false;
         }
      }
      return true;
   }
   
   public boolean validAnimalType(String tape){
      if(tape.equalsIgnoreCase("Frog")){
         return true;
      } else if(tape.equalsIgnoreCase("Salamander")){
         return true;
      } else if(tape.equalsIgnoreCase("african herbivores")){
         return true;
      } else if(tape.equalsIgnoreCase("american herbivores")){
         return true;
      } else if(tape.equalsIgnoreCase("bear")){
         return true;
      } else if(tape.equalsIgnoreCase("feline")){
         return true;
      } else if(tape.equalsIgnoreCase("primate")){
         return true;
      } else if(tape.equalsIgnoreCase("rodent")){
         return true;
      } else if(tape.equalsIgnoreCase("crocodile")){
         return true;
      } else if(tape.equalsIgnoreCase("lizard")){
         return true;
      } else if(tape.equalsIgnoreCase("snake")){
         return true;
      } else if(tape.equalsIgnoreCase("turtle")){
         return true;
      } else if(tape.equalsIgnoreCase("eagle")){
         return true;
      } else if(tape.equalsIgnoreCase("falcon")){
         return true;
      } else if(tape.equalsIgnoreCase("flightless")){
         return true;
      } else if(tape.equalsIgnoreCase("hawk")){
         return true;
      } else if(tape.equalsIgnoreCase("smallbird")){
         return true;
      } else if(tape.equalsIgnoreCase("fish")){
         return true;
      } else if(tape.equalsIgnoreCase("mollusk")){
         return true;
      } else if(tape.equalsIgnoreCase("shark")){
         return true;
      } else if(tape.equalsIgnoreCase("shellfish")){
         return true;
      } else {
      return false;
      }
   }
   
	// Author: Krassy, Ziyad
	// Description: a - the new Animal that going to be added; type - the type
	// of food being added
	// Purpose: This method adds the new animal to the array
   public boolean addAnimal(Animal a) {
      int index = -1;
      boolean empty = false;
      for (int i = 0; i < aList.length-1 && !empty; i++) {
         if (aList[i] == null) {
            index = i;
            empty = true;
            //System.out.println("Found empty spot.");
         }
      }
      if (index != -1) {
         aList[index] = a;
         numAnimals++;
         saveAnimalList();
         return true;
      }
      return false;
   
   }
   

	// Author: Ying
	// Description: species - the species of the animal that is going to be
	// added; name - the name of the animal; id - the id of the animal; habitat
	// - the habitat of the animal; gender - whether the animal is male or
	// female; age - the age of the animal; weight - the weight of the animal in
	// kilograms; health - whether the animal is healthy or not (true = healthy,
	// false = sick); mealPlan - what the animal eats (meat, veg, critters);
	// foodQuantity - the amount of food the animal eats;return value: boolean
	// Purpose: Determines which type of marine animal to add and creates the
	// object and inserts it into the the array.
   public boolean addMarine(String species, String type, String name, int id,
   		String habitat, char gender, int age, double weight,
   		boolean health, String mealPlan, int foodQuantity) {
      boolean successful = false;
      if (type.equalsIgnoreCase("fish")) {
         Fish f = new Fish(name, id, habitat, species, type, gender,
            	age, weight, health, mealPlan, foodQuantity);
         successful = addAnimal(f);
         if(successful){
            sortAnimalsByID();
         }
         return successful;
      } 
      else if (type.equalsIgnoreCase("mollusk")) {
         Mollusk m = new Mollusk(name, id, habitat, species, type,
            	gender, age, weight, health, mealPlan, foodQuantity);
         successful = addAnimal(m);
         if(successful){
            sortAnimalsByID();
         }
         return successful;
      } 
      else if (type.equalsIgnoreCase("shark")) {
         Shark s = new Shark(name, id, habitat, species, type, gender,
            	age, weight, health, mealPlan, foodQuantity);
         successful = addAnimal(s);
         if(successful){
            sortAnimalsByID();
         }
         return successful;
      } 
      else if (type.equalsIgnoreCase("shellfish")) {
         Shellfish s = new Shellfish(name, id, habitat, species, type,
            	gender, age, weight, health, mealPlan, foodQuantity);
         successful = addAnimal(s);
         if(successful){
            sortAnimalsByID();
         }
         return successful;
      }
      return successful;
   
   }

	// Author: Sarah
	// Description: species - the species of the animal that is going to be
	// added; name - the name of the animal; id - the id of the animal; habitat
	// - the habitat of the animal; gender - whether the animal is male or
	// female; age - the age of the animal; weight - the weight of the animal in
	// kilograms; health - whether the animal is healthy or not (true = healthy,
	// false = sick); mealPlan - what the animal eats (meat, veg, critters);
	// foodQuantity - the amount of food the animal eats;return value: boolean
	// Purpose: Determines which type of bird to add and creates the object and
	// inserts it into the the array.
   public boolean addBird(String species, String type, String name, int id, String habitat,
   		char gender, int age, double weight, boolean health,
   		String mealPlan, int foodQuantity) {
      boolean successful = false;
      if (type.equalsIgnoreCase("eagle")) {
         Eagle e = new Eagle(name, id, habitat, species, type, gender,
            	age, weight, health, mealPlan, foodQuantity);
         successful = addAnimal(e);
         if(successful){
            sortAnimalsByID();
         }
         return successful;
      } 
      else if (type.equalsIgnoreCase("falcon")) {
         Falcon f = new Falcon(name, id, habitat, species, type, gender,
            	age, weight, health, mealPlan, foodQuantity);
         successful = addAnimal(f);
         if(successful){
            sortAnimalsByID();
         }
         return successful;
      } 
      else if (type.equalsIgnoreCase("hawk")) {
         Hawk h = new Hawk(name, id, habitat, species, type, gender,
            	age, weight, health, mealPlan, foodQuantity);
         successful = addAnimal(h);
         if(successful){
            sortAnimalsByID();
         }
         return successful;
      } 
      else if (type.equalsIgnoreCase("flightless")) {
         Flightless n = new Flightless(name, id, habitat, species, type, gender,
            	age, weight, health, mealPlan, foodQuantity);
         successful = addAnimal(n);
         if(successful){
            sortAnimalsByID();
         }
         return successful;
      } 
      else if (type.equalsIgnoreCase("smallbird")) {
         SmallBird s = new SmallBird(name, id, habitat, species, type, gender,
            	age, weight, health, mealPlan, foodQuantity);
         successful = addAnimal(s);
         if(successful){
            sortAnimalsByID();
         }
         return successful;
      }
      return successful;
   }

	// Author: Ziyad
	// Description: species - the species of the animal that is going to be
	// added; name - the name of the animal; id - the id of the animal; habitat
	// - the habitat of the animal; gender - whether the animal is male or
	// female; age - the age of the animal; weight - the weight of the animal in
	// kilograms; health - whether the animal is healthy or not (true = healthy,
	// false = sick); mealPlan - what the animal eats (meat, veg, critters);
	// foodQuantity - the amount of food the animal eats;return value: boolean
	// Purpose: Determines which type of reptile to add and creates the object
	// and inserts it into the the array.
   public boolean addReptile(String species, String type, String name, int id,
   		String habitat, char gender, int age, double weight,
   		boolean health, String mealPlan, int foodQuantity) {
      boolean successful = false;
   	//System.out.println(type);
   	//System.out.println(type.equalsIgnoreCase("turtle"));
      if (type.equalsIgnoreCase("lizard")) {
         Lizard l = new Lizard(name, id, habitat, species, type, gender,
            	age, weight, health, mealPlan, foodQuantity);
         successful = addAnimal(l);
         if(successful){
            sortAnimalsByID();
         }
         return successful;
      } 
      else if (type.equalsIgnoreCase("crocodile")) {
         Crocodile c = new Crocodile(name, id, habitat, species, type, gender,
            	age, weight, health, mealPlan, foodQuantity);
         successful = addAnimal(c);
         if(successful){
            sortAnimalsByID();
         }
         return successful;
      } 
      else if (type.equalsIgnoreCase("turtle")) {
      	//System.out.println("add the turtle");
         Turtle t = new Turtle(name, id, habitat, species, type, gender,
            	age, weight, health, mealPlan, foodQuantity);
      	//System.out.println(t.getName());
         successful = addAnimal(t);
         if(successful){
            sortAnimalsByID();
         }
         return successful;
      }
      else if (type.equalsIgnoreCase("snake")) {
         Snake s = new Snake(name, id, habitat, species, type, gender,
            	age, weight, health, mealPlan, foodQuantity);
         successful = addAnimal(s);
         if(successful){
            sortAnimalsByID();
         }
         return successful;
      }
      return false;
   }

	// Author: Ying
	// Description: species - the species of the animal that is going to be
	// added; name - the name of the animal; id - the id of the animal; habitat
	// - the habitat of the animal; gender - whether the animal is male or
	// female; age - the age of the animal; weight - the weight of the animal in
	// kilograms; health - whether the animal is healthy or not (true = healthy,
	// false = sick); mealPlan - what the animal eats (meat, veg, critters);
	// foodQuantity - the amount of food the animal eats;return value: boolean
	// Purpose: Determines which type of amphibian to add and creates the object
	// and inserts it into the the array.
   public boolean addAmphib(String species, String type, String name, int id,
   		String habitat, char gender, int age, double weight,
   		boolean health, String mealPlan, int foodQuantity) {
      boolean successful = false;
      if (type.equalsIgnoreCase("frog")) {
         Frog f = new Frog(name, id, habitat, species, type, gender,
            	age, weight, health, mealPlan, foodQuantity);
         successful = addAnimal(f);
         if(successful){
            sortAnimalsByID();
         }
         return successful;
      } 
      else if (type.equalsIgnoreCase("salamander")) {
         Salamander s = new Salamander(name, id, habitat, species,
            	type, gender, age, weight, health, mealPlan,
            	foodQuantity);
         successful = addAnimal(s);
         if(successful){
            sortAnimalsByID();
         }
         return successful;
      }
      return false;
   
   }

	// Author: Sarah
	// Description: species - the species of the animal that is going to be
	// added; name - the name of the animal; id - the id of the animal; habitat
	// - the habitat of the animal; gender - whether the animal is male or
	// female; age - the age of the animal; weight - the weight of the animal in
	// kilograms; health - whether the animal is healthy or not (true = healthy,
	// false = sick); mealPlan - what the animal eats (meat, veg, critters);
	// foodQuantity - the amount of food the animal eats;return value: boolean
	// Purpose: Determines which type of mammal to add and creates the object
	// and inserts it into the the array.
   public boolean addMammal(String species, String type, String name, int id,
   		String habitat, char gender, int age, double weight,
   		boolean health, String mealPlan, int foodQuantity) { // Sarah
      boolean successful = false;
      if (type.equalsIgnoreCase("american herbivores")) {
         AmericanHerbivore a = new AmericanHerbivore(name, id, habitat,
            	species, "Mammal", gender, age, weight, health, mealPlan,
            	foodQuantity);
         successful = addAnimal(a);
         if(successful){
            sortAnimalsByID();
         }
         return successful;
      } 
      else if (type.equalsIgnoreCase("african herbivores")) {
         AfricanHerbivore a = new AfricanHerbivore(name, id, habitat,
            	species, "Mammal", gender, age, weight, health, mealPlan,
            	foodQuantity);
         successful = addAnimal(a);
         if(successful){
            sortAnimalsByID();
         }
         return successful;
      } 
      else if (type.equalsIgnoreCase("bear")) {
         Bear b = new Bear(name, id, habitat, species, "Mammal", gender,
            	age, weight, health, mealPlan, foodQuantity);
         successful = addAnimal(b);
         if(successful){
            sortAnimalsByID();
         }
         return successful;
      } 
      else if (type.equalsIgnoreCase("feline")) {
         Feline f = new Feline(name, id, habitat, species, "Mammal", gender,
            	age, weight, health, mealPlan, foodQuantity);
         successful = addAnimal(f);
         if(successful){
            sortAnimalsByID();
         }
         return successful;
      } 
      else if (type.equalsIgnoreCase("primate")) {
         Primate p = new Primate(name, id, habitat, species, "Mammal",
            	gender, age, weight, health, mealPlan, foodQuantity);
         successful = addAnimal(p);
         if(successful){
            sortAnimalsByID();
         }
         return successful;
      } 
      else if (type.equalsIgnoreCase("rodent")) {
         Rodent r = new Rodent(name, id, habitat, species, "Mammal", gender,
            	age, weight, health, mealPlan, foodQuantity);
         successful = addAnimal(r);
         if(successful){
            sortAnimalsByID();
         }
         return successful;
      }
      return successful;
   }

	// Author: Sarah
	// Description: id - the id number inputted by the user;return value:
	// boolean
	// Purpose: To remove an animal for the array list of animals by finding its
	// id
   public boolean removeAnimal(int id) {
      for (int i = 0; i < aList.length; i++) {
         if (id == aList[i].getID()) {
            aList[i] = null;
            numAnimals--;
            saveAnimalList();
            return true;
         }
      }
      return false;
   }

	// Author: Sarah
	// Description: No parameters;return value: void
	// Purpose: To sort the animals in the zoo by their species by comparing
	// their species individually.
   public void sortAnimalsByID() {
   //System.out.println("numAnimal" +numAnimals);
      if(numAnimals != 1 && numAnimals != 0){
         for (int upperbound = numAnimals-1; upperbound > 0; upperbound--) {
            int maxIndex = 0;
            for (int j = 0; j <= upperbound; j++) {
               if (aList[maxIndex].getID() < aList[j].getID()) {
                  maxIndex = j;
               }
               Animal temp = aList[maxIndex];
               aList[maxIndex] = aList[upperbound];
               aList[upperbound] = temp;
            }
         }
      }
   }

	// Author: Sarah
	// Description: type - the type of animal the user inputs;return value: int
	// Purpose: returns the number of animals that belong to the type
   public int findNumByType(String type) {
      int count = 0;
      type.toLowerCase();
   
      for (int i = 0; i < numAnimals; i++)
         if (type.equalsIgnoreCase(aList[i].getType()))
            count++;
      return count;
   }

	// Author: Sarah
	// Description: hab - the habitat the user inputs;return value: int
	// Purpose: returns the number of animals that live in the habitat
   public int findNumByHabitat(String hab) {
      int count = 0;
      hab.toLowerCase();
      for (int i = 0; i < numAnimals; i++)
         if (hab.equalsIgnoreCase(aList[i].getHabitat()))
            count++;
      return count;
   }

	// Author: Sarah
	// Description: No parameters;return value: void
	// Purpose: Prints all animals upon user request
   public void listAllAnimals() { 
      System.out.println(aList[0].getName());
      for (int i = 0; i < numAnimals; i++) {
         System.out.printf("Name: %s\n", aList[i].getName());
         System.out.printf("id: %d\n", aList[i].getID());
      	/*System.out.printf("Habitat: %s\n", aList[i].getHabitat());
      	System.out.printf("Type: %s\n", aList[i].getType());
      	System.out.printf("Species: %s\n", aList[i].getSpecies());
         System.out.printf("Gender: %c\n", aList[i].getGender());
      	System.out.printf("Age: %d\n", aList[i].getAge());
      	System.out.printf("Weight: %2f\n", aList[i].getWeight());
      	if (aList[i].getHealthy())
      		System.out.println("Health Status: healthy");
      	else
      		System.out.println("Health Status: sick");
      	System.out.printf("Meal Plan: %s\n", aList[i].getMealPlan());
      	System.out.printf("Amount of food consumed: %d\n",
      			aList[i].getFoodQuantity());
         */
      }
   }

	// Author: Ying
	// Description: No parameters; return value: void
	// Purpose: This method lists all the reptiles in the zoo. It outputs the
	// name and id of the reptile.
   public void listAllReptile() { // Ying
      for (int i = 0; i < aList.length; i++) {
         if (aList[i] instanceof Reptile) {
            System.out.printf("Name of Reptile: %s\n", aList[i].getName());
            System.out.printf("id: %d\n", aList[i].id);
         }
      }
   }

	// Author: Sarah
	// Description: No parameters; return value: void
	// Purpose: This method lists all the marine animals in the zoo. It outputs
	// the name and id of the marine animal.
   public void listAllMarine() {
      for (int i = 0; i < aList.length; i++) {
         if (aList[i] instanceof Marine) {
            System.out.printf("Name of Marine animal: %s\n",
               	aList[i].getName());
            System.out.printf("id: %d\n", aList[i].id);
         }
      }
   }

	// Author: Ziyad and Sarah
	// Description: No parameters; return value: void
	// Purpose: This method lists all the mammals in the zoo. It outputs the
	// name and id of the mammal.
   public void listAllMammal() {
      for (int i = 0; i < aList.length; i++) {
         if (aList[i] instanceof Mammal) {
            System.out.printf("Name of Mammal: %s\n", aList[i].getName());
            System.out.printf("id: %d\n", aList[i].id);
         }
      }
   }

	// Author: Sarah
	// Description: No parameters; return value: void
	// Purpose: This method lists all the birds in the zoo. It outputs the name
	// and id of the bird.
   public void listAllBird() {
      for (int i = 0; i < aList.length; i++) {
         if (aList[i] instanceof Bird) {
            System.out.printf("Name of Bird: %s\n", aList[i].getName());
            System.out.printf("id: %d\n", aList[i].id);
         }
      }
   }

	// Author: Ying
	// Description: No parameters; return value: void
	// Purpose: This method lists all the amphibians in the zoo. It outputs the
	// name and id of the amphibian.
   public void listAllAmphib() {
      for (int i = 0; i < aList.length; i++) {
         if (aList[i] instanceof Amphibian) {
            System.out
               	.printf("Name of Amphibian: %s\n", aList[i].getName());
            System.out.printf("id: %d\n", aList[i].id);
         }
      }
   }

	// Author: Krassy
	// Description: id - The identification number of the animal; return value:
	// int
	// Purpose: This method searches for an animal based on its id and returns
	// the index of the animal with that id in the Animal ArrayList
   private int searchAnimalByid(int id) {
      int index = -1;
      for (int i = 0; i < numAnimals; i++) {
         if (aList[i].getID() == id)
            index = i;
      }
      return index;
   }

	// Author: Krassy
	// Description: health - whether the animal is healthy or not (true =
	// healthy, false = sick);return value: void
	// Purpose: This method updates the animal health

   public void updateAnimalHealth(int id, boolean health) {
      int i = searchAnimalByid(id);
      aList[i].setHealthy(health);
      saveAnimalList();
   }

	// -------------------------------------

	// ----FILE INPUT/OUTPUT---
	// Author: Ziyad and Krassy
	// Description: file - the name of the animal text file to be imported into
	// the program; return value: void
	// Purpose: To import the list of animals for use by the user
   public void loadAnimalFromFile() {
      try {
         BufferedReader in = new BufferedReader(new FileReader("animals.txt"));
         int totAnimals = Integer.parseInt(in.readLine());
         String name;
         int id;
         char gender;
         String species;
         int age;
         String kingdom;
         String type;
         String habitat;
         double weight;
         String mealPlan;
         int foodQuantity;
         boolean healthy;
         boolean successful = false;
         for (int i = 0; i < totAnimals; i++) {
            name = in.readLine();
            id = Integer.parseInt(in.readLine());
            habitat = in.readLine();
            kingdom = in.readLine();
            species = in.readLine();
            type = in.readLine();
            gender = in.readLine().charAt(0);
            age = Integer.parseInt(in.readLine());
            weight = Double.parseDouble(in.readLine());
            healthy = Boolean.parseBoolean(in.readLine());
            mealPlan = in.readLine();
            foodQuantity = Integer.parseInt(in.readLine());
            if (kingdom.equalsIgnoreCase("marine")){
               successful = addMarine(species, type, name, id, habitat, gender, age, weight, healthy, mealPlan, foodQuantity);
            }
            else if (kingdom.equalsIgnoreCase("bird")){
               successful = addBird(species, type, name, id, habitat, gender, age, weight, healthy, mealPlan, foodQuantity);
            }
            else if (kingdom.equalsIgnoreCase("mammal")){
               successful = addMammal(species, type, name, id, habitat, gender, age, weight, healthy, mealPlan, foodQuantity);
            }
            else if (kingdom.equalsIgnoreCase("reptile")){
               successful = addReptile(species, type, name, id, habitat, gender, age, weight, healthy, mealPlan, foodQuantity);
            }
            else if (kingdom.equalsIgnoreCase("amphibian")){
               successful = addAmphib(species, type, name, id, habitat, gender, age, weight, healthy, mealPlan, foodQuantity);
            }
            if(!successful){
               System.out.println("Maximum capacity has been reached. Cannot add animal with name: " + name);
            }
         	
         }
         in.close();
      }
      catch (IOException e) {
         System.out.println(e.getMessage());
         System.out.println("Problem reading file");
      }
   
   }

	// Author: Ying
	// Description: mapFile - the map of the zoo in String format(not 100% sure)
	// in, readFile, line - basic file reader stuff; return value: void
	// Purpose: This method outputs the map of the zoo
   public void printMap() {
      File mapFile = new File("map.txt");
      FileReader in;
      BufferedReader readFile;
      String line;
   
      try {
         in = new FileReader(mapFile);
         readFile = new BufferedReader(in);
         while ((line = readFile.readLine()) != null) {
            System.out.println(line);
         }
         readFile.close();
         in.close();
      } 
      catch (FileNotFoundException e) {
         System.out.println("File does not exist or could not be found.");
         System.err.println("FileNotFoundException: " + e.getMessage());
      } 
      catch (IOException e) {
         System.out.println("Problem reading file.");
         System.out.println("IOException: " + e.getMessage());
      }
   }
	
	// Author: Ziyad, Krassy
	// Description: no parameters; return value: void
	// Purpose: This method saves the list of staff into a file
   public void saveStaffList() {
      try {
         BufferedWriter out = new BufferedWriter(new FileWriter("staff.txt", false));
         out.write(String.valueOf(numStaff));
         out.newLine();
         for (int i = 0; i < numStaff; i++) {
            out.write(staffList[i].getName());
            out.newLine();
            out.write(String.valueOf(staffList[i].getID()));
            out.newLine();
            out.write(String.valueOf(staffList[i].getPay()));
            out.newLine();
            out.write(staffList[i].getJob());
            out.newLine();
         }
         out.close();
      } 
      catch (IOException e) {
         System.out.println("Problem reading file");
      }
   
   }
	
	// Author: Krassy, Ziyad
	// Description: no parameters; return value: void
	// Purpose: This method saves the items in the storage into a file
   public void saveStorage() {
      try {
         BufferedWriter out = new BufferedWriter(new FileWriter("storage.txt", false));
         out.write(String.valueOf(strge.getMedicine() + strge.getCritters() + strge.getMeat() + strge.getVeggies()));
         out.newLine();
         out.write(String.valueOf(strge.getMedicine()));
         out.newLine();
         out.write(String.valueOf(strge.getCritters()));
         out.newLine();
         out.write(String.valueOf(strge.getVeggies()));
         out.newLine();
         out.write(String.valueOf(strge.getMeat()));
         out.close();
      } 
      catch (IOException e) {
         System.out.println("Problem reading file");
      }
   
   }
	
	// Author: Krassy, Ziyad
	// Description: no parameters; return value: void
	// Purpose: This method saves the list of animals in the Zoo into a file
   public void saveAnimalList() {
      try {
         BufferedWriter out = new BufferedWriter(new FileWriter("animals.txt", false));
         out.write(String.valueOf(numAnimals));
         out.newLine();
         for (int i = 0; i < numAnimals; i++) {
            out.write(String.valueOf(aList[i].getName()));
            out.newLine();
            out.write(String.valueOf(aList[i].getID()));
            out.newLine();
            out.write(String.valueOf(aList[i].getHabitat()));
            out.newLine();
            out.write(String.valueOf(getKingdom(aList[i])));
            out.newLine();
            out.write(String.valueOf(aList[i].getSpecies()));
            out.newLine();
            out.write(String.valueOf(aList[i].getType()));
            out.newLine();
            out.write(String.valueOf(aList[i].getGender()));
            out.newLine();
            out.write(String.valueOf(aList[i].getAge()));
            out.newLine();
            out.write(String.valueOf(aList[i].getWeight()));
            out.newLine();
            out.write(String.valueOf(aList[i].getHealthy()));
            out.newLine();
            out.write(String.valueOf(aList[i].getMealPlan()));
            out.newLine();
            out.write(String.valueOf(aList[i].getFoodQuantity()));
            out.newLine();
         }
         out.close();
      } 
      catch (IOException e) {
         System.out.println("Problem reading file");
      }
   }
   
   public String getKingdom(Animal a){
      if (a instanceof Reptile){
         return "Reptile";
      } 
      else if (a instanceof Marine){
         return "Marine";
      } 
      else if (a instanceof Bird){
         return "Bird";
      } 
      else if (a instanceof Mammal){
         return "Mammal";
      } 
      else if (a instanceof Amphibian){
         return "Amphibian";
      } 
      else {
         return "";
      }
   }
	// ------------------------------------

	// ----ACCESSORS----
	// Author: Sarah
	// Description: return value: int
	// Purpose: This information is returned to the ZooRunner upon user request
   public int getMaxAttendance() {
      return maxAttendance;
   }
   public int getnumAnimals() {
      return numAnimals;
   }
	// Author: Sarah
	// Description: return value: double
	// Purpose: This information is returned to the ZooRunner upon user request

   public double getCustomerPrice() {
      return customerPrice;
   }

   public int getAListLength() {
      return aList.length;
   }
	// -----------------
}
