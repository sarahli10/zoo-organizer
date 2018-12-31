// Class Name: Storage
// Authors: Krassy, Ziyad
// Date: May 30, 2017
// Purpose: This class contains the information on the amount of food and medication left in storage.
//          It also contains the functions to add and subtract for the storage to keep track of how much is left

class Storage {
	protected int medicine = 0;
	protected int critters = 0;
	protected int veggies = 0;
	protected int meat = 0;

	public Storage(int m, int c, int v, int mt) {
		medicine = m;
		critters = c;
		veggies = v;
		meat = mt;
	}

	public void outputStorage() {
		System.out.println("Storage:");
		System.out.println(medicine + " Medical supplies left");
		System.out.println(critters + " Critter(s) left");
		System.out.println(veggies + " Veggie(s) left");
		System.out.println(meat + " piece(s) of meat left");
	}

	public boolean addMeds(int numMeds) {
		medicine = medicine + numMeds;
		return true;
	}

	public boolean deductMeds(int meds) {
		if (meds > medicine)
			return false;
		medicine = medicine - meds;
		return true;
	}

	public boolean addFood(String n, int j) {
		if (n.equalsIgnoreCase("Critters")) {
			critters = critters + j;
			return true;
		} else if (n.equalsIgnoreCase("Vegetables")) {
			veggies = veggies + j;
			return true;
		} else if (n.equalsIgnoreCase("Meat")) {
			meat = meat + j;
			return true;
		}
		return false;
	}

	public boolean deductFood(String n, int j) {
		if (n.equalsIgnoreCase("Critters")) {
			if (j < critters)
				return false;
			critters = critters - j;
			return true;
		} else if (n.equalsIgnoreCase("Vegetables")) {
			if (j > veggies)
				return false;
			veggies = veggies - j;
			return true;
		} else if (n.equalsIgnoreCase("Meat")) {
			if (j > meat)
				return false;
			meat = meat - j;
			return true;
		}
		return false;
	}

	public int getMedicine() {
		return medicine;
	}

	public int getCritters() {
		return critters;
	}

	public int getMeat() {
		return meat;
	}

	public int getVeggies() {
		return veggies;
	}
}