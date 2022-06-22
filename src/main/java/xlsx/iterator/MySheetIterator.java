package xlsx.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import lombok.Getter;
import lombok.Setter;
import xlsx.entity.Dia;
import xlsx.entity.TempoDeAula;
import xlsx.service.GeneralServices;

@Getter
@Setter
public class MySheetIterator {
	
	private Iterator<Cell> diaCellIterator;
	private Iterator<Cell> materiaCellIterator;
	private Iterator<Cell> horarioCellIterator;
	private List<AlunoIterator> alunoIteratorList;
	private List<Dia> diaList;
	
	public MySheetIterator(Row diaRow, Row materiaRow, Row horarioRow, Iterator<Row> itr) {
		this.diaCellIterator = diaRow.cellIterator();
		this.diaCellIterator.next();
		this.diaCellIterator.next();
		this.diaCellIterator.next();
		
		this.materiaCellIterator = materiaRow.cellIterator();
		this.materiaCellIterator.next();
		this.materiaCellIterator.next();
		this.materiaCellIterator.next();
		
		this.horarioCellIterator = horarioRow.cellIterator();
		this.horarioCellIterator.next();
		this.horarioCellIterator.next();
		this.horarioCellIterator.next();
		
		itr.next();
		itr.next();
		itr.next();
		
		this.alunoIteratorList = new ArrayList<AlunoIterator>();
		while(itr.hasNext()) {
			Row alunoRow = itr.next();
			AlunoIterator alunoIterator = new AlunoIterator(alunoRow);
			if(alunoIterator.getNumero() == 0)
				break;
			this.alunoIteratorList.add(alunoIterator);
		}
		
//		for(AlunoIterator alunoIterator : this.alunoIteratorList) {
//			System.out.println(alunoIterator.getNumero() + ", " + alunoIterator.getNome());
//		}
		
		this.diaList = new ArrayList<Dia>();
		
	}
	
	public boolean next() {
		Cell diaCell = null;
		try {
			diaCell = this.diaCellIterator.next();		
		} catch (Exception e) {
		}
		
		Cell horarioCell = null;
		try {
			String stringDate = GeneralServices.convertDateCellValueToDateString(diaCell.getDateCellValue());
			
			//se não for pro catch, é novo dia
			Dia dia = new Dia(stringDate);
			this.diaList.add(dia);
			horarioCell = this.horarioCellIterator.next();
		} catch (Exception e) {
			// não é novo dia
			horarioCell = this.horarioCellIterator.next();
			if(horarioCell.getStringCellValue().equals("")) //se não tiver horário, acabou
				return false;
		}
		
		TempoDeAula tempoDeAula = new TempoDeAula(this.materiaCellIterator.next().getStringCellValue(), horarioCell.getStringCellValue());
		System.out.println(tempoDeAula.getNomeMateria() + " " + tempoDeAula.getHorario());
		
		for(AlunoIterator alunoIterator : this.alunoIteratorList) {
			if(alunoIterator.isFalta()) {
				System.out.println(alunoIterator.getNome());
				tempoDeAula.getFaltas().add(alunoIterator);
			}
		}
		System.out.println();
		System.out.println();
		return true;
		
	}

}
