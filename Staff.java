//Class Name: Staff
//Authors: Krassy, Ziyad
//Date: May 30, 2017
//Purpose: This class contains the information of an individual staff member

public class Staff
{
	private String name;
	private int id;
	private double pay;
	private String job;
	
	public Staff(String n, int i, double p, String j)
	{
		name = n;
		id = i;
		pay = p;
		job = j;
	}

public int getID(){
      return id;
   }
   
   public String getName(){
      return name;
   }
   
   public double getPay(){
      return pay;
   }
   
   public String getJob(){
      return job;
   }
}