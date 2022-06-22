package xlsx.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import xlsx.iterator.AlunoIterator;

@Getter
@Setter
public class TempoDeAula {
	
	private String nomeMateria;
	private String horario;
	private List<AlunoIterator> faltas = new ArrayList<AlunoIterator>();

	public TempoDeAula(String nomeMateria, String horario) {
		this.nomeMateria = nomeMateria;
		this.horario = horario;
		this.faltas = new ArrayList<AlunoIterator>();
	}
	
}
