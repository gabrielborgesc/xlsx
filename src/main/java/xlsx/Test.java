package xlsx;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Test {
	
	public static void main(String[] args) throws IOException {
		
		File file = new File("D:\\Gabriel\\Documentos\\Matérias\\Faltas\\test.xlsx");   //creating a new file instance  
		FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file  
		//creating Workbook instance that refers to .xlsx file  
		XSSFWorkbook wb = new XSSFWorkbook(fis);   
		XSSFSheet sheet = wb.getSheetAt(1);     //creating a Sheet object to retrieve object
		System.out.println("sheet name: " + sheet.getSheetName());
		Iterator<Row> itr = sheet.iterator();
		Row row = itr.next();
		Iterator<Cell> cellIterator1 = row.cellIterator();
		Cell cell1 = cellIterator1.next();
		row=itr.next();
		Iterator<Cell> cellIterator2 = row.cellIterator();
		Cell cell2 = cellIterator2.next(); 
		System.out.println(cell1.getNumericCellValue());
		System.out.println(cell2.getNumericCellValue());
		
	}
	
	public void test()   
	{  
		try  
		{  
			File file = new File("D:\\Gabriel\\Documentos\\Matérias\\Faltas\\faltas.xlsx");   //creating a new file instance  
			FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file  
			//creating Workbook instance that refers to .xlsx file  
			XSSFWorkbook wb = new XSSFWorkbook(fis);   
			XSSFSheet sheet = wb.getSheetAt(1);     //creating a Sheet object to retrieve object
			System.out.println("sheet name: " + sheet.getSheetName());
			Iterator<Row> itr = sheet.iterator();    //iterating over excel file
			while (itr.hasNext())                 
			{  
				Row row = itr.next();  
				Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column  
				while (cellIterator.hasNext())   
				{  
				Cell cell = cellIterator.next();
				switch (cell.getCellType())               
				{  
					case STRING:    //field that represents string cell type  
					System.out.print(cell.getStringCellValue() + "\t\t\t");  
					break;  
					case NUMERIC:    //field that represents number cell type  
					System.out.print(cell.getNumericCellValue() + "\t\t\t");  
					break;  
					default:					
				}  
				}  
				System.out.println("");  
			}  
		}  
		catch(Exception e)  
		{  
			e.printStackTrace();  
		}  
	}

}
