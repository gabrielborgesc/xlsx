package xlsx;

import java.io.File;  
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import xlsx.iterator.MySheetIterator;
import xlsx.service.GeneralServices;

public class ReaderDescricaoFim {
	
	public static void main(String[] args)   
	{  
		try  
		{  
			File file = new File("D:\\Dev\\CDS\\temporalidades\\meio\\atividade-meio-descricao-recortado-customizado.xlsx");   //creating a new file instance  
			FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file  
			//creating Workbook instance that refers to .xlsx file  
			XSSFWorkbook wb = new XSSFWorkbook(fis);   
			XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object
//			System.out.println("sheet name: " + sheet.getSheetName());
			Iterator<Row> itr = sheet.iterator();    //iterating over excel file
			
			List<Iterator<Cell>> finalCellIteratorList = new ArrayList<Iterator<Cell>>();
			int line = 0;
			while(itr.hasNext()) {
				line++;
				Row row = itr.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				Cell cell = cellIterator.next();
				
				String cellValue = cell.getStringCellValue();
				if(!cellValue.isEmpty() && !Character.isDigit(cellValue.charAt(0))){
//					finalCellIteratorList.add(row.cellIterator());
					System.out.println(line + " -> " + cellValue);
					System.out.println();
				}

			}
			
//			XSSFSheet newSheet = wb.createSheet("formatado");
//			int rowNumber = 0;
//			for(Iterator<Cell> cellIterator : finalCellIteratorList) {
//				
//				Row row = newSheet.createRow(rowNumber++);
////				for(int j = 0; j < 4; j++ ) {
//					Cell newCell = row.createCell(0);
//					newCell.setCellValue(cellIterator.next().getStringCellValue());
////				}
//			}
//			
//			FileOutputStream out = new FileOutputStream(file);
//			wb.write(out);
//		        out.close();

		}  
		catch(Exception e)  
		{  
			e.printStackTrace();  
		}  
	}

}
