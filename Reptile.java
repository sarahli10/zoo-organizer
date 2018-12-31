// Class Name: Reptile
// Authors: Krassy
// Date: May 30, 2017
// Purpose: This class is the subclass of the Animal class, helps differentiate animals.

abstract class Reptile extends Animal {
	public Reptile(String n, int i, String h, String s, String t, char g, int a, double w, boolean health, String m, int f) {
		super(n, i, h, s, t, g, a, w, health, m, f);
	}
   public String getKingdom(){
      return "Amphibian";
   }
}