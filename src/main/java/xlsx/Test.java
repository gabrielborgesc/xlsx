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
		String codigo = "300";
		String codigo1 = "002.01";
		String codigo2 = "002.1";
		String codigo3 = "002.021";
		String codigo4 = "120";
		String codigo5 = "110";
		
		System.out.println(codigo + " -> " + new Test().findCodigoPai(codigo));
		System.out.println(codigo1 + " -> " + new Test().findCodigoPai(codigo1));
		System.out.println(codigo2 + " -> " + new Test().findCodigoPai(codigo2));
		System.out.println(codigo3 + " -> " + new Test().findCodigoPai(codigo3));
		System.out.println(codigo4 + " -> " + new Test().findCodigoPai(codigo4));
		System.out.println(codigo5 + " -> " + new Test().findCodigoPai(codigo5));
	}
	
	public String findCodigoPai(String codigo) {
		
		String caracterPonto = ".";
		String caracterZero = "0";
		
		if(!codigo.contains(caracterPonto)) {
			String lastCharacter = String.valueOf(codigo.charAt(codigo.length() - 1));
			if(lastCharacter.equals(caracterZero)) {
				String substring = codigo.substring(0, codigo.length() - 2);
				return substring + caracterZero + caracterZero;
			}
			String substring = codigo.substring(0, codigo.length() - 1);
			String codigoPai = substring + caracterZero;
			return codigoPai;
		}
		String substring = codigo.substring(0, codigo.length() - 1);
		String lastCharacter = String.valueOf(substring.charAt(substring.length() - 1));
		if(lastCharacter.equals(caracterPonto)) {
			return substring.replace(".", "");
		}
		
		if(lastCharacter.equals(caracterZero)) {
			return substring.replace(".0", "");
		}
		
		return substring;
		
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
