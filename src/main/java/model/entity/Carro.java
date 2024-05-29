package model.entity;

public class Carro {
	private Integer idCarro;
	private String placa;
	private String tipoVeiculo;
	public Carro(Integer idCarro, String placa, String tipoVeiculo) {
		super();
		this.idCarro = idCarro;
		this.placa = placa;
		this.tipoVeiculo = tipoVeiculo;
	}
	public Carro() {
		super();
	}
	public Integer getIdCarro() {
		return idCarro;
	}
	public void setIdCarro(Integer idCarro) {
		this.idCarro = idCarro;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getTipoVeiculo() {
		return tipoVeiculo;
	}
	public void setTipoVeiculo(String tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}
}
