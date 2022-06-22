package xlsx.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dia {
	
	private String data;
	private List<TempoDeAula> temposDeAula;
	
	public Dia(String data) {
		this.data = data;
	}

}
