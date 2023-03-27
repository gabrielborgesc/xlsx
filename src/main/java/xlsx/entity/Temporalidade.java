package xlsx.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Temporalidade {
	
	private Integer id;
	private String codigo;
	private String descricao;
	private Integer idPai;

}
