package com.sapient.runner2;

import java.util.Comparator;

public class sortfun implements Comparator<transactionpojo> {

	@Override
	public int compare(transactionpojo p1, transactionpojo p2) {
		// TODO Auto-generated method stub
		if(p1.getClient_id().toLowerCase().equals(p2.getClient_id().toLowerCase()))
		{
			if(p1.getTrans_type().toLowerCase().equals(p2.getTrans_type().toLowerCase()))
			{
				if(p1.getTrans_date().equals(p2.getTrans_date()))
				{
					return p1.getPriority_flag().toLowerCase().compareTo(p2.getPriority_flag().toLowerCase());
					
				}
				else return p1.getTrans_date().compareTo(p2.getTrans_date());
				
			}
			else
				return p1.getTrans_type().toLowerCase().compareTo(p2.getTrans_type().toLowerCase());
			
			
		}
		return p1.getClient_id().toLowerCase().compareTo(p2.getClient_id().toLowerCase());
	}

}
