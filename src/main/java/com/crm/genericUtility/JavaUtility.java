package com.crm.genericUtility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * This class contains all java specific common methods
 * @author Prakruthi
 *
 */
public class JavaUtility 
{
	/**
	 * This method is use create random numbers
	 * @param limit
	 * @return
	 */
	public static int generateRandomNumber(int limit)
	{
		Random random = new Random();
		int randomnumber=random.nextInt(limit);
		return randomnumber;
	}
	
	/**
	 * This Method is used get the date in required format
	 * @return
	 */
	public static String getCurrentDateandTime()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
		Date date = new Date();
		String requireddateformat = sdf.format(date);
		return requireddateformat;
	}
	public static long stringTolong(String timeout)
	{
		long longtimeout=Long.parseLong(timeout);
		return longtimeout;
	}
}
