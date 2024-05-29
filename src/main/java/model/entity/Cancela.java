package model.entity;

public class Cancela {
	private Integer idCancela;
	private String status;
	public Cancela() {
		super();
	}
	public Cancela(Integer idCancela, String status) {
		super();
		this.idCancela = idCancela;
		this.status = status;
	}
	public Integer getIdCancela() {
		return idCancela;
	}
	public void setIdCancela(Integer idCancela) {
		this.idCancela = idCancela;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
