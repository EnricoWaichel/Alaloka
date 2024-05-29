package model.entity;

import java.time.LocalDate;

public class Ticket {
	private Integer idTicket;
	private LocalDate dataEntrada;
	private LocalDate dataSaida;
	private double valorPago;
	public Ticket() {
		super();
	}
	public Ticket(Integer idTicket, LocalDate dataEntrada, LocalDate dataSaida, double valorPago) {
		super();
		this.idTicket = idTicket;
		this.dataEntrada = dataEntrada;
		this.dataSaida = dataSaida;
		this.valorPago = valorPago;
	}
	public Integer getIdTicket() {
		return idTicket;
	}
	public void setIdTicket(Integer idTicket) {
		this.idTicket = idTicket;
	}
	public LocalDate getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	public LocalDate getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(LocalDate dataSaida) {
		this.dataSaida = dataSaida;
	}
	public double getValorPago() {
		return valorPago;
	}
	public void setValorPago(double valorPago) {
		this.valorPago = valorPago;
	}
}
