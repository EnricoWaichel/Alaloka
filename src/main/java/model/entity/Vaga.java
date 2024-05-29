package model.entity;

public class Vaga {
	private Integer id;
	private Integer numeroVaga;
	private String tipoVaga;
	private boolean disponivel;
	
	public Vaga(Integer id, Integer numeroVaga, String tipoVaga, boolean disponivel) {
		super();
		this.id = id;
		this.numeroVaga = numeroVaga;
		this.tipoVaga = tipoVaga;
		this.disponivel = disponivel;
	}

	public Vaga() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumeroVaga() {
		return numeroVaga;
	}

	public void setNumeroVaga(Integer numeroVaga) {
		this.numeroVaga = numeroVaga;
	}

	public String getTipoVaga() {
		return tipoVaga;
	}

	public void setTipoVaga(String tipoVaga) {
		this.tipoVaga = tipoVaga;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}
	
	
}
