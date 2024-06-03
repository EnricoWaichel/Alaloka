package service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import exception.AlalokaException;
import model.entity.Carro;
import model.entity.Cliente;
import model.entity.Ticket;
import model.entity.Vaga;
import model.repository.alaloka.TicketRepository;
import model.repository.alaloka.VagaRepository;

public class TicketService {

    private TicketRepository ticketRepository = new TicketRepository();
    private VagaRepository vagaRepository = new VagaRepository();

    public Ticket criarTicket(Vaga vaga, Carro carro, Cliente cliente) throws AlalokaException {
        if (!vaga.isDisponivel()) {
            throw new AlalokaException("Vaga não está disponível.");
        }
        vaga.setDisponivel(false);
        vagaRepository.alterar(vaga);

        Ticket novoTicket = new Ticket(vaga, carro, cliente, LocalDateTime.now(), null, 0.0);
        return ticketRepository.salvar(novoTicket);
    }

    public boolean finalizarTicket(int ticketId) throws AlalokaException {
        Ticket ticket = ticketRepository.consultarPorId(ticketId);
        if (ticket == null) {
            throw new AlalokaException("Ticket não encontrado.");
        }
        ticket.setDataSaida(LocalDateTime.now());
        ticket.setValorPago(calcularValor(ticket));

        Vaga vaga = ticket.getVaga();
        vaga.setDisponivel(true);
        vagaRepository.alterar(vaga);

        return ticketRepository.alterar(ticket);
    }

    public Ticket consultarTicketPorId(int id) {
        return ticketRepository.consultarPorId(id);
    }

    public List<Ticket> consultarTodosTickets() {
        return ticketRepository.consultarTodos();
    }

    private double calcularValor(Ticket ticket) {
        LocalDateTime dataEntrada = ticket.getDataEntrada();
        LocalDateTime dataSaida = ticket.getDataSaida();
        
        long diffEmHoras = ChronoUnit.HOURS.between(dataEntrada, dataSaida);
        
        // Suponha uma taxa fixa por hora
        double taxaPorHora = 10.0; // Valor temporario
        
        double valorTotal = diffEmHoras * taxaPorHora;
        
        return valorTotal;
    }

}
