// Class Name: Marine
// Authors: Krassy
// Date: May 30, 2017
// Purpose: This class is the subclass of the Animal class, helps differentiate animals.

abstract class Marine extends Animal {
	public Marine(String n, int i, String h, String s, String t, char g, int a, double w, boolean health, String m, int f) {
		super(n, i, h, s, t, g, a, w, health, m, f);

	}
   
   public String getKingdom(){
      return "Amphibian";
   }
}