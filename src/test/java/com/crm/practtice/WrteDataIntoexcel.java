package com.crm.practtice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WrteDataIntoexcel {

	private static Sheet sheet;

	public static void main(String[] args) throws EncryptedDocumentException, IOException
	{
		//step1:we should convert physical data into java readable object using FileInputStream
		FileInputStream fin = new FileInputStream("./src/test/resources/TestData.xlsx");
		
		//step2:open the excel file using workbookfactory class and create(fin)
		Workbook workbook=WorkbookFactory.create(fin);
		
		//step3:we should get contol of particular sheet using getsheet(---)
		sheet = workbook.getSheet("Sdet33");
		
		//step4:we shoul get the control of particular row using getrow(---)
		Row row = sheet.getRow(1);
		
		//step5:we should create particular cell by using createcell(---)
		Cell cell = row.createCell(3);
		
		//step6:we should store/write the data by using set cell value
		cell.setCellValue("fail");
		
		//step7:we shold specify the path of the file of excel by using fileoutput stream
		FileOutputStream fos = new FileOutputStream("./src/test/resources/TestData.xlsx");
		
		//step8:we should flush the data
		workbook.write(fos);
		
		//step9:close the workbook using close()
		workbook.close();
		
		System.out.println("data is stored in excel");	
	}

}
