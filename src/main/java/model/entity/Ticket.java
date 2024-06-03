package model.entity;

import java.time.LocalDateTime;

public class Ticket {
    private Integer id;
    private Vaga vaga;
    private Carro carro;
    private Cliente cliente;
    private LocalDateTime dataEntrada;
    private LocalDateTime dataSaida;
    private double valorPago;

    public Ticket() {}

    public Ticket(Vaga vaga, Carro carro, Cliente cliente, LocalDateTime dataEntrada, LocalDateTime dataSaida, double valorPago) {
        this.vaga = vaga;
        this.carro = carro;
        this.cliente = cliente;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.valorPago = valorPago;
        this.dataEntrada = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDateTime dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDateTime getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDateTime dataSaida) {
        this.dataSaida = dataSaida;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }
}