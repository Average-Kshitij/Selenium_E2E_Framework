package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter {
	
	static String cellValueString="";
	static double cellValueNum=0;
	static boolean cellValueBool=false, stringValFound =false , numValFound =false ,boolValFound =false;
	static int HeadRow = 0;
	

	public static void main(String[] args) throws InvalidFormatException, IOException {
		// TODO Auto-generated method stub

		
		
		System.out.println(readRows());
		System.out.println(HeadRow);
		
		updateRows(HeadRow);
				
		}

	
	
	
	
		
	public static String readRows() throws InvalidFormatException, IOException
	{
		String cellValue="";
		File fiele = new File("D:\\Test.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fiele);
		Sheet sheet =workbook.getSheet("Sheet1");
		
		int noRows = sheet.getLastRowNum();
		for(int i=0;i<noRows;i++)
		{
			try {
				Row row = sheet.getRow(i);
				Iterator<Cell> cellIt =  row.cellIterator();
				DataFormatter dataFormatter = new DataFormatter();
				
				while(cellIt.hasNext())
				{
					Cell cell = cellIt.next();
					
					 cellValue = dataFormatter.formatCellValue(cell);
					break;
					
				}
				if(cellValue.equals("Gl Date"))
				{
					HeadRow=i;
				break;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
			}
	}
	return cellValue;
		
	}
	
	public static String updateRows(int HeadRow) throws InvalidFormatException, IOException
	{
	int collNum=0;
	FileInputStream fiele = new FileInputStream("D:\\Test.xlsx");
	XSSFWorkbook workbook = new XSSFWorkbook(fiele);
	Sheet sheet =workbook.getSheet("Sheet1");
	
	Row row = sheet.getRow(HeadRow);
	int noRows = sheet.getLastRowNum();
	int lasCol = sheet.getRow(0).getLastCellNum();
	System.out.println();
	for(int i=0;i<lasCol;i++)
	{
		
		Iterator<Cell> cellIt =  row.cellIterator();
		DataFormatter dataFormatter = new DataFormatter();
		while(cellIt.hasNext())
		{
			Cell cell = cellIt.next();
			
			 String cellValue = dataFormatter.formatCellValue(cell);
			 if(cellValue.equals("Gl Date"))
			 {
				 collNum=i;
				 break;
			 }
			
		}
	
	}
	
	for(int i=HeadRow+1;i<=noRows;i++)
	{

		DataFormatter dataFormatter = new DataFormatter();
		for(int j=collNum-1;j<lasCol;j++)
		{
			Cell cell = sheet.getRow(i).getCell(j);
			
			cell.setCellValue("05/06/2023");
		}
		
	}
	
FileOutputStream fileOut = new FileOutputStream("D:\\Test.xlsx");


workbook.write(fileOut);
fileOut.close();
		
		return cellValueString;
		
	}
	

}

