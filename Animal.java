// Class Name: Staff
// Authors: Sarah, Krassy
// Date: May 30, 2017
// Purpose: This class contains the information of an animal.

abstract class Animal {
	protected String name;
	protected int id;
	protected String habitat;
	protected String species;
	protected String type;
	protected char gender;
	protected int age;
	protected double weight;
	protected boolean healthy;
	protected String mealPlan;
	protected int foodQuantity;

	public Animal(String n, int i, String h, String s, String t, char g, int a, double w, boolean health, String m, int f) {
		name = n;
		id = i;
		habitat = h;
		species = s;
		type = t;
		gender = g;
		age = a;
		weight = w;
		healthy = health;
		mealPlan = m;
		foodQuantity = f;
	}

	public void setName(String n) {
		name = n;
	}

	public void setId(int i) {
		id = i;
	}

	public void setHabitat(String h) {
		habitat = h;
	}

	public void setSpecies(String s) {
		species = s;
	}

	public void setType(String t) {
		type = t;
	}

	public void setGender(char g) {
		gender = g;
	}

	public void setAge(int a) {
		age = a;
	}

	public void setWeight(double w) {
		weight = w;
	}

	public void setHealthy(boolean health) {
		healthy = health;
	}

	public void setMealPlan(String m) {
		mealPlan = m;
	}

	public void setFoodQuantity(int f) {
		foodQuantity = f;
	}

	public String getName() {
		return name;
	}

	public int getID() {
		return id;
	}

	public String getHabitat() {
		return habitat;
	}

	public String getSpecies() {
		return species;
	}

	public String getType() {
		return type;
	}

	public char getGender() {
		return gender;
	}

	public int getAge() {
		return age;
	}

	public double getWeight() {
		return weight;
	}

	public boolean getHealthy() {
		return healthy;
	}

	public String getMealPlan() {
		return mealPlan;
	}

	public int getFoodQuantity() {
		return foodQuantity;
	}

	public boolean isSick() {
		return !healthy;
	}

}