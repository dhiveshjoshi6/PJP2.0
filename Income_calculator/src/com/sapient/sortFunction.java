package com.sapient;


import java.util.Comparator;

public class sortFunction implements Comparator<datapojo>{
	
	@Override
	public int compare(datapojo p1, datapojo p2) {
	
		if(p1.getCountry().contentEquals(p2.getCountry())) {
			return p1.getGender().compareTo(p2.getGender());
		}
		else {
			return p1.getCountry().compareTo(p2.getCountry());
		}
		
	}
	

}
