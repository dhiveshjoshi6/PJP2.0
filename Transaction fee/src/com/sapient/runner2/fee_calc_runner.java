package com.sapient.runner2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class fee_calc_runner {
 public static void main(String[] args)
 {
	 
	 //reading the csv file
	 String delimiter=",";
	 Transactionprocessing tp=new Transactionprocessing();
	 try {
		 File file=new File("Sample_Data_Fee_Calculator.csv");
         FileReader fr=new FileReader(file);
		 BufferedReader br=new BufferedReader(fr); 
		 String line="";
		 String [] temp;
		 int cnt=1;
		 while((line=br.readLine())!=null) {
			 if(cnt==1)
			 {
				 cnt++;continue;
			 }
			 temp=line.split(delimiter);
			
			 String[] var=temp[4].split("/");
			 Date date=new Date(Integer.parseInt(var[2]),Integer.parseInt(var[0]),Integer.parseInt(var[1]));
			 
			 tp.getList().add(new transactionpojo(temp[0],temp[1],temp[2],temp[3],date,Double.parseDouble(temp[5]),temp[6]));
			 
		 }
		 }
		 catch(Exception e)
		 {
		 System.out.println(e); 
		 }
	
		 
		 
		 // Transaction processing
		 tp.intra_day_trans();
		 tp.normal_trans();
		 
		 //writing the summary report
		 System.out.println("------------------------");
		 Collections.sort(tp.getList(),new sortfun());
		 
		 
		List<transactionpojo> report=new ArrayList<>();
        if(tp.getList().size()==0)
        	System.out.println("list is empty");
        else
        {
        	report.add(tp.getList().get(0));
        	for(int i=1;i<tp.getList().size();i++)
        	{   int ind=report.size()-1;
        		if(tp.getList().get(i).getClient_id().toLowerCase().equals(report.get(ind).getClient_id().toLowerCase())&&
        		    tp.getList().get(i).getTrans_type().toLowerCase().equals(report.get(ind).getTrans_type().toLowerCase())&&
        		    tp.getList().get(i).getTrans_date().equals(report.get(ind).getTrans_date())&&
        		    tp.getList().get(i).getPriority_flag().toLowerCase().equals(report.get(ind).getPriority_flag().toLowerCase()))
        		{
        			Double data=report.get(ind).getMarket_value();
        		    data+=tp.getList().get(i).getMarket_value();
        		    report.get(ind).setMarket_value(data);
        		}
        		else
        			report.add(tp.getList().get(i));
        	}
        	for(int i=0;i<tp.getList().size();i++)
   		 {
   			 System.out.println(report.get(i));
   		 }
   		 
        	
		 BufferedWriter bw=null;
		 try {
			 File file=new File("summary_report.csv");
			 if(!file.exists())
				 file.createNewFile();
		 FileWriter fw=new FileWriter(file);
		 bw=new BufferedWriter(fw);
		 bw.write("Client Id,Transaction Type,Transaction Date,Priority,Processing Fee");
		 bw.newLine();
		 for(transactionpojo record:report)
		 {   String h=String.join("/", Integer.toString(record.getTrans_date().getMonth()),Integer.toString(record.getTrans_date().getDate()),Integer.toString(record.getTrans_date().getYear()));
		     
			 bw.write(String.join(",",record.getClient_id(),record.getTrans_type(),h,record.getPriority_flag(),Double.toString(record.getMarket_value())));
			 bw.newLine();
			 
		 }
		 bw.close();
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		
        }
        System.out.println("success");
        }
		
		 
		 
		 
	 }
 
 
	 
 

