package com.sapient.runner2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Transactionprocessing {

	private List<transactionpojo> list=new ArrayList<>();

	public List<transactionpojo> getList() {
		return list;
	}

	public void setList(List<transactionpojo> list) {
		this.list = list;
	}
	public void intra_day_trans()
	{
      int []marked=new int[list.size()];
      Set<String> set=new HashSet<>();
      for(int i=0;i<list.size();i++)
    	  marked[i]=0;
      for(int i=0;i<list.size();i++)
      {
			 String key1=list.get(i).getClient_id().toLowerCase()+list.get(i).getSecurity_id().toLowerCase()+list.get(i).getTrans_type().toLowerCase()+list.get(i).getTrans_date();
             //System.out.println(key1);
			 if(list.contains(key1))
          {
        	  marked[i]=1;
			  double updatedvalue=list.get(i).getMarket_value()+10;
			  list.get(i).setMarket_value(updatedvalue);
			  continue;
          }
        	  
        	  
          
    	  if(marked[i]==0&&list.get(i).getTrans_type().toLowerCase().equals("buy"))
    	  {  boolean cond=false;
    		  for(int j=0;j<list.size();j++)
    		  {
    			  if(marked[j]==0&&list.get(i).getClient_id().toLowerCase().equals(list.get(j).getClient_id().toLowerCase())&&
    					  list.get(i).getSecurity_id().toLowerCase().equals(list.get(j).getSecurity_id().toLowerCase())&&
    					  list.get(i).getTrans_date().equals(list.get(j).getTrans_date())&&list.get(j).getTrans_type().toLowerCase().equals("sell")) {
    				  double updatedvalue=list.get(j).getMarket_value()+10;
    				  list.get(j).setMarket_value(updatedvalue);
    				  marked[j]=1;cond=true;System.out.println("hihihihihihihiih");
    			}
    			  if(cond)
    			  {marked[i]=1;
    			  double updatedvalue=list.get(i).getMarket_value()+10;
				  list.get(i).setMarket_value(updatedvalue);
    			 String key=list.get(i).getClient_id().toLowerCase()+list.get(i).getSecurity_id().toLowerCase()+list.get(i).getTrans_type().toLowerCase()+list.get(i).getTrans_date();
				  set.add(key);}
    		  }
    	  }
      }
      
	}
	
	public void normal_trans() {
		 int charge=0;
		for(int i=0;i<list.size();i++)
		{   
			if(list.get(i).getPriority_flag().toLowerCase().equals("y"))
			{
				charge=500;
				
				
			}
			else if(list.get(i).getPriority_flag().toLowerCase().equals("n")&&
					(list.get(i).getTrans_type().toLowerCase().equals("sell")||
					list.get(i).getTrans_type().toLowerCase().equals("withdraw"))) {
				charge=100;
				//System.out.println("hihihihihihihhi");
			}
			else
				charge=50;
			double updatedval=list.get(i).getMarket_value()+charge;
			list.get(i).setMarket_value(updatedval);
		}
	}
	
}
