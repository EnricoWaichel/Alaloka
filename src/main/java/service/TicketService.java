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

    public Ticket salvarTicket(Ticket ticket) throws AlalokaException {
        Vaga vaga = ticket.getVaga();
        if (!vaga.isDisponivel()) {
            throw new AlalokaException("Vaga não está disponível.");
        }
        vaga.setDisponivel(false);
        vagaRepository.alterar(vaga);

        ticket.setDataEntrada(LocalDateTime.now());
        return ticketRepository.salvar(ticket);
    }

    public boolean atualizarTicket(Ticket ticket) throws AlalokaException {
        Ticket ticketExistente = ticketRepository.consultarPorId(ticket.getId());
        if (ticketExistente == null) {
            throw new AlalokaException("Ticket não encontrado.");
        }
        return ticketRepository.alterar(ticket);
    }

    public boolean excluirTicket(int id) throws AlalokaException {
        Ticket ticket = ticketRepository.consultarPorId(id);
        if (ticket == null) {
            throw new AlalokaException("Ticket não encontrado.");
        }

        Vaga vaga = ticket.getVaga();
        vaga.setDisponivel(true);
        vagaRepository.alterar(vaga);

        return ticketRepository.excluir(id);
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

        double taxaPorHora = 10.0; // Valor temporário

        double valorTotal = diffEmHoras * taxaPorHora;

        return valorTotal;
    }
}
