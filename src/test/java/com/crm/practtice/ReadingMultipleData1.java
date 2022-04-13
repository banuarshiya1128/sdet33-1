package com.crm.practtice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadingMultipleData1 {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		FileInputStream fin = new FileInputStream("./src/test/resources/TestData.xlsx");
		Workbook workbook = WorkbookFactory.create(fin);
		Sheet sheet = workbook.getSheet("Login");
		for(int i=1;i<=sheet.getLastRowNum();i++)
		{

			for(int j=0;j<sheet.getRow(i).getPhysicalNumberOfCells();j++)
			{
				System.out.print(sheet.getRow(i).getCell(j).getStringCellValue()+" ");
				
			}
			System.out.println();

		}
	}
}


