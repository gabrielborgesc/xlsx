package xlsx;

import java.io.File;  
import java.io.FileInputStream;
import java.util.Iterator;  
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import xlsx.iterator.MySheetIterator;
import xlsx.service.GeneralServices;

public class Reader {
	
	public static void main(String[] args)   
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
			
			Row diaRow = itr.next();
			Row materiaRow = itr.next();
			itr.next();
			Row horarioRow = itr.next();
			
			MySheetIterator mySheetIterator = new MySheetIterator(diaRow, materiaRow, horarioRow, itr);
			
			while(mySheetIterator.next());
			
			
//			Iterator<Cell> cellIterator = diaRow.cellIterator();   //iterating over each column
//			cellIterator.next();
//			cellIterator.next();
//			cellIterator.next();
//			Cell cell = cellIterator.next();
//			String stringDate = GeneralServices.convertDateCellValueToDateString(cell.getDateCellValue());
//			System.out.println("test: " + stringDate);

		}  
		catch(Exception e)  
		{  
			e.printStackTrace();  
		}  
	}

}
