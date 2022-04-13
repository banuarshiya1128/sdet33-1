package com.crm.practtice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadMultipleData2 {

	public static void main(String[] args) throws EncryptedDocumentException, IOException 
	{
		FileInputStream fin = new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook workbook = WorkbookFactory.create(fin);
		Sheet sheet = workbook.getSheet("Login");
		for(int i=1,j=0;i<=sheet.getLastRowNum();i++)
		{
			 String username = sheet.getRow(i).getCell(0).getStringCellValue();
		if(username.equalsIgnoreCase("admin10"))
		{
			System.out.println(sheet.getRow(i).getCell(1).getStringCellValue());
		}
		}
	}

}
