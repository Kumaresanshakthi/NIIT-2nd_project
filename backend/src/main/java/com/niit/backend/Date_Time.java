package com.niit.backend;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Date_Time 
{
	public String getDateTime()
	{
		DateFormat df = new SimpleDateFormat("dd-MMM-yyyy @ HH:mm");
	       Date dateobj = new Date();
	       String datetime = df.format(dateobj).toString();
	       System.out.println("Date - "+datetime);
	       return datetime;
	}
	
	public static void main(String[] args) 
	{
		Date_Time dt = new Date_Time();
		System.out.println(dt.getDateTime());
	}

	public String toStringDate(Date date)
	{
		DateFormat df = new SimpleDateFormat("dd-MMM-yyyy"); 
		 String StringDate = df.format(date).toString();
		 return StringDate;
	}
	}
