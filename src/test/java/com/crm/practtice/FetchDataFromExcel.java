package com.crm.practtice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FetchDataFromExcel {

	private static Date DOB;

	public static void main(String[] args) throws EncryptedDocumentException, IOException
	{
		//step:1 convert physical file into java readable object
		FileInputStream fin = new FileInputStream("./src/test/resources/TestData.xlsx");
		
		//Step2:open the excel using workbook facory class create(--)static method
		Workbook workbook=WorkbookFactory.create(fin);
		
		//step3:get the control of particular sheet by using getsheet(--) abstract method of Sheet interface
		Sheet sheetname = workbook.getSheet("Sdet33");
		
		//step4:get the control of particular row by using getRow(--) abstract method of Row interface
		Row row = sheetname.getRow(1);
		
		//step5:get the control of particular cell by using getsheet(--) abstract method of Cell interface
		Cell cell = row.getCell(0);
		
		//step6:Read/Fetch the particular data
		String data = cell.getStringCellValue();
		System.out.println(data);
		Cell cell1 = row.getCell(1);
		Date dob = cell1.getDateCellValue();
		System.out.println(dob);
		Cell cell3 = row.getCell(2);
		String place = cell3.getStringCellValue();
		System.out.println(place);
		
		//step:7 close the workbook by using object of workbook
		
		workbook.close();
	}

}
