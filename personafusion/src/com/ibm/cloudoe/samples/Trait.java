package com.ibm.cloudoe.samples;

/*
 * Note Traits are from the Watson API
 */

public class Trait 
{
	String traitName;
	double percent;
	
	Trait(String traitName, double percent)
	{
		this.traitName = traitName;
		this.percent = percent;
	}
	
	public String toString()
	{
		return this.traitName + " , " + this.percent;
	}
}
