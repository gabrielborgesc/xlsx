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

public class ReaderTemporalidadeFimSplitQuebraDeLinha {
	
	public static void main(String[] args)
	/*
	 * Varre todas as descrições e onde tiver quebra de linha ("\n"), faz split e pega só a primeira parte.
	 * Este código espera ler um xlsx contendo apenas uma coluna e sendo ela a descrição das temporalidades.
	 * Ao final, cria um novo sheet chamado descricao-formatada no xlsx original com as descrições formatadas.
	 */
	{  
		try  
		{  
			File file = new File("B:\\Dev\\sped30\\temporalidades\\temporalidades-descicao.xlsx");   //creating a new file instance  
			FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file  
			//creating Workbook instance that refers to .xlsx file  
			XSSFWorkbook wb = new XSSFWorkbook(fis);   
			XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object
//			System.out.println("sheet name: " + sheet.getSheetName());
			Iterator<Row> itr = sheet.iterator();    //iterating over excel file
			
			List<Iterator<Cell>> finalCellIteratorList = new ArrayList<Iterator<Cell>>();
			while(itr.hasNext()) {
				Row row = itr.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				if(!cellIterator.hasNext())
					break;

				finalCellIteratorList.add(row.cellIterator());
			}

			
			XSSFSheet newSheet = wb.createSheet("descricao-formatada");
			int rowNumber = 0;
			for(Iterator<Cell> cellIterator : finalCellIteratorList) {
				
				Row row = newSheet.createRow(rowNumber++);
				Cell newCell = row.createCell(0);

				String originalCellValue = cellIterator.next().getStringCellValue();
				String[] spplitedArray = originalCellValue.split("\n");
				
				newCell.setCellValue(spplitedArray[0].trim());
				
			}
			
			FileOutputStream out = new FileOutputStream(file);
			wb.write(out);
		        out.close();

		}  
		catch(Exception e)  
		{  
			e.printStackTrace();  
		}  
	}

}
