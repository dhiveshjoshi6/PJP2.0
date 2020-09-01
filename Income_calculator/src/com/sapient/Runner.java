package com.sapient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Runner {
	
	public static void main(String[] args) {
		
		String delimeter = "," ;//to read CSV file
		incomedao dao = new incomedao();
		try {
			File file = new File("Sample input file Assignment 3.csv");
			FileReader fRead = new FileReader(file);
			BufferedReader bRead = new BufferedReader(fRead);
			String line ="";
			int flag=1;
			String[] tmp;
			while((line=bRead.readLine()) != null) {
				if(flag==1)
				{
					flag++; //to skip the heading
					continue;
				}
				tmp =  line.split(delimeter);
				String currency = tmp[3];
				//System.out.println(tmp[0] + tmp[1] + tmp[2] + tmp[3] + tmp[4]+"/n");
				double avgIncome = Double.parseDouble(tmp[4]);
				if(currency == "INR") {
					avgIncome/=66;
				}
				else if(currency == "SGP") {
					avgIncome/=1.5;
				}
				else if(currency == "HKD") {
					avgIncome/=8;
				}
				else if(currency == "GBP") {
					avgIncome/=0.67;
				}
				
				dao.getList().add(new datapojo(tmp[0],tmp[1],tmp[2],tmp[3],avgIncome));
			}
			
		}catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		
		Collections.sort(dao.getList(), new sortFunction());
		List<datapojo> report = new ArrayList<>();
		if(dao.getList().size()==0) {
			System.out.println("Contains no data");
		}
		else {
			report.add(dao.getList().get(0));
			for(int i=1 ; i<dao.getList().size() ; i++) {
				int end = report.size()-1;
				if(dao.getList().get(i).getCountry().equals(report.get(end).getCountry()) && dao.getList().get(i).getGender().equals(report.get(end).getGender())) {
					Double sum = dao.getList().get(i).getAvgIncome();
					sum += report.get(end).getAvgIncome();
					report.get(end).setAvgIncome(sum);
				}
				else {
					report.add(dao.getList().get(i));
				}	
			}
			
			BufferedWriter BWrite = null;
			try {
				File file = new File("Output Report.csv");
				if(!file.exists()) {
					file.createNewFile();
				}
				FileWriter FWrite = new FileWriter(file);
				BWrite = new BufferedWriter(FWrite);
				
				BWrite.write("City,Country,Gender,Avg Income");
				BWrite.newLine();
				for(datapojo data : report) {
					BWrite.write(data.getCity()+delimeter+data.getCountry()+delimeter+data.getGender()+delimeter+data.getAvgIncome());
					BWrite.newLine();
				}
				BWrite.close();
			}
			catch(Exception e){
				e.printStackTrace();
				System.out.println("error in creating the file recheck the code");
			}
			System.out.println("Output file created");
		}
		
		
	}

}
