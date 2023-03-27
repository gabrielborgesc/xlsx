package xlsx;

import java.io.File;  
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

import xlsx.entity.Temporalidade;
import xlsx.iterator.MySheetIterator;
import xlsx.service.GeneralServices;

public class DefineIdPai {
	
	public static String findCodigoPai(String codigo) {
		
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
	
	private static int findIdPai(Iterator<Row> itr, String codigoPai) {
		while(itr.hasNext()) {
			Row row = itr.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			Integer id = (int) cellIterator.next().getNumericCellValue();
			String codigo = cellIterator.next().getStringCellValue();
			if(codigo.equals(codigoPai)) {
				return id;
			}
		}
		
		
		return 0;
	}
	
	public static void main(String[] args)   
	{  
		try  
		{  
			File file = new File("D:\\Dev\\CDS\\temporalidades\\temporalidades_SPED_3.0_nova.xlsx");   //creating a new file instance  
			FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file  
			//creating Workbook instance that refers to .xlsx file  
			XSSFWorkbook wb = new XSSFWorkbook(fis);   
			XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object
//			System.out.println("sheet name: " + sheet.getSheetName());
			Iterator<Row> itr = sheet.iterator();    //iterating over excel file
			
			List<Temporalidade> list = new ArrayList<Temporalidade>();
			while(itr.hasNext()) {
				Row row = itr.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				
				Integer id = (int) cellIterator.next().getNumericCellValue();
				
				String codigo = cellIterator.next().getStringCellValue();
				
				String descricao = cellIterator.next().getStringCellValue();
				
				String codigoPai = findCodigoPai(codigo);
				Integer idPai = null;
				if(!codigoPai.equals(codigo)) {
					idPai = findIdPai(sheet.iterator(), codigoPai);
				}
				list.add(new Temporalidade(id, codigo, descricao, idPai));
				
				if(codigo.equals("692")) {
					writeToFile(file, wb, list);
					return;
				}
			}
			

		}  
		catch(Exception e)  
		{  
			e.printStackTrace();  
		}  
	}

	private static void writeToFile(File file, XSSFWorkbook wb, List<Temporalidade> list) {
		XSSFSheet newSheet = wb.createSheet("final");
		int rowNumber = 0;
		for(Temporalidade item : list) {
			
			Row row = newSheet.createRow(rowNumber++);
			Cell newCell = row.createCell(0);
			newCell.setCellValue(item.getId());
			
			newCell = row.createCell(1);
			newCell.setCellValue(item.getCodigo());
			
			newCell = row.createCell(2);
			newCell.setCellValue(item.getDescricao());
			
			newCell = row.createCell(3);
			if(item.getIdPai()==null) {
				newCell.setCellValue("");
			} else {
				newCell.setCellValue(item.getIdPai());
			}
					
		}
		
		try {
			FileOutputStream out = new FileOutputStream(file);
			wb.write(out);
	        out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
