package com.crm.practtice;

public class HowToFindIntandString {

	public static void main(String[] args) 
	{
		String s="124";
		String num=" ";
		for(int i=0;i<s.length();i++)
		{
			char ch=s.charAt(i);
			if(Character.isDigit(ch))
			{
				num=num+s.charAt(i);
			}
			else
			{
				break;
			}
			
		}
		int colNum=0;
		String colName="";
		if(num.equalsIgnoreCase(s))
		{
			colNum = Integer.parseInt(s);
		}
		else
		{
			colName=s;
		}
		System.out.println(colName);
		System.out.println(colNum);
	}

}
