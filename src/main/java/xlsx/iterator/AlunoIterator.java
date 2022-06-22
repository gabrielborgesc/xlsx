package xlsx.iterator;

import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlunoIterator {

	private int numero;
	private String nome;
	private Iterator<Cell> cellIterator;
	
	public AlunoIterator(Row alunoRow) {
		Iterator<Cell> cellIterator = alunoRow.cellIterator();
		Cell cell = cellIterator.next();
		this.numero = (int) cell.getNumericCellValue();
		cellIterator.next();
		cell = cellIterator.next();
		this.nome = cell.getStringCellValue();
		this.cellIterator = cellIterator;
	}

	public boolean isFalta() {
		Cell cell = this.cellIterator.next();
		return cell.getStringCellValue().toLowerCase().equals("f");
	}
	
}
