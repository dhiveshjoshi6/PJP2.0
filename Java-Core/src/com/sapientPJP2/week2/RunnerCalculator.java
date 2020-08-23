package com.sapientPJP2.week2;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

public class RunnerCalculator {
	
	static int monthDays[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	static class Date  
    { 
        int d, m, y; 
        public Date(int d, int m, int y) 
        { 
            this.d = d; 
            this.m = m;
            this.y = y; 
        }  
    }; 
    
	public static void main(String[] args) throws ParseException, IOException {
		
	while(true) {
		
		System.out.println("Option 1 - Add, Subtract between two dates and express the output in days, dates, weeks, months");
		System.out.println("Option 2 - Add, Subtract 'n' Days, Months, Weeks to the given date and derive the final date");
		System.out.println("Option 3 - Determine the Day of the Week for a given date");
		System.out.println("Option 4 - Determine the Week number for a given a date");
		System.out.println("Option 5 - Adding two dates");
		System.out.println("Option 6 - See the History of all operations performed");
		System.out.println("Option 7 - Wants to enter the NLP phrase ?");
		System.out.println("Option 8 - Close the file");
		System.out.println("Enter the option you want to choose :");
		
		Scanner scan = new Scanner(System.in);
		int inputOption = scan.nextInt();
		
		if(inputOption == 1) {
			
			System.out.print("Enter the first date - lower date  - DD-MM-YYYY format ");
			String date1 = scan.next();
			System.out.print("Enter the second date - higher date - DD-MM-YYYY format ");
			String date2 = scan.next();
			
			int day1 = Integer.parseInt(date1.substring(0,2));
			int month1 = Integer.parseInt(date1.substring(3,5));
			int year1 = Integer.parseInt(date1.substring(6,10));
			
			int day2 = Integer.parseInt(date2.substring(0,2));
			int month2 = Integer.parseInt(date2.substring(3,5));
			int year2 = Integer.parseInt(date2.substring(6,10));
			
			Date dt1 = new Date(day1,month1,year1);
			Date dt2 = new Date(day2,month2,year2);
			
			// calculation of total number of days 
			int totalDays = DiffTwoDates.getDifference(dt1, dt2);
			System.out.println("Total number of Days : " + totalDays);
			
			// calculation of total number of months 
			int totalMonths = 0;
			int diffMonths = (dt2.y - dt1.y - 1)*12;
			int i=dt1.m+1;
			while(i<=12) {
				i++;
				totalMonths++;
			}
			i=1;
			while(i<dt2.m) {
				i++;
				totalMonths++;
			}
			totalMonths+=(diffMonths+((monthDays[dt1.m-1]-dt1.d)+dt2.d)/31);
			
			int remdayMonths = ((monthDays[dt1.m-1]-dt1.d)+dt2.d)%31;
			int remdayWeeks = totalDays%7; 

			System.out.println("In Months : " + totalMonths + " Months " + remdayMonths + " Days");
			System.out.println("In Weeks : " + totalDays/7 + " Weeks " + remdayWeeks + " Days");
			
			try
			{
			    String filename= "history.txt";
			    FileWriter fw = new FileWriter(filename,true);
			    fw.write("\n"+"Input Option 1 : Date1 : "+date1 + " Date2 : "+date2 + "\n");
			    fw.write("Output :"+ "\n");
			    fw.write("Difference bw two dates in terms of days : "+ totalDays + "\n");
			    fw.write("Difference bw two dates in terms of months :"+ totalMonths + " Months " + remdayMonths + " Days"+ "\n");
			    fw.write("Difference bw two dates in terms of weeks :"+ totalDays/7 + " Weeks " + remdayWeeks + " Days"+ "\n");
			    fw.write("================================================================================================"+ "\n");
			    fw.close();
			}
			catch(IOException msg)
			{
			    System.err.println("IOException: " + msg.getMessage());
			}
		}
		else if(inputOption == 2) {
			System.out.println("Enter the date - DD-MM-YYYY format ");
			String date = scan.next();
			
			int day = Integer.parseInt(date.substring(0,2));
			int month = Integer.parseInt(date.substring(3,5));
			int year = Integer.parseInt(date.substring(6,10));
			
			Date dt = new Date(day,month,year);
			
			System.out.println("Enter the value of N ");
			int n = scan.nextInt();
			System.out.println("N is days or months or years ? ");
			String input = scan.next();
			System.out.println("Is add or subtract ?");
			String operation = scan.next();
			
			String givenDate = Integer.toString(dt.y)+"-"+Integer.toString(dt.m)+"-"+Integer.toString(dt.d);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(givenDate));
			String newDate = null;
			
			if(operation.equals("add")) {
				if(input.equals("year")) {
					dt.y +=n;
					System.out.println("New Date after adding " + n + " years : " + dt.d+"-"+dt.m+"-"+dt.y);
				}
				else if(input.equals("months")) {
					dt.y += n/12 + (dt.m+n%12)/12; 
					if((dt.m+n%12)/12 == 0) {
						dt.m += n%12; 
					}
					else dt.m = (dt.m+n%12)%12;
					System.out.println("New Date after adding " + n + " months : " + dt.d+"-"+dt.m+"-"+dt.y);
				}
				else if(input.equals("days")) {
					cal.add(Calendar.DAY_OF_MONTH, 32);
					newDate = sdf.format(cal.getTime());
					System.out.println("New Date after adding " + n + " days : "+newDate);
				}
				try
				{
				    String filename= "history.txt";
				    FileWriter fw = new FileWriter(filename,true);
				    fw.write("\n"+"Input Option 2 : Date : "+date + "Add N : "+ n+ "\n" );
				    fw.write("Output :"+ "\n");
				    if(input.equals("year"))
				    	fw.write("New Date after adding " + n + " years : " + dt.d+"-"+dt.m+"-"+dt.y+ "\n");
				    else if(input.equals("months"))
				    	fw.write("New Date after adding " + n + " months : " + dt.d+"-"+dt.m+"-"+dt.y+ "\n");
				    else if(input.equals("days"))
				    	fw.write("New Date after adding " + n + " days : " + newDate+ "\n");
				    fw.write("================================================================================================"+ "\n");
				    fw.close();
				}
				catch(IOException msg)
				{
				    System.err.println("IOException: " + msg.getMessage());
				}
			}
			else if(operation.equals("subtract")){
				if(input.equals("years")) {
					cal.add(Calendar.YEAR,-1*n); 
					newDate = sdf.format(cal.getTime()); 
					System.out.println("New Date after substracting " + n + " years : "+newDate);
				}
				else if(input.equals("months")) {
					cal.add(Calendar.MONTH,-1*n); 
					newDate = sdf.format(cal.getTime());  
					System.out.println("New Date after substracting " + n + " months : "+newDate);
				}
				else if(input.equals("days")){
					cal.add(Calendar.DATE,-1*n); 
					newDate = sdf.format(cal.getTime());
					System.out.println("New Date after substracting " + n + " days : "+newDate);
				}
				try
				{
				    String filename= "history.txt";
				    FileWriter fw = new FileWriter(filename,true);
				    fw.write("\n"+"Input Option 2 : Date : "+date + "Subtract N : "+ n + "\n");
				    fw.write("Output :"+ "\n");
				    if(input.equals("year"))
				    	fw.write("New Date after substracting " + n + " years : "+newDate+ "\n");
				    else if(input.equals("months"))
				    	fw.write("New Date after substracting " + n + " months : "+newDate+ "\n");
				    else if(input.equals("days"))
				    	fw.write("New Date after substracting " + n + " days : "+newDate+ "\n");
				    fw.write("================================================================================================"+ "\n");
				    fw.close();
				}
				catch(IOException msg)
				{
				    System.err.println("IOException: " + msg.getMessage());
				}
			}
		}
		else if(inputOption == 3) {
			System.out.println("Enter the date : DD-MM-YYYY format ");
			String date = scan.next();
			
			int day = Integer.parseInt(date.substring(0,2));
			int month = Integer.parseInt(date.substring(3,5));
			int year = Integer.parseInt(date.substring(6,10));
			
			Date dt = new Date(day,month,year);
			String givenDate = Integer.toString(dt.y)+"-"+Integer.toString(dt.m)+"-"+Integer.toString(dt.d);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(givenDate));
			System.out.println("Day of the week for the given date : " + cal.get(Calendar.DAY_OF_WEEK));
			try
			{
			    String filename= "history.txt";
			    FileWriter fw = new FileWriter(filename,true);
			    fw.write("\n"+"Input Option 3 : Date : "+date+ "\n");
			    fw.write("Output :"+ "\n");
			    fw.write("Day of the week for the given date : " + cal.get(Calendar.DAY_OF_WEEK)+ "\n");
			    fw.write("================================================================================================"+ "\n");
			    fw.close();
			}
			catch(IOException msg)
			{
			    System.err.println("IOException: " + msg.getMessage());
			}
		}
		else if(inputOption == 4) {
			System.out.println("Enter the date : DD-MM-YYYY format ");
			String date = scan.next();
			
			int day = Integer.parseInt(date.substring(0,2));
			int month = Integer.parseInt(date.substring(3,5));
			int year = Integer.parseInt(date.substring(6,10));
			
			Date dt = new Date(day,month,year);
			String givenDate = Integer.toString(dt.y)+"-"+Integer.toString(dt.m)+"-"+Integer.toString(dt.d);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(givenDate));
			System.out.println("Week number for the given date : " + cal.get(Calendar.WEEK_OF_YEAR));
			try
			{
			    String filename= "history.txt";
			    FileWriter fw = new FileWriter(filename,true);
			    fw.write("\n"+"Input Option 4 : Date : "+date+ "\n");
			    fw.write("Output :"+ "\n");
			    fw.write("Week number for the given date : " + cal.get(Calendar.WEEK_OF_YEAR)+ "\n");
			    fw.write("================================================================================================"+ "\n");
			    fw.close();
			}
			catch(IOException msg)
			{
			    System.err.println("IOException: " + msg.getMessage());
			}
		}
		else if(inputOption == 5) { 
			System.out.print("Enter the first date - DD-MM-YYYY format ");
			String date1 = scan.next();
			System.out.print("Enter the second date - DD-MM-YYYY format ");
			String date2 = scan.next();
			
			int day1 = Integer.parseInt(date1.substring(0,2));
			int month1 = Integer.parseInt(date1.substring(3,5));
			int year1 = Integer.parseInt(date1.substring(6,10));
			
			int day2 = Integer.parseInt(date2.substring(0,2));
			int month2 = Integer.parseInt(date2.substring(3,5));
			int year2 = Integer.parseInt(date2.substring(6,10));
			
			Date dt1 = new Date(day1,month1,year1);
			Date dt2 = new Date(day2,month2,year2);
			
			String givenDate = Integer.toString(dt1.y)+"-"+Integer.toString(dt1.m)+"-"+Integer.toString(dt1.d);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(givenDate));
			
			cal.add(Calendar.DATE, dt2.d);
			cal.add(Calendar.MONTH, dt2.m);
			cal.add(Calendar.YEAR, dt2.y); 
			
			String newDate = sdf.format(cal.getTime());
			System.out.println("New date after adding Two dates : "+ newDate);	
			try
			{
			    String filename= "history.txt";
			    FileWriter fw = new FileWriter(filename,true);
			    fw.write("\n"+"Input Option 5 : Date1 : "+ date1 + " Date2 : "+date2+ "\n");
			    fw.write("Output :"+ "\n");
			    fw.write("New date after adding Two dates : "+ newDate+ "\n");
			    fw.write("================================================================================================"+ "\n");
			    fw.close();
			}
			catch(IOException msg)
			{
			    System.err.println("IOException: " + msg.getMessage());
			}
		}
		else if(inputOption == 6) {
			 BufferedReader br = new BufferedReader(new FileReader("history.txt"));
			 String line;
			 while ((line = br.readLine()) != null) {
			   System.out.println(line);
			 }
			 br.close();
		}
		else if(inputOption == 7) {
			
			System.out.println("Enter the NLP phrase :");
			Scanner sc = new Scanner(System.in);
	        String phrase = sc.nextLine();
			String filename= "history.txt";
		    FileWriter fw = new FileWriter(filename,true);
			
			LocalDate currDate = LocalDate.now();
			fw.write("Input Option 7 : NLP Phrase conversion : "+ phrase + " \n");
			
			if(phrase.equals("today")) {
				System.out.println(currDate);
			}		
			else if(phrase.equals("tomorrow")) {
				System.out.println(currDate.plusDays(1));
			}
			else if(phrase.equals("day after tomorrow")) {
				System.out.println(currDate.plusDays(2));
			}
			else if(phrase.equals("yesterday")) {
				System.out.println(currDate.minusDays(1));
			}
			else if(phrase.equals("day before yesterday")) {
				System.out.println(currDate.minusDays(2));
			}
			else if(phrase.equals("last week")) {
				System.out.println(currDate.minusWeeks(1));
			}
			else if(phrase.equals("previous week")) {
				System.out.println(currDate.minusWeeks(1));
			}
			
			else if(phrase.equals("next week")) {
				System.out.println(currDate.plusWeeks(1));
			}
			else if(phrase.equals("next month")) {
				System.out.println(currDate.plusMonths(1));
			}
			else if(phrase.equals("next year")) {
				System.out.println(currDate.plusYears(1));
			}
			else if(phrase.equals("last month")){
				System.out.println(currDate.minusMonths(1));
			}
			else if(phrase.equals("last year")) {
				System.out.println(currDate.minusYears(1));
			}
			else if(phrase.equals("month after")) {
				System.out.println(currDate.plusMonths(1));
			}
			else if(phrase.equals("month before")) {
				System.out.println(currDate.minusMonths(1));
			}
			else if(phrase.equals("weeks from now")) {
				System.out.println("How much ?");
				int n = scan.nextInt();
				System.out.println(currDate.plusWeeks(n));
			}
			else if(phrase.equals("days from now")) {
				System.out.println("How much ?");
				int n = scan.nextInt();
				System.out.println(currDate.plusDays(n));
			}
			else if(phrase.equals("months from now")) {
				System.out.println("How much ?");
				int n = scan.nextInt();
				System.out.println(currDate.plusMonths(n));
			}
			else if(phrase.equals("years from now")) {
				System.out.println("How much ?");
				int n = scan.nextInt();
				System.out.println(currDate.plusYears(n));
			}
			else if(phrase.equals("days earlier")) {
				System.out.println("How much ?");
				int n = scan.nextInt();
				System.out.println(currDate.minusDays(n));
			}
			else if(phrase.equals("weeks earlier")) {
				System.out.println("How much ?");
				int n = scan.nextInt();
				System.out.println(currDate.minusWeeks(n));
			}
			else if(phrase.equals("months earlier")) {
				System.out.println("How much ?");
				int n = scan.nextInt();
				System.out.println(currDate.minusMonths(n));
			}
			else if(phrase.equals("years earlier")) {
				System.out.println("How much ?");
				int n = scan.nextInt();
				System.out.println(currDate.minusYears(n));
			}
			fw.write("Output :" + currDate + "\n");
			fw.write("================================================================================================"+ "\n");
			fw.close();
			
		}
		else if(inputOption == 8) {
			System.out.println("Closing the Calculator");
			PrintWriter pw = new PrintWriter("history.txt");
			pw.close();
			String filename= "history.txt";
		    FileWriter fw = new FileWriter(filename,true);
		    fw.write("========================PRINTING THE HISTORY LOGS==============================================="+"\n");
			fw.write("Option 1 - Add, Subtract between two dates and express the output in days, dates, weeks, months"+"\n");
			fw.write("Option 2 - Add, Subtract 'n' Days, Months, Weeks to the given date and derive the final date"+"\n");
			fw.write("Option 3 - Determine the Day of the Week for a given date"+"\n");
			fw.write("Option 4 - Determine the Week number for a given a date"+"\n");
			fw.write("Option 5 - Adding two dates"+"\n");
			fw.write("Option 6 - See the History of all operations performed"+"\n");
			fw.write("Option 7 - Wants to enter the NLP phrase ?");
			fw.write("Option 8 - Close the file"+"\n");
			fw.write("================================================================================================"+"\n");
			fw.close();
			System.exit(1);
		}
	}
  }
	

}
