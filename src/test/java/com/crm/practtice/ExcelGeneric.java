package com.crm.practtice;

import com.crm.genericUtility.ConstantPath;
import com.crm.genericUtility.ExcelUtility;
import com.crm.genericUtility.PropertyUtility;

public class ExcelGeneric {

	public static void main(String[] args) throws Throwable 
	{
		String propertypath=ConstantPath.PropertyFilePath;
		PropertyUtility.toLoadPropertyFile(propertypath);
		String excellpath = ConstantPath.excelPath;
		String excellpath1 = ConstantPath.excelPath1;
		String sheetname = PropertyUtility.TogetValue("sheetname");
		String sheetname1 = PropertyUtility.TogetValue("sheetname1");
		ExcelUtility.openExcel(excellpath);
		System.out.println(ExcelUtility.fetchData(sheetname, 14, 1));
		System.out.println(ExcelUtility.fetchData(sheetname, 14, 2));
		System.out.println(ExcelUtility.fetchData(sheetname, 14, 3));
		ExcelUtility.writeDataInExistingrow(sheetname, 14, 4, "fail", excellpath);
		ExcelUtility.writeDataInNewrow(sheetname, 21, 1, "status", excellpath);
		ExcelUtility.writeDataInNewrow(sheetname, 22, 1, "Pass", excellpath);
		ExcelUtility.openExcel(excellpath1);
		System.out.println(ExcelUtility.fetchData(sheetname1, 5, 1));
		ExcelUtility.writeDataInExistingrow(sheetname1, 11, 2, "fail", excellpath1);
		ExcelUtility.writeDataInNewrow(sheetname1, 21, 1, "status", excellpath1);
		ExcelUtility.writeDataInNewrow(sheetname1, 22, 1, "pass", excellpath1);
		ExcelUtility.closeExcel();
	}

}
